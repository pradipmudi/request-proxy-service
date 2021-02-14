package com.fw.https.rabbitmq.publisher;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fw.https.constants.QueueProperties;
import com.fw.https.model.ClientQuery;
import com.fw.https.model.RequestStatus;
import com.fw.https.util.HttpUtil;

@RestController
@ControllerAdvice
@Async("asyncExec")
@RequestMapping("/query")
public class RequestController {
	
	@Autowired
	private RabbitTemplate template;
	
	@GetMapping("/get/{appName}")
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String getHttpsGetResponse(@PathVariable String appName, @RequestParam(value="client", required=true) String clientID , HttpServletResponse response) throws IOException {
		String requestId = UUID.randomUUID().toString();

		ClientQuery clientQuery = new ClientQuery();
		clientQuery.setClientId(clientID);
		clientQuery.setQuery(appName);
		clientQuery.setRequestId(requestId);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		
		return addRequestToQueue(clientQuery,requestId);
	}

	@PostMapping("/{appName}")
	public String getHttpsPostResponse(@RequestBody ClientQuery clientQuery, @PathVariable String appName, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain"); 
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		
		String requestId = UUID.randomUUID().toString();
		clientQuery.setRequestId(requestId);
		
		return addRequestToQueue(clientQuery,requestId);
	}
	
	private String addRequestToQueue(ClientQuery clientQuery, String requestId) throws IOException {
		RequestStatus requestStatus = new RequestStatus();
		requestStatus.setRequestId(requestId);
		requestStatus.setTimestamp(System.currentTimeMillis());
		requestStatus.setStatus("In process");
		requestStatus.setMessage("Request query has been processed successfully for "+clientQuery.getQuery()+" app!!!!");
		
		// adding the requests to the queue to be consumed by the hosts
		template.convertAndSend(QueueProperties.EXCHANGE, QueueProperties.ROUTING_KEY, requestStatus);
		
		return HttpUtil.processRequest(clientQuery,requestStatus);
		
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
