package site.isscloud.xdclass.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 *   `url` varchar(256) DEFAULT NULL COMMENT '跳转地址',
 *   `img` varchar(256) DEFAULT NULL COMMENT '图片地址',
 *   `create_time` datetime DEFAULT NULL,
 *   `weight` int(11) DEFAULT NULL COMMENT '数字越小排越前',
 */
@Data
public class VideoBanner implements Serializable {
    private Integer id;
    private String url;
    private String img;
    private Integer weight;
    @JsonProperty("create_time")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
