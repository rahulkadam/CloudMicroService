package com.example.tutorialclient;

import com.example.tutorialclient.component.TutorialClientChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.client.RestTemplate;


@EnableZuulProxy
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableBinding(Source.class)
@SpringBootApplication
public class TutorialClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorialClientApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Source source() {
		return new Source() {
			@Override
			public MessageChannel output() {
				return null;
			}
		};
	}
}