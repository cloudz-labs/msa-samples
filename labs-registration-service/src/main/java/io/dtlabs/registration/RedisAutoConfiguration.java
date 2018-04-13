package io.dtlabs.registration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
@Profile({"dev", "stg", "prod"})
public class RedisAutoConfiguration extends AbstractCloudConfig {
	
	@Value("${services.redis.name}")
	private String redisName;
	
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return connectionFactory().redisConnectionFactory(redisName);
	}
	
	@Bean
	public static ConfigureRedisAction configureRedisAction() {
		return ConfigureRedisAction.NO_OP;
	}
	
}
