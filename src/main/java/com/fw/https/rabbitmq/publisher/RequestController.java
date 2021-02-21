package com.fw.https.rabbitmq.publisher;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
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
import com.fw.https.request.wrapper.RequestWrapper;
import com.fw.https.util.HttpUtil;

@RestController
@ControllerAdvice
@Async("asyncExec")
//@RequestMapping("/query")
public class RequestController {
	
	@Autowired
	private RabbitTemplate template;
	
	@PostMapping("/")
	public String getHttpsPostResponse(@RequestParam(value="clientId", required=true) String clientId, @RequestParam(value="url", required=true) String url, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
		return addRequestToQueue(clientId,url,httpServletRequest);
	}
	
	@GetMapping("/")
	public String getHttpsGetResponse(@RequestParam(value="clientId", required=true) String clientId, @RequestParam(value="url", required=true) String url, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
		return addRequestToQueue(clientId,url,httpServletRequest);
	}
	
	private String addRequestToQueue(String clientId, String url, HttpServletRequest httpServletRequest) throws IOException {
		String requestCorrelationId = UUID.randomUUID().toString();
		String requestBody = new RequestWrapper(httpServletRequest).getBody();
		
		ClientQuery clientQuery = new ClientQuery();
		clientQuery.setClientId(clientId);
		clientQuery.setUrl(url);
		clientQuery.setRequestBody(requestBody);
		clientQuery.setRequestId(requestCorrelationId);
		
		System.out.println("requestBody : "+requestBody);
		
		RequestStatus requestStatus = new RequestStatus();
		requestStatus.setRequestId(requestCorrelationId);
		requestStatus.setTimestamp(System.currentTimeMillis());
		requestStatus.setStatus("In process");
		requestStatus.setMessage("Request query has been processed successfully for "+clientQuery.getUrl()+" app!!!!");
		
		// adding the requests to the queue to be consumed by the hosts
		template.convertAndSend(QueueProperties.EXCHANGE, QueueProperties.ROUTING_KEY, requestStatus);
		
		return HttpUtil.processRequest(clientQuery,requestStatus);
	}
	
}
