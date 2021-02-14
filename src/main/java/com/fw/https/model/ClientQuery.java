package com.fw.https.model;

public class ClientQuery {

	String query;
	String requestId;
	String clientId;
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
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
		return "ClientQuery [query=" + query + ", requestId=" + requestId + ", clientId=" + clientId + "]";
	}
}
