package site.isscloud.xdclass.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterRequest {
    @NotNull
    private String name;
    @NotNull
    private String phone;
    @NotNull
    private String pwd;
}
