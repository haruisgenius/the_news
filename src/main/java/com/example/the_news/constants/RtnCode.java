package com.example.the_news.constants;

public enum RtnCode {
	
	SUCCESSFUL("200", "Successful!!"),
	CANNOT_EMPTY("404", "Input is empty!"),
	NOT_FOUND("404", "Not found!"),
	INCORRECT("401", "Incorrect requests!"),
	DATA_EXISTED("404", "Data is existed!");  // 已存在
	

	private String code;
	
	private String message;

	private RtnCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
