package site.isscloud.xdclass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.isscloud.xdclass.mapper.EpisodeMapper;
import site.isscloud.xdclass.model.entity.Episode;
import site.isscloud.xdclass.service.EpisodeService;

import java.util.List;

@Service
public class EpisodeServiceIpml implements EpisodeService {

    @Autowired
    private EpisodeMapper episodeMapper;

    @Override
    public Episode getFirstEpisodeByVideoId(Integer videoId) {
        return episodeMapper.getFirstEpisodeByVideoId(videoId);
    }

    @Override
    public List<Episode> selectEpisodesByVideoId(Integer videoId) {
        return episodeMapper.selectEpisodesByVideoId(videoId);
    }

    @Override
    public List<Episode> selectEpisodesByChapterId(Integer chapterId) {
        return episodeMapper.selectEpisodesByChapterId(chapterId);
    }
}
