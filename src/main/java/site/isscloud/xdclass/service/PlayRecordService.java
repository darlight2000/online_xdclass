package site.isscloud.xdclass.service;

import org.apache.ibatis.annotations.Param;
import site.isscloud.xdclass.model.entity.PlayRecord;

public interface PlayRecordService {
    PlayRecord getPlayRecordByUserIdAndVideoId(Integer videoId, Integer userId);
    PlayRecord getPlayRecordById(Integer id);
    Integer save(PlayRecord playRecord);
}
