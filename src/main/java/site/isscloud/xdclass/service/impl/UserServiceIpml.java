package site.isscloud.xdclass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import site.isscloud.xdclass.model.entity.User;
import site.isscloud.xdclass.exception.BizException;
import site.isscloud.xdclass.mapper.UserMapper;
import site.isscloud.xdclass.service.UserService;
import site.isscloud.xdclass.utils.JUtils;

import java.util.Date;

@Service
public class UserServiceIpml implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserByPhoneAndPwd(String phone,String pwd) {
        return userMapper.getUserByPhoneAndPwd(phone,pwd);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    public Integer save(User user) {
        if (user.getId() == null) {
            if (user.getName() != null && user.getPwd() != null && user.getPhone() != null) {
                User u = userMapper.getUserByPhoneOrName(user.getPhone(), user.getName());
                if (u != null) {
                    throw new BizException(-1, "存在相同用户名或者电话的用户");
                }
                int i = (int)(Math.random()*(20+1));
                user.setHeadImg("https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/"+i+".jpeg");
                user.setCreateTime(new Date());
                return userMapper.add(user);
            } else {
                throw new BizException(-1, "新增用户参数异常");
            }
        }
        return null;
    }

    public User parseToUser(MultiValueMap<String, String> userInfo) {
        MultiValueMap<String, String> u = userInfo;
        User user = new User();
        if (userInfo.containsKey("id")) {
            try {
                user.setId(Integer.parseInt(userInfo.get("id").get(0)));
            } catch (NumberFormatException e) {
                user.setId(null);
            }
        }

        if(userInfo.containsKey("name")) {
            user.setName(userInfo.get("name").get(0));
        }
        // 密码加密存储
        if(userInfo.containsKey("pwd")) {
            user.setPwd(JUtils.MD5(userInfo.get("pwd").get(0)));
        }
        if(userInfo.containsKey("head_img")) {
            user.setHeadImg(userInfo.get("head_img").get(0));
        }
        if(userInfo.containsKey("phone")) {
            user.setPhone(userInfo.get("phone").get(0));
        }

        if (userInfo.containsKey("create_time")) {
            try {
                user.setCreateTime(new Date(Date.parse(userInfo.get("create_time").get(0))));
            } catch (Exception e) {
                user.setCreateTime(new Date());
            }
        } else {
            user.setCreateTime(new Date());
        }
        return user;
    }
}
