package com.branch;

import java.time.Duration;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.branch.model.Branch;
import com.branch.model.QuarterlyReport;

@SpringBootApplication(scanBasePackages = "com.branch")
@ComponentScan(basePackages = "com.branch")
@EnableCaching
public class BranchManagementSystemApplication {
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(); // or use JedisConnectionFactory
	}

	@Bean
	public RedisCacheConfiguration cacheConfiguration() {
		return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(100)); // Set default TTL for
																								// cached entries to 30
																								// seconds
	}

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(cacheConfiguration()).build();
	}
	
	
	@Bean
	public RedisTemplate<String, List<QuarterlyReport>> redisTemplate1(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, List<QuarterlyReport>> templates = new RedisTemplate<>();
		templates.setConnectionFactory(redisConnectionFactory);

		// Set the default serializer for keys and values
		templates.setKeySerializer(new StringRedisSerializer());
		templates.setValueSerializer(new GenericJackson2JsonRedisSerializer());

		return templates;
	}

	@Bean
	public RedisTemplate<String, List<Branch>> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, List<Branch>> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);

		// Set the default serializer for keys and values
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

		return template;
	}

	public static void main(String[] args) {
		SpringApplication.run(BranchManagementSystemApplication.class, args);
	}

}



