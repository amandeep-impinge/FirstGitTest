package com.amsterdamworldwide.olmeca.model;

public class IEHttpResponse {
	
	int httpStatusCode;
	String response;
	
	
	public int getHttpStatusCode() {
		return httpStatusCode;
	}
	
	
	public String getResponse() {
		return response;
	}
	
	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
	
	public void setResponse(String response) {
		this.response = response;
	}
	

}
