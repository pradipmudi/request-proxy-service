package com.fw.https;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fw.https.util.HttpUtil;

@SpringBootApplication
@RestController

public class RequestProxyServiceApplication {
	int count = 0;
	
	@GetMapping("/response")
	public String getMessage(@RequestParam(value="q", required=false) String q, @RequestParam(value="client", required=false) String clientID ) throws IOException {
		System.out.println(q+" "+clientID);
		return HttpUtil.processRequest(q,clientID);
	}
	
	

	@PostMapping("/response")
	public String getMessage2(@RequestParam(value="q", required=false) String q, @RequestParam(value="client", required=false) String clientID ) throws IOException {
		return HttpUtil.processRequest(q,clientID);
	}

	public static void main(String[] args) {
		SpringApplication.run(RequestProxyServiceApplication.class, args);
	}

}
