package com.ilan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableDiscoveryClient
@RefreshScope
public class WebFluxConsumer {

	public static void main(String[] args) {
		SpringApplication.run(WebFluxConsumer.class, args);
	}

}
