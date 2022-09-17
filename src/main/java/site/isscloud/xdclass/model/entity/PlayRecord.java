package site.isscloud.xdclass.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 *   `user_id` int(11) DEFAULT NULL,
 *   `video_id` int(11) DEFAULT NULL,
 *   `current_num` int(11) DEFAULT NULL COMMENT '当前播放第几集',
 *   `episode_id` int(11) DEFAULT NULL COMMENT '当前播放第几集视频id',
 *   `create_time` datetime DEFAULT NULL,
 */
@Data
public class PlayRecord {
    private Integer id;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("video_id")
    private Integer videoId;
    @JsonProperty("current_num")
    private Integer currentNum;
    @JsonProperty("episode_id")
    private Integer episodeId;
    @JsonProperty("create_time")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
