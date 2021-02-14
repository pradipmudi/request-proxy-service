package com.fw.https.model;

public class RequestStatus {
	String status;
	long timestamp;
	String message;
	String requestId;
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String string) {
		this.status = string;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return "RequestStatus [status=" + status + ", timestamp=" + timestamp + ", message=" + message + ", requestId="
				+ requestId + "]";
	}
}
