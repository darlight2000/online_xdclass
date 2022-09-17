package site.isscloud.xdclass.interceptor;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import site.isscloud.xdclass.utils.JUtils;
import site.isscloud.xdclass.utils.JWTUtils;
import site.isscloud.xdclass.utils.JsonData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 解析token，并将用户信息user_id,name,并request.setAttribute()中
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(token == null) {
            token = request.getParameter("token");
        }

        try{
            if(StringUtils.isNotBlank(token)) {
                Claims claims = JWTUtils.checkJWT(token);
                if(claims == null) {
                    // 告诉登录过期，重新登录
                    JUtils.sendJsonMessage(response, JsonData.buildError("访问此接口需要先登录"));
                }
                Integer id = (Integer) claims.get("id");
                String name = (String) claims.get("name");
                // 注意，通过request.setAttribute()方法将相关信息传递给后面的controller使用
                request.setAttribute("user_id", id);
                request.setAttribute("name", name);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JUtils.sendJsonMessage(response, JsonData.buildError("访问此接口需要先登录"));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
