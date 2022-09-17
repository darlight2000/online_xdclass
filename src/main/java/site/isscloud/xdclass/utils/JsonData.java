package site.isscloud.xdclass.utils;

public class JsonData {
    private int code;
    private String msg;
    private Object data;

    public JsonData(){};

    public JsonData(Object data) {
        this.data = data;
        this.code = 0;
        this.msg = null;
    }

    public JsonData(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public JsonData(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static JsonData buildSuccess(Object data){
        return new JsonData(data);
    }

    public static JsonData buildSuccess(Object data, String msg){
        JsonData jsonData = new JsonData(0, msg, data);
        return jsonData;
    }

    public static JsonData buildError(String msg){
        return new JsonData(-1,msg);
    }

    public static JsonData buildError(int code, String msg){
        return new JsonData(code,msg);
    }
}
