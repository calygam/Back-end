package com.calygam.back.sucesshandlers;

public class ApiSucessHandler<T> {
	private Boolean responseOk;
	private String responseMsg;
	private T responseData;
	
    public ApiSucessHandler(boolean responseOk, String responseMsg, T responseData) {
        this.responseOk = responseOk;
        this.responseMsg = responseMsg;
        this.responseData = responseData;
    }

	public Boolean getResponseOk() {
		return responseOk;
	}

	public void setResponseOk(Boolean responseOk) {
		this.responseOk = responseOk;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public T getResponseData() {
		return responseData;
	}

	public void setResponseData(T responseData) {
		this.responseData = responseData;
	}
    
    
    
}
