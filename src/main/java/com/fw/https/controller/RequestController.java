package com.fw.https.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

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
	

}
