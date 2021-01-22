package com.fw.https.queue.manager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

import com.fw.https.model.RequestStatus;

public class QueueManager{
	public static Map<String,Queue<RequestStatus>> requestQueueMap = new HashMap<>();
	
	public static boolean addNewRequest(RequestStatus requestStatus, String clientID) {
		Queue<RequestStatus> requestQueue = new LinkedList<>();
		if(requestQueueMap.containsKey(clientID)) {
			
			requestQueue = requestQueueMap.get(clientID);
			if(requestStatus.getTimestamp()-requestQueue.peek().getTimestamp() >= 60000) {
				requestQueue.poll();
			}
			
			if(requestQueue.size()<50){
				requestQueue.add(requestStatus);
				requestQueueMap.put(clientID, requestQueue);
				return true;
			}
			return false;
		}
		requestQueue.add(requestStatus);
		requestQueueMap.put(clientID, requestQueue);
		return true;
	}



}
