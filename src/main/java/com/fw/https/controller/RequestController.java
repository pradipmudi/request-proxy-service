package com.fw.https.controller;

import java.io.IOException;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fw.https.model.ClientQuery;
import com.fw.https.util.HttpUtil;

@RestController
@ControllerAdvice
@Async("asyncExec")
public class RequestController {
	
	@RequestMapping(
		    value = "/response", 
		    method = RequestMethod.GET)
	public String getHttpsGetResponse(@RequestParam(value="q", required=false) String query, @RequestParam(value="client", required=false) String clientID , HttpServletResponse response) throws IOException {
		ClientQuery clientQuery = new ClientQuery();
		clientQuery.setClientId(clientID);
		clientQuery.setQuery(query);
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		return HttpUtil.processRequest(clientQuery);
	}
	
	
	@RequestMapping(
		    value = "/response", 
		    method = RequestMethod.POST,
		    consumes = "application/json")
	public String getHttpsPostResponse(@RequestBody ClientQuery clientQuery,HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		return HttpUtil.processRequest(clientQuery);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Callable<String> getFoobar() throws InterruptedException {
	    return new Callable<String>() {
	        @Override
	        public String call() throws Exception {
	            Thread.sleep(8000); //this will cause a timeout
	            return "foobar";
	        }
	    };
	}
	

}
