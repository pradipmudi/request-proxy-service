package com.fw.https;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fw.https.model.ClientQuery;
import com.fw.https.util.HttpUtil;

@SpringBootApplication
@RestController

public class RequestProxyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestProxyServiceApplication.class, args);
	}

}
