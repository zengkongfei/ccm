package com.ccm.api.common.exception;

/**
 * 异常统一处理类:和业务相关的
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -2086758504212510611L;

    private String errKey; // 异常资源key名字编码 可以用模块+功能+关键词 例如 inv.stock.overinv

    private String errMsg; // 异常提示信息,如果为空，将使用资源中的key

    private Object[] parameters;

    public BizException(String errKey) {
        super(new Exception(errKey));
        this.errKey = errKey;

    }

    public BizException(String errKey, String message) {
        super(message);
        this.errKey = errKey;
        this.errMsg = message;
    }

    public BizException(String errKey, String message, Throwable cause) {

        super(cause);
        this.errKey = errKey;
        this.errMsg = errKey;

    }

    public String getErrKey() {
        return errKey;
    }

    public void setErrKey(String errKey) {
        this.errKey = errKey;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Object[] getParameters() {
        return parameters;
    }

}
