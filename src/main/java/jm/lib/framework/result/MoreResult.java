package jm.lib.framework.result;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jiming.liu on 2015/4/30.
 */
@Data
public class MoreResult extends Result implements MoreResultable, Serializable {
    /**
     * 可选参数，如果成功了，并且这个参数有值，则跳转到这个 url
     */
    private String nextUrl;
    /**
     * 返回值编码，不一定是 error，所以叫 code
     */
    private Integer code;

    public MoreResult() {  }

    public MoreResult(Result result) {
        super(result.isRet(), result.getMsg());
    }


}
