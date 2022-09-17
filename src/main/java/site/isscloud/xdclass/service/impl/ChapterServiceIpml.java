package site.isscloud.xdclass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.isscloud.xdclass.config.CacheKeyManager;
import site.isscloud.xdclass.model.entity.Chapter;
import site.isscloud.xdclass.mapper.ChapterMapper;
import site.isscloud.xdclass.model.entity.Video;
import site.isscloud.xdclass.service.ChapterService;
import site.isscloud.xdclass.utils.BaseCache;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterServiceIpml implements ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private BaseCache baseCache;

    /**
     * 根据videoId获取章节详情列表，章节详情是否包含Episode根据depth决定
     * @param videoId video的id
     * @param depth 1：仅chapter 2:包含episode
     * @return
     */
    @Override
    public List<Chapter> selectChaptersDetailByVideoIdLazy(Integer videoId, Integer depth) {
        try{
            Object obj = baseCache.getOneHourCache().get(String.format(CacheKeyManager.VIDEO_CHAPTERS_KEY,videoId,depth),()->{
                List<Chapter> cs = chapterMapper.selectChaptersByVideoIdLazy(videoId);
                List<Chapter> chapters = new ArrayList<>();
                if(cs != null && cs.size() > 0) {
                    cs.forEach(c -> {
                        Chapter chapter = getChapterByDepth(c,depth);
                        chapters.add(chapter);
                    });
                }
                System.out.println(String.format("从数据库中查找video_id=%s,depth=%s的chapters",videoId,depth));
                return chapters;
            });
            if(obj instanceof List) {
                List<Chapter> list = (List<Chapter>) obj;
                System.out.println(String.format("从缓存中查找video_id=%s,depth=%s的chapters",videoId,depth));
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据Chapter的id,以及depth层级，来获得Chapter
     * @param id chapter的id
     * @param depth 层级 1:仅chapter 2:含有episode
     * @return
     */
    @Override
    public Chapter getChapterDetailByIdLazy(Integer id, Integer depth) {
        try{
            Object obj = baseCache.getOneHourCache().get(String.format(CacheKeyManager.CHAPTER_DETAIL_KEY,id,depth),()->{
                Chapter c = chapterMapper.getChapterDetailByIdLazy(id);
                Chapter chapter = getChapterByDepth(c,depth);
                System.out.println(String.format("从数据库中查找id=%s,depth=%s的Chpater",id,depth));
                return chapter;
            });
            if(obj instanceof Chapter) {
                Chapter chapter = (Chapter) obj;
                System.out.println(String.format("从缓存中查找id=%s,depth=%s的Chpater",id,depth));
                return chapter;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据depth来获取对应的chapter对象，即depth=1,则仅chapter对象，depth=2,则含有Episode对象
     * @param chapter
     * @param depth
     * @return
     */
    private Chapter getChapterByDepth(Chapter chapter, Integer depth) {
        Chapter c = chapter;
        if(c == null) {
            return null;
        }
        int d = 1;
        if(depth != null) {
            d = depth;
        }
        Chapter myChapter = new Chapter(c.getId(),c.getVideoId(),c.getTitle(),c.getOrdered(),c.getCreateTime());
        if(d > 1) {
            myChapter.setEpisodeList(c.getEpisodeList());
        }
        return myChapter;
    }
}
