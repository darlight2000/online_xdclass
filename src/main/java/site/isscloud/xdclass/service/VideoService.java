package site.isscloud.xdclass.service;

import site.isscloud.xdclass.model.entity.Video;
import site.isscloud.xdclass.model.entity.VideoBanner;

import java.util.List;

public interface VideoService {
    List<Video> listVideo();
    List<VideoBanner> listIndexBanner();
    Video getVideoDetailByIdLazy(Integer id, Integer depth);
}
