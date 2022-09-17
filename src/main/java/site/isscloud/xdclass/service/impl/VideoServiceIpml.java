package site.isscloud.xdclass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.isscloud.xdclass.config.CacheKeyManager;
import site.isscloud.xdclass.model.entity.Chapter;
import site.isscloud.xdclass.model.entity.Video;
import site.isscloud.xdclass.model.entity.VideoBanner;
import site.isscloud.xdclass.mapper.VideoMapper;
import site.isscloud.xdclass.service.VideoService;
import site.isscloud.xdclass.utils.BaseCache;

import java.util.ArrayList;
import java.util.List;
@Service
public class VideoServiceIpml implements VideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private BaseCache baseCache;

    @Override
    public List<Video> listVideo() {
        List<Video> list = videoMapper.listVideo();
        System.out.println("从数据库中查找首页视频列表");
        return list;
    }

    @Override
    public List<VideoBanner> listIndexBanner() {
        try{
            Object obj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY,()->{
                List<VideoBanner> list = videoMapper.listIndexBanner();
                System.out.println("从数据库中查找轮播图");
                return list;
            });
            if(obj instanceof List) {
                List<VideoBanner> list = (List<VideoBanner>) obj;
                System.out.println("从缓存中查找轮播图");
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据层级depth和video的id来获得对应的video
     * @param id video的id
     * @param depth 层级，1:仅video, 2:包含Chapter 3:包含episode
     * @return
     */
    @Override
    public Video getVideoDetailByIdLazy(Integer id,Integer depth)
    {
        try{
            Object obj = baseCache.getOneHourCache().get(String.format(CacheKeyManager.VIDEO_DETAIL_KEY,id,depth),()->{
                Video v = videoMapper.getVideoDetailByIdLazy(id);
                Video video = getVideoByDepth(v,depth);
                System.out.println(String.format("从数据库中查找id=%s,depth=%s的Video",id,depth));
                return video;
            });
            if(obj instanceof Video) {
                Video video = (Video) obj;
                System.out.println(String.format("从缓存中查找id=%s,depth=%s的Video",id,depth));
                return video;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将mybatis从数据库中查出的video，根据层级进行输出
     * @param video mybatis从数据库中查出的video
     * @param depth 层级，1:仅video, 2:包含Chapter 3:包含episode
     * @return
     */
    private Video getVideoByDepth(Video video, Integer depth) {
        Video v = video;
        if(v == null) {
            return null;
        }
        int d = 1;
        if(depth != null) {
            d = depth;
        }
        Video myVideo = new Video(v.getId(),v.getTitle(),v.getSummary(),v.getCoverImg(),v.getPrice(),v.getPoint(),v.getCreateTime());
        if(d > 1) {
            List<Chapter> cs = v.getChapterList();
            List<Chapter> myChapters =  new ArrayList<>();
            if(cs != null && cs.size() > 0) {
                Integer finalDepth = d;
                cs.forEach(c -> {
                    Chapter chapter = new Chapter(c.getId(),c.getVideoId(),c.getTitle(),
                            c.getOrdered(),c.getCreateTime());
                    if(finalDepth >2) {
                        chapter.setEpisodeList(c.getEpisodeList());
                    }
                    myChapters.add(chapter);
                });
            }
            myVideo.setChapterList(myChapters);
        }
        return myVideo;
    }
}
