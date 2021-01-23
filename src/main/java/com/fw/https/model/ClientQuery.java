package com.fw.https.model;

public class ClientQuery {

	String query;
	String clientId;
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	@Override
	public String toString() {
		return "ClientQuery [query=" + query + ", clientId=" + clientId + "]";
	}
}
