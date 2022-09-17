package site.isscloud.xdclass.mapper;

import org.apache.ibatis.annotations.Param;
import site.isscloud.xdclass.model.entity.User;

public interface UserMapper {
    User getUserByPhoneAndPwd(@Param(value = "phone") String phone, @Param(value = "pwd") String pwd);
    User getUserById(@Param(value = "id") Integer id);
    Integer add(User user);
}
