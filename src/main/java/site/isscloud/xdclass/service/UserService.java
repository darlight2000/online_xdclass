package site.isscloud.xdclass.service;

import org.springframework.util.MultiValueMap;
import site.isscloud.xdclass.model.entity.User;

public interface UserService {
    User getUserByPhoneAndPwd (String phone,String pwd);
    User getUserById(Integer id);
    Integer save(User user);
    User parseToUser(MultiValueMap<String, String> userInfo);
}
