package com.fw.https.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fw.https.model.ClientQuery;
import com.fw.https.util.HttpUtil;

@RestController
public class RequestController {
	
	@RequestMapping(
		    value = "/response", 
		    method = RequestMethod.GET)
	@ResponseBody
	public String getHttpsGetResponse(@RequestParam(value="q", required=false) String query, @RequestParam(value="client", required=false) String clientID ) throws IOException {
		ClientQuery clientQuery = new ClientQuery();
		clientQuery.setClientId(clientID);
		clientQuery.setQuery(query);
		
		return HttpUtil.processRequest(clientQuery);
	}
	
	
	@RequestMapping(
		    value = "/response", 
		    method = RequestMethod.POST,
		    consumes = "application/json")
	@ResponseBody
	public String getHttpsPostResponse(@RequestBody ClientQuery clientQuery) throws IOException {
		return HttpUtil.processRequest(clientQuery);
	}

}
