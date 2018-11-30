package com.ts.task;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class TaskService {

	private static Logger logger = LoggerFactory.getLogger(TaskService.class);

	
	@Scheduled(cron = "0/30 * * * * ?")
	public void overdueOrder() {
		
	}
	
	
}
