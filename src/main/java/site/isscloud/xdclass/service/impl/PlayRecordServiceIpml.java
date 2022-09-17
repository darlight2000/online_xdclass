package site.isscloud.xdclass.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.isscloud.xdclass.exception.BizException;
import site.isscloud.xdclass.mapper.PlayRecordMapper;
import site.isscloud.xdclass.model.entity.PlayRecord;
import site.isscloud.xdclass.service.PlayRecordService;

import java.util.Date;

@Service
public class PlayRecordServiceIpml implements PlayRecordService {

    @Autowired
    private PlayRecordMapper playRecordMapper;

    @Override
    public PlayRecord getPlayRecordByUserIdAndVideoId(Integer videoId, Integer userId) {
        return playRecordMapper.getPlayRecordByUserIdAndVideoId(videoId,userId);
    }

    @Override
    public PlayRecord getPlayRecordById(Integer id) {
        return playRecordMapper.getPlayRecordById(id);
    }

    @Override
    public Integer save(PlayRecord playRecord) {
        Integer orderId = playRecord.getId();
        PlayRecord record = playRecordMapper.getPlayRecordByUserIdAndVideoId(playRecord.getVideoId(),playRecord.getUserId());
        if( orderId != null && orderId > 0) {
            // 修改订单
        } else {
            if(record != null) {
                throw new BizException(-1, "该用户存在此视频播放清单");
            }
            playRecord.setCreateTime(new Date());
            playRecord.setCurrentNum(0);

            // 新增订单
            if(playRecord.getVideoId() != null && playRecord.getUserId() != null) {
                return playRecordMapper.add(playRecord);
            } else {
                throw new BizException(-1, "新增播放记录参数信息不足");
            }
        }
        return 0;
    }
}
