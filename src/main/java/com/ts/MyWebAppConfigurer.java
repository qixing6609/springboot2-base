package com.ts;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.ts.interceptor.MyInterceptor;

/**
 * 拦截器配置类
 * 
 * MyWebAppConfigurer<BR>
 * 创建人:liuguangming <BR>
 * 时间：2018年7月26日-下午2:25:53 <BR>
 * @version 2.0
 *
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurationSupport {
	@Autowired
	private Environment env;
	@Autowired
	private MyInterceptor myInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(myInterceptor).addPathPatterns("/ydj/**");
//		registry.addInterceptor(authorizeInterceptor()).addPathPatterns("/ydj-backstage/**");
		super.addInterceptors(registry);
	}
	
	
//	@Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//    registry.addViewController("/").setViewName("index.html");
//    }
	
	/**
	 * 设置静态资源及其系统路径
	 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/ydj/resources/**").addResourceLocations("file:" + env.getProperty("upload.filePath"));
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
    
    
    @Bean
	public HttpMessageConverter<String> responseBodyConverter(){
	StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
	return converter;
	}
    
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	super.configureMessageConverters(converters);

	converters.add(responseBodyConverter());
	}
}
