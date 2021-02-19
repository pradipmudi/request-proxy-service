package com.fw.https.util;

import java.io.IOException;

import com.fw.https.model.ClientQuery;
import com.fw.https.model.RequestStatus;
import com.fw.https.queue.manager.QueueManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
public class HttpUtil {
	
	

	public static String getRequest(String q) throws IOException {
        
        try (var webClient = new WebClient()) {
        	q = q.replaceAll(" ", "");
        	String url = "https://192.168.0.193:7777/query/"+q;
        	
        	webClient.getOptions().setThrowExceptionOnScriptError(false);
        	webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        	webClient.getOptions().setUseInsecureSSL(true);
            HtmlPage page = webClient.getPage(url);
            WebResponse response = page.getWebResponse();
            String content = response.getContentAsString();

            return content;
        }
		
	}
	
	public static String processRequest(ClientQuery clientQuery, RequestStatus requestStatus) throws IOException {
		
		boolean response = QueueManager.addNewRequest(requestStatus,clientQuery.getClientId());
		
		if(System.currentTimeMillis()-requestStatus.getTimestamp()>5000)
			return "request took more than 5 seconds";
		if(response) {
			return "Success!!! Accessed by HTTPS protocol....... : "+getRequest(clientQuery.getQuery());
		}
		return "Over 50 requests";
	}
}
