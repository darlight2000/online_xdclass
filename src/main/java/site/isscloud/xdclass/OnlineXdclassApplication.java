package site.isscloud.xdclass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@SpringBootApplication
// 扫描mapper,注意这里的目录一定要写对
@MapperScan("site.isscloud.xdclass.mapper")
// 启动事务
@EnableTransactionManagement
public class OnlineXdclassApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineXdclassApplication.class, args);
	}

}
