package com.ts.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.ts.constant.CacheContext;
import com.ts.mapper.ext.CommonMapper;
import com.ts.service.ICommonService;


@Service
public class CommonServiceImpl implements ICommonService {


    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private AsynService asynService;

    @Override
    public void delCategoryCache(){
        redisTemplate.delete(CacheContext.MEDICINE_CATEGORY);
    }


    @Override
    public Map<String, Object> configInfo(int appType, int terminal){
    	Map<String, Object> data = new HashMap<>();
    	
    	data.put("isShowReg", 1);
    	return data;
    }
    
}
