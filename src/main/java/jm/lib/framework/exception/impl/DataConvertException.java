package jm.lib.framework.exception.impl;

import java.util.Locale;

public class DataConvertException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 5746197082359549770L;

    private Exception exception;
    private String fieldName;
    private Object fieldValue;
    private String message;

    public DataConvertException(String field_name, Object field_value, String message) {
        this.message = message;
        this.fieldName = field_name;
        this.fieldValue =  field_value;
    }

    public DataConvertException(Object fieldvalue) {
        this("", fieldvalue, (Exception)null);
    }

    public DataConvertException(Object fieldvalue, Exception e) {
        this("", fieldvalue, e);
    }
    public DataConvertException(String fieldname, Object fieldvalue, Exception e) {
        //TODO: 要考虑到区分各种已知情况，比如 NumberFormatException 等
        this.fieldName = fieldname;
        this.fieldValue = fieldvalue;
        this.exception = e;
    }

    public Exception getNestedException() {
        return this.exception;
    }

    public String getMessage(Locale l) {
        return this.getNestedException().getMessage();
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return this.message;
    }
}
