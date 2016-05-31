package jm.lib.framework.result;

import java.io.Serializable;

/**
 * Created by jiming.liu on 2015/3/3.
 */
public class DataResult<T> extends Result implements Serializable {
    private T data;


    public static <T> DataResult<T> fail(String message, T data) {
        return new DataResult<>(false, message, data);
    }
    public static <T> DataResult<T> success(String message, T data) {
        return new DataResult<>(true, message, data);
    }

    public DataResult() {
        super();
    }

    public DataResult(boolean ret, String message, T data) {
        super(ret, message);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
