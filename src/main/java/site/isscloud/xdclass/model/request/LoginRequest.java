package site.isscloud.xdclass.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 登录请求对象
 */
@Data
public class LoginRequest {
    @NotNull
    private String phone;
    @NotNull
    private String pwd;
}
