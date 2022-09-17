package site.isscloud.xdclass.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import site.isscloud.xdclass.model.entity.User;
import site.isscloud.xdclass.model.entity.VideoOrder;
import site.isscloud.xdclass.model.request.LoginRequest;
import site.isscloud.xdclass.service.UserService;
import site.isscloud.xdclass.service.VideoOrderService;
import site.isscloud.xdclass.utils.JUtils;
import site.isscloud.xdclass.utils.JWTUtils;
import site.isscloud.xdclass.utils.JsonData;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private VideoOrderService videoOrderService;

    /**
     * 注册
     * @param user
     * @param level
     * @return
     */
    @PostMapping("register")
    // 注意：@RequestBody是post请求json数据时，需要加的注解
    public JsonData register(@RequestBody User user) {
        user.setPwd(JUtils.MD5(user.getPwd()));
        Integer rows = userService.save(user);
        if (rows == null || rows != 1) {
            JsonData.buildError("注册失败");
        }
        return JsonData.buildSuccess("注册成功");
    }

    @PostMapping("login")
    public JsonData login(@RequestBody LoginRequest loginRequest) {
        String strPwd = JUtils.MD5(loginRequest.getPwd());
        User user = userService.getUserByPhoneAndPwd(loginRequest.getPhone(), strPwd);
        if (user == null) {
            return JsonData.buildError("登录失败，用户名或密码错误");
        }
        String token = JWTUtils.geneJsonWebToken(user);
        Map<String,String> map = new HashMap<>();
        map.put("token", token);
        return JsonData.buildSuccess(map);
    }

    /**
     * 通过用户id获取用户信息，不含pwd
     *
     * @param id 用户id
     * @return
     */
    @GetMapping("{id}")
    public JsonData getUserById(@PathVariable("id") Integer id, HttpServletRequest request) {
        // userId是LoginInterceptor中成功解析token后存入到request中的用户id
        Integer userId = (Integer) request.getAttribute("user_id");
        if(userId.intValue() != id.intValue()) {
            return JsonData.buildError("您无权获取其他用户信息");
        }
        User user = userService.getUserById(id);
        return JsonData.buildSuccess(user);
    }

    @GetMapping("/{id}/orders")
    public JsonData selectVideoOrdersByUserId(@PathVariable("id") Integer id, HttpServletRequest request) {
        // userId是LoginInterceptor中成功解析token后存入到request中的用户id
        Integer userId = (Integer) request.getAttribute("user_id");
        if(userId.intValue() != id.intValue()) {
            return JsonData.buildError("您无权获取其他用户信息");
        }
        List<VideoOrder> list = videoOrderService.selectVideoOrdersByUserId(userId);
        return JsonData.buildSuccess(list);
    }

}
