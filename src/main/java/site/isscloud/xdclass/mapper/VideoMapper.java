package site.isscloud.xdclass.mapper;

import org.apache.ibatis.annotations.Param;
import site.isscloud.xdclass.model.entity.Video;
import site.isscloud.xdclass.model.entity.VideoBanner;

import java.util.List;
public interface VideoMapper {
    List<Video> listVideo();
    List<VideoBanner> listIndexBanner();
    Video getVideoDetailByIdLazy(@Param(value = "id") Integer id);
}
