package com.fw.https.model;

public class ClientQuery {

	String url;
	String requestId;
	String clientId;
	String requestBody;
	
	public String getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	@Override
	public String toString() {
		return "ClientQuery [url=" + url + ", requestId=" + requestId + ", clientId=" + clientId + ", requestBody="
				+ requestBody + "]";
	}
}
