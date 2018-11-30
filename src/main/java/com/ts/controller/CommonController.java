package com.ts.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ts.service.ICommonService;
import com.ts.service.ICommonService;
import com.ts.utils.ValidationUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(methods = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
@Api(value = "工具类接口", description = "非业务类,工具性接口等")
@RestController
@RequestMapping("/ydj")
public class CommonController {

    @Autowired
    private ICommonService commonService;

    @ApiOperation(value = "配置信息(appType:[0#android,1#ios];terminal:[1#用户端;2#业务员端;])",notes="{\"appType\":\"1\",\"terminal\":\"2\"}")
    @RequestMapping(value = "/configInfo", method = RequestMethod.POST)
	public String getVersionInfo(@RequestBody String postData){
		JSONObject postJson = JSON.parseObject(postData);
		String appType = postJson.getString("appType");
		int terminal = postJson.getIntValue("terminal");
		ValidationUtil.notBlank("appType", appType, "app类型不能为空");
		ValidationUtil.contain("appType", appType, new String[]{"0","1"}, "APP类型不正确");
		return ValidationUtil.successReturn(commonService.configInfo(Integer.valueOf(appType), terminal));
	}
    
   

}
