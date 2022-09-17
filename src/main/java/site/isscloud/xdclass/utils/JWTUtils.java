package site.isscloud.xdclass.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import site.isscloud.xdclass.model.entity.User;

import java.util.Date;

/**
 * JWT 工具类
 */
public class JWTUtils {

    /**
     * 过期时间，一周
     */
    private static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;

    /**
     * 密钥
     */
    private static final String SECRET = "xdclass.isscloud.site";

    /**
     * 前缀
     */
    private static final String TOKEN_PREFIX = "xdclass";

    /**
     * 主题
     */
    private static final String SUBJECT = "xdclass";

    /**
     * 根据⽤用户信息，⽣生成令牌 * @param user
     * @return
     */
    public static String geneJsonWebToken(User user){
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("head_img",user.getHeadImg()==null?"":user.getHeadImg())
                .claim("id",user.getId())
                .claim("name",user.getName())
                .claim("phone",user.getPhone())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256,SECRET).compact();
        token = TOKEN_PREFIX + token;
        return token;
    }

    /**
     * 校验token的⽅方法
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {
        try{
            final  Claims claims = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX,"")).getBody();
            return claims;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
