package jm.lib.framework.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiming.liu
 */
@Getter @Setter
public class Result implements Serializable {

    private static final long serialVersionUID = 1580785256161528841L;

    private boolean ret;


    /**
     * 这个信息时展现给最终用户看的
     */
    private String msg;

    /**
     * message for log or system. Not sure how to use it and not display to customer
     */
//    private String msg;

    private Map<String, Object> ext;


    public static Result success() {
        return new Result(true, "success");
    }

    public static Result success(String msg) {
        return new Result(true, msg);
    }

    public static Result fail(String msg) {
        return new Result(false, msg);
    }

    public Result() {
    }

    public Result(boolean ret, String msg) {
        this.ret = ret;
        this.msg = msg;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Result)) return false;

        Result result = (Result) o;

        if (ret != result.ret) return false;
        if (msg != null ? !msg.equals(result.msg) : result.msg != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (ret ? 1 : 0);
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "ret=" + ret +
                ", msg='" + msg + '\'' +
                '}';
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("ret", this.isRet());
        result.put("msg", this.getMsg());
        return result;
    }

}
