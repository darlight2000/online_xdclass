package site.isscloud.xdclass.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VideoOrderRequest {

    private Integer id;
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    private Integer state;
    @JsonProperty("total_fee")
    private Integer totalFee;
    @JsonProperty("video_id")
    @NotNull
    private Integer videoId;
    @JsonProperty("user_id")
    @NotNull
    private Integer userId;
    @JsonProperty("video_title")
    private String videoTitle;
    @JsonProperty("video_img")
    private String videoImg;
}
