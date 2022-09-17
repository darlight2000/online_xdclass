package site.isscloud.xdclass.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import site.isscloud.xdclass.utils.JUtils;
import site.isscloud.xdclass.utils.JsonData;

/**
 * 异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(BizException.class);

    /**
     * 业务异常公共处理方法
     * @param bizException 业务异常
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public JsonData bizExceptionHandle(BizException bizException) {
        logger.error("【业务异常】:" + bizException);
        return JsonData.buildError(bizException.getCode(),"【业务异常】:"+bizException.getMsg());
    }

    /**
     * 空指针异常处理方法
     * @param nullPointerException 空指针异常
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public JsonData nullPointerExceptionHandle(NullPointerException nullPointerException) {
        logger.error("【空指针异常】:" + nullPointerException);
        return JsonData.buildError(-100, "【空指针异常】："+nullPointerException.toString());
    }

    /**
     * 未识别异常处理方法
     * @param exception 未识别异常
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData exceptionHandle(Exception exception) {
        String pre = "【未识别异常】:";
        String msg = exception.getMessage();
        String ExceptionName = JUtils.getType(exception);
//        System.out.println("ExceptionName:"+ExceptionName);

        if (msg.indexOf("Validation") >= -1 && msg.indexOf("argument") >= -1) {
            pre = "【请求参数异常】:";
        }
        logger.error(pre + exception);
        return JsonData.buildError(-1, pre + ExceptionName);
    }


}
