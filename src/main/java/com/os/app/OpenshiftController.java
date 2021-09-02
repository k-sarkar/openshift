package com.os.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenshiftController {
	
	@Value("${message:default message}")
	private String message;

	@GetMapping("/user")
	public String getUser() {
		return "Koushik Sarkar";
	}
	
	@GetMapping("/message")
	public String getGetmessage() {
		return message;
	}
	
}
