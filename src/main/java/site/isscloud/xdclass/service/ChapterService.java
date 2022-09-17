package site.isscloud.xdclass.service;

import site.isscloud.xdclass.model.entity.Chapter;

import java.util.List;

public interface ChapterService {
    /**
     * 根据changer的id获取Chapter对象，lazy
     * @param id chapter的id
     * @return chapter对象
     */
    Chapter getChapterDetailByIdLazy(Integer id, Integer depth);

    /**
     * 根据video的id获取Chapter的列表，lazy
     * @param videoId video的id
     * @param depth 层级 1：仅chapters 2:包含episode
     * @return
     */
    List<Chapter> selectChaptersDetailByVideoIdLazy(Integer videoId, Integer depth);
}
