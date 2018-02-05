package com.crm.manager.common.exception;

/**
 * Description:自定义Exception,主要是为了返回JSON信息
 *
 * @author chen
 * @create 2018-01-27
 **/
public class ApiException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2591646516742720560L;
	
	//成功code
	public static final int CODE_SUCCESS = 200;
	//失败code
	public static final int CODE_FAILURED = 500;
	
	private int code; // 异常对应的返回码
	private String message; // 异常对应的描述信息
	
	public ApiException() {
		super();
	}

	public ApiException(String message) {
		super(message);
		this.message = message;
	}
	
	public ApiException(int code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public ApiException(String message, Exception ex) {
		super(message, ex);
	}
	
	public ApiException(int code, String message, Exception ex) {
		super(message, ex);
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
