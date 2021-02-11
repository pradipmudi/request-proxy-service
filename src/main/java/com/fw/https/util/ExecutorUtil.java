package com.fw.https.util;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ExecutorUtil {
	@Bean(name="asyncExec")
	public Executor asyncExecutor()
	{
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(3);
	    executor.setMaxPoolSize(3);
	    executor.setQueueCapacity(50);
	    executor.setThreadNamePrefix("AsynchThread-");
	    executor.setAllowCoreThreadTimeOut(true);
	    executor.setKeepAliveSeconds(5);
	    executor.initialize();

	    return executor;
	}

}
