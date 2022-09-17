package site.isscloud.xdclass.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.isscloud.xdclass.exception.BizException;
import site.isscloud.xdclass.mapper.PlayRecordMapper;
import site.isscloud.xdclass.mapper.VideoOrderMapper;
import site.isscloud.xdclass.model.entity.*;
import site.isscloud.xdclass.service.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoOrderServiceIpml implements VideoOrderService {

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private PlayRecordService playRecordService;

    @Autowired
    private EpisodeService episodeService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private UserService userService;

    @Override
    public List<VideoOrder> selectVideoOrdersByUserId(Integer userId) {
        return videoOrderMapper.selectVideoOrdersByUserId(userId);
    }

    @Override
    public VideoOrder getVideoOrderByUserIdAndVideoId(Integer videoId, Integer userId, Integer state) {
        return videoOrderMapper.getVideoOrderByUserIdAndVideoIdAndState(videoId,userId,state);
    }

    @Override
    public VideoOrder getVideoOrderById(Integer id) {
        return videoOrderMapper.getVideoOrderById(id);
    }

    @Override
    // 开启此方法事务
    @Transactional
    public int save(VideoOrder videoOrder) {
        Integer orderId = videoOrder.getId();
        Integer videoId = videoOrder.getVideoId();
        Integer userId = videoOrder.getUserId();
        if(videoId == null || userId==null) {
            throw new BizException(-1, "视频id或用户id不存在");
        }
        Video video = videoService.getVideoDetailByIdLazy(videoId,1);
        if(video == null) {
            throw new BizException(-1, "订单关联视频不存在");
        }
        User user = userService.getUserById(userId);
        if(user == null) {
            throw new BizException(-1, "订单关联用户不存在");
        }
        VideoOrder order = videoOrderMapper.getVideoOrderByUserIdAndVideoIdAndState(videoOrder.getVideoId(),videoOrder.getUserId(),1);
        if( orderId != null && orderId > 0) {
            // 修改订单
        } else {
            if(order != null) {
                throw new BizException(-1, "该用户已购买此视频，不需要重复下单");
            }

            videoOrder.setOutTradeNo(UUID.randomUUID().toString());
            videoOrder.setCreateTime(new Date());
            videoOrder.setVideoImg(video.getCoverImg());
            videoOrder.setVideoTitle(video.getTitle());
            videoOrder.setTotalFee(video.getPrice());
            videoOrder.setState(1);


            // 新增订单
            if(StringUtils.isNotBlank(videoOrder.getOutTradeNo())
                    && StringUtils.isNotBlank(videoOrder.getVideoTitle())
                    && videoOrder.getVideoId() != null && videoOrder.getUserId() != null) {
                Integer flag = videoOrderMapper.add(videoOrder);
                if(flag == 1) {
                    Episode episode = episodeService.getFirstEpisodeByVideoId(videoOrder.getVideoId());
                    PlayRecord playRecord = new PlayRecord();
                    playRecord.setEpisodeId(episode.getId());
                    playRecord.setVideoId(videoOrder.getVideoId());
                    playRecord.setUserId(videoOrder.getUserId());
                    flag = playRecordService.save(playRecord);
                }
                return flag;
            } else {
                throw new BizException(-1, "新增订单基本数据不足");
            }
        }
        return 0;
    }
}
