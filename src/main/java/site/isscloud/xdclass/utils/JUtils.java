package site.isscloud.xdclass.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.MessageDigest;

/**
 * 通用工具类
 */
public class JUtils {
    /**
     * java 获取对象的数据类型
     * @param object
     * @return
     */
    public static  String getType(Object object){
        String typeName=object.getClass().getName();
        // System.out.println("typeName-------" +typeName);  // 如果传入整数，输出java.lang.Integer
        int length= typeName.lastIndexOf(".");
        // System.out.println("length-------" +length);  // 输出9，lastIndexOf表示最后一次出现的位置，返回的是下标 ，找不到返回-1
        String type =typeName.substring(length+1);  //  substring截取字符串typeName，从下标为length+1开始到最后
        return type;
    }


    /**
     * MD5加密
     * @param data
     * @return
     */
    public static String MD5(String data) {
        try{
            java.security.MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * 响应json数据给前端
     * @param response
     * @param obj
     */
    public static void sendJsonMessage(HttpServletResponse response, Object obj){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(obj));
            writer.close();
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
