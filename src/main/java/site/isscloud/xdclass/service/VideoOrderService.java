package site.isscloud.xdclass.service;

import org.apache.ibatis.annotations.Param;
import site.isscloud.xdclass.model.entity.VideoOrder;

import java.util.List;

public interface VideoOrderService {
    List<VideoOrder> selectVideoOrdersByUserId(@Param("userId")Integer userId);
    int save(VideoOrder videoOrder);
    VideoOrder getVideoOrderByUserIdAndVideoId(Integer videoId, Integer userId, Integer state);
    VideoOrder getVideoOrderById(Integer id);
}
