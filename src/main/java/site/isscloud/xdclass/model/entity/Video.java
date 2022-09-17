package site.isscloud.xdclass.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 *   `title` varchar(524) DEFAULT NULL COMMENT '视频标题',
 *   `summary` varchar(1026) DEFAULT NULL COMMENT '概述',
 *   `cover_img` varchar(524) DEFAULT NULL COMMENT '封面图',
 *   `price` int(11) DEFAULT NULL COMMENT '价格,分',
 *   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
 *   `point` double(11,2) DEFAULT '8.70' COMMENT '默认8.7，最高10分',
 */
@JsonIgnoreProperties(value = "handler")
@Data
public class Video implements Serializable {
    private Integer id;
    private String title;
    private String summary;
    @JsonProperty("cover_img")
    private String coverImg;
    private Integer price;
    private Double point;
    @JsonProperty("create_time")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonProperty("chapter_list")
    private List<Chapter> chapterList;

    public Video() {
    }

    public Video(Integer id, String title, String summary, String coverImg, Integer price, Double point, Date createTime) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.coverImg = coverImg;
        this.price = price;
        this.point = point;
        this.createTime = createTime;
    }
}
