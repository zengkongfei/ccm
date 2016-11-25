package com.ccm.api.common.exception;

/**
 * 统一拦截Exception抛出自定义异常类
 */
public class IsSendEmailException extends RuntimeException {

	private static final long serialVersionUID = -2086758504212510611L;

	public IsSendEmailException() {
		super();
	}

	public IsSendEmailException(String message) {
		super(message);
	}

	public IsSendEmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public IsSendEmailException(Throwable cause) {
		super(cause);
	}

	public IsSendEmailException(Exception e) {
		super(e);
	}

}
