package site.isscloud.xdclass.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 *   `video_id` int(11) DEFAULT NULL COMMENT '视频主键',
 *   `title` varchar(128) DEFAULT NULL COMMENT '章节名称',
 *   `ordered` int(11) DEFAULT NULL COMMENT '章节顺序',
 *   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
 */
@JsonIgnoreProperties(value = "handler")
@Data
public class Chapter {
    private Integer id;
    @JsonProperty("video_id")
    private Integer videoId;
    private String title;
    private Integer ordered;
    @JsonProperty("create_time")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonProperty("episode_list")
    private List<Episode> episodeList;

    public Chapter(Integer id, Integer videoId, String title, Integer ordered, Date createTime) {
        this.id = id;
        this.videoId = videoId;
        this.title = title;
        this.ordered = ordered;
        this.createTime = createTime;
    }
}
