package site.isscloud.xdclass.mapper;

import org.apache.ibatis.annotations.Param;
import site.isscloud.xdclass.model.entity.PlayRecord;

public interface PlayRecordMapper {
    PlayRecord getPlayRecordByUserIdAndVideoId(@Param("videoId") Integer videoId, @Param("userId")Integer userId);
    PlayRecord getPlayRecordById(Integer id);
    Integer add(PlayRecord playRecord);
}
