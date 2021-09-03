package com.os.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenshiftController {
	
	@Value("${message:default message}")
	private String message;
	
	@Value("${message:default config message}")
	private String configMessage;
	
	@Value("${message:default secret message}")
	private String secretMessage;

	@GetMapping("/user")
	public String getUser() {
		return "Koushik Sarkar";
	}
	
	@GetMapping("/message")
	public String getGetMessage() {
		return message;
	}
	
	@GetMapping("/config/message")
	public String getGetConfigMessage() {
		return configMessage;
	}
	
	
	@GetMapping("/secret/message")
	public String getGetSecretMessage() {
		return secretMessage;
	}
	
	
}
