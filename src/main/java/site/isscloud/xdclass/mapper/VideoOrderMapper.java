package site.isscloud.xdclass.mapper;

import org.apache.ibatis.annotations.Param;
import site.isscloud.xdclass.model.entity.VideoOrder;

import java.util.List;

public interface VideoOrderMapper {
    List<VideoOrder> selectVideoOrdersByUserId(@Param("userId")Integer userId);
    Integer add(VideoOrder videoOrder);
    VideoOrder getVideoOrderById(@Param("id") Integer id);
    VideoOrder getVideoOrderByUserIdAndVideoIdAndState(@Param("videoId") Integer videoId, @Param("userId")Integer userId,@Param("state") Integer state);
}
