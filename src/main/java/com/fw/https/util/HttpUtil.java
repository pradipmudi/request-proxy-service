package com.fw.https.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.fw.https.model.ClientQuery;
import com.fw.https.model.RequestStatus;
import com.fw.https.queue.manager.QueueManager;

public class HttpUtil {

	public static String getRequest(String q) throws IOException {
		q = q.replaceAll(" ", "+");
		URL google = new URL("http://www.google.com/?q="+q);
        URLConnection googleConnection = google.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                googleConnection.getInputStream()));
        String inputLine;

        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) 
            response.append(inputLine);
        in.close();
		
        return response.toString();
	}
	
	public static String processRequest(ClientQuery clientQuery) throws IOException {
		RequestStatus requestStatus = new RequestStatus();
		requestStatus.setTimestamp(System.currentTimeMillis());
		requestStatus.setStatus(true);
		boolean response = QueueManager.addNewRequest(requestStatus,clientQuery.getClientId());
		if(System.currentTimeMillis()-requestStatus.getTimestamp()>5000)
			return "request took more than 5 seconds";
		if(response) {
			return "Accessed by HTTPS protocol....... : "+getRequest(clientQuery.getQuery());
		}
		return "Over 50 requests";
	}
}
