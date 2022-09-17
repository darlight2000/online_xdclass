package site.isscloud.xdclass.mapper;

import org.apache.ibatis.annotations.Param;
import site.isscloud.xdclass.model.entity.Episode;

import java.util.List;

public interface EpisodeMapper {
    List<Episode> selectEpisodesByChapterId(@Param(value = "chapterId") Integer chapterId);
    List<Episode> selectEpisodesByVideoId(@Param(value = "videoId") Integer videoId);
    Episode getFirstEpisodeByVideoId(@Param(value="videoId") Integer videoId);
}
