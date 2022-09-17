package site.isscloud.xdclass.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import site.isscloud.xdclass.model.entity.Episode;

import java.util.List;

@Service
public interface EpisodeService {
    Episode getFirstEpisodeByVideoId(Integer videoId);
    List<Episode> selectEpisodesByVideoId(Integer videoId);
    List<Episode> selectEpisodesByChapterId(Integer chapterId);
}
