package jm.lib.framework.result;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jiming.liu
 */
@Data
public class MorePageResult<T> extends PageResult<T> implements MoreResultable, Serializable {
    /**
     * 可选参数，如果成功了，并且这个参数有值，则跳转到这个 url
     */
    private String nextUrl;

    /**
     * 返回值编码，不一定是 error，所以叫 code
     */
    private Integer code;


    public MorePageResult() {

    }

    public MorePageResult(PageResult<T> result) {
        super();
        this.setMsg(result.getMsg());
        this.setData(result.getData());
        this.setPage(result.getPage());
        this.setPageSize(result.getPageSize());
        this.setRet(result.isRet());
        this.setTotal(result.getTotal());
    }

}
