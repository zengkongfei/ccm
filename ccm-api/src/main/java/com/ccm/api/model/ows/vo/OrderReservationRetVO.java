package com.ccm.api.model.ows.vo;

/**
 * chinaonline预订接口返回对象
 */
public class OrderReservationRetVO {

	private String resultStatusFlag; // 结果标志 SUCCESS 成功,FAIL 失败， ERR 调用错误

	private String resevationUniqueID; // 统一预订号

	private String errorCode;

	private String errMsg; // 消息

	public static final String RESULT_STATUS_PROCESS = "PROCESSING";

	public static final String RESULT_STATUS_SUCCESS = "SUCCESS";

	public static final String RESULT_STATUS_FAIL = "FAIL";

	public static final String RESULT_STATUS_ERR = "ERR";

	public String getResultStatusFlag() {
		return resultStatusFlag;
	}

	public void setResultStatusFlag(String resultStatusFlag) {
		this.resultStatusFlag = resultStatusFlag;
	}

	public String getResevationUniqueID() {
		return resevationUniqueID;
	}

	public void setResevationUniqueID(String resevationUniqueID) {
		this.resevationUniqueID = resevationUniqueID;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
