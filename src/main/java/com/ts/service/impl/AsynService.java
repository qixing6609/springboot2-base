package com.ts.service.impl;


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class AsynService {
	
	
	@Async
	public void sendSmsToCustom(String phoneA,String phoneB,String outId,String res){
		
	}

}
