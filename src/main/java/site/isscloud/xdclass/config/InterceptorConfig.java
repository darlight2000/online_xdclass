package site.isscloud.xdclass.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.isscloud.xdclass.interceptor.CorsInterceptor;
import site.isscloud.xdclass.interceptor.LoginInterceptor;

/**
 * 拦截器配置
 * 不要权限的url /api/v1/pub/
 * 需要登录可以访问的url /api/v1/pri/
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Bean
    CorsInterceptor corsInterceptor() {
        return new CorsInterceptor();
    }

    private final String PREFIX = "/api/v1/pri";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截全部路径，这个跨越的拦截器需要放在最上面
         */
        registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(loginInterceptor()).addPathPatterns(PREFIX+"/*/*/**")
                .excludePathPatterns(PREFIX+"/user/login",PREFIX+"/user/register");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
