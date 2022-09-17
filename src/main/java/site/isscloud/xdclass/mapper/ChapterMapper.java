package site.isscloud.xdclass.mapper;

import org.apache.ibatis.annotations.Param;
import site.isscloud.xdclass.model.entity.Chapter;

import java.util.List;

public interface ChapterMapper {
    List<Chapter> selectChaptersByVideoIdLazy(@Param(value = "videoId") Integer videoId);
    Chapter getChapterDetailByIdLazy(@Param(value = "id")Integer id);
}
