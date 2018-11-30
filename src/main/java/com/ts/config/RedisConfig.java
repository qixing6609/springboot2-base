package com.ts.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by shan on 16/7/18.
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
	Logger logger = LoggerFactory.getLogger(RedisConfig.class);
	   @Value("${spring.redis.database}")
	    private int database;
	    
	    @Value("${spring.redis.host}")
	    private String host;

	    @Value("${spring.redis.port}")
	    private int port;

	    @Value("${spring.redis.timeout}")
	    private int timeout;

	    @Value("${spring.redis.pool.max-active}")
	    private int maxActive;
	    
	    @Value("${spring.redis.pool.max-idle}")
	    private int maxIdle;
	    
	    @Value("${spring.redis.pool.min-idle}")
	    private int minIdle;

	    @Value("${spring.redis.pool.max-wait}")
	    private long maxWaitMillis;

	    @Value("${spring.redis.password}")
	    private String password;
	    
	    
	    @Bean
	    public RedisConnectionFactory jedisConnectionFactory() {
	        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
	        redisStandaloneConfiguration.setDatabase(database);
	        redisStandaloneConfiguration.setHostName(host);
	        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
	        
	        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
	        //其他配置，可再次扩展
	        return jedisConnectionFactory;
	    }

	    @Bean
	    public JedisPool redisPoolFactory() {
	        logger.info("JedisPool注入成功！！");
	        logger.info("redis地址：{}:{}", host, port);
	        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
	        jedisPoolConfig.setMaxIdle(maxIdle);
	        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

	        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);

	        return jedisPool;
	    }
	    
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		@Bean
	    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
	        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
	        redisTemplate.setConnectionFactory(cf);
	        
	        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
	        ObjectMapper om = new ObjectMapper();
	        om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
	        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
	        jackson2JsonRedisSerializer.setObjectMapper(om);
	        
	        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
	        
	        //key序列化方式
	        redisTemplate.setKeySerializer(redisSerializer);
	        //value序列化
	        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
	        //value hashmap序列化
	        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
	        
	        return redisTemplate;
	    }

//		@Bean
//	    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//	    	
////	        RedisCacheManager cacheManager = new RedisCacheManager(cacheWriter, defaultCacheConfiguration)
	////
////	        //默认超时时间,单位秒
////	        cacheManager.setDefaultExpiration(3000);
////	        //根据缓存名称设置超时时间,0为不超时
////	        Map<String,Long> expires = new ConcurrentHashMap<>();
////	        cacheManager.setExpires(expires);
//			
//			Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//	        ObjectMapper om = new ObjectMapper();
//	        om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
//	        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//	        jackson2JsonRedisSerializer.setObjectMapper(om);
//	        
//	        //初始化一个RedisCacheWriter  
//	        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);  
//	        //设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下注释代码为默认实现  
//	        //ClassLoader loader = this.getClass().getClassLoader();  
//	        //JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(loader);  
//	        //RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer);  
//	        //RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);  
//	        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();  
//	        //设置默认超过期时间是30秒  
//	        defaultCacheConfig.serializeValuesWith(
//	        		RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer)
//	        		).entryTtl(Duration.ofSeconds(300));  
//	        //初始化RedisCacheManager  
//	        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig);  
	//
//	        return cacheManager;
//	    }
		
		
		@Bean
	    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
	        return new RedisCacheManager(
	            RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
	            this.getRedisCacheConfigurationWithTtl(600), // 默认策略，未配置的 key 会使用这个
	            this.getRedisCacheConfigurationMap() // 指定 key 策略
	        );
	    }

	    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
	        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
	        redisCacheConfigurationMap.put("MerchantProductAccess", this.getRedisCacheConfigurationWithTtl(30));
	        redisCacheConfigurationMap.put("UserInfoListAnother", this.getRedisCacheConfigurationWithTtl(180));

	        return redisCacheConfigurationMap;
	    }

	    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
	        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
	        ObjectMapper om = new ObjectMapper();
	        om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
	        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
	        jackson2JsonRedisSerializer.setObjectMapper(om);

	        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
	        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
	            RedisSerializationContext
	                .SerializationPair
	                .fromSerializer(jackson2JsonRedisSerializer)
	        ).entryTtl(Duration.ofSeconds(seconds));

	        return redisCacheConfiguration;
	    }
}
