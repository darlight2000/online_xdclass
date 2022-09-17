package site.isscloud.xdclass;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import site.isscloud.xdclass.model.entity.User;
import site.isscloud.xdclass.utils.JWTUtils;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class OnlineXdclassApplicationTests {

	@Test
	public void TestJWT() {
		User user = new User();
		user.setId(1);
		user.setName("dark");
		user.setHeadImg("");
		user.setPhone("123");
		String token = JWTUtils.geneJsonWebToken(user);
		Claims claims = JWTUtils.checkJWT(token);
		System.out.println(claims.get("id")+","+claims.get("name")+","+claims.get("phone"));
	}

}
