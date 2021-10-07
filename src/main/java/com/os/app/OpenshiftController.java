package com.os.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenshiftController {
	
	static Integer healthCounter = 0;
	
	@Value("${message:default message}")
	private String message;
	
	@Value("${config_message:default config message}")
	private String configMessage;
	
	@Value("${secret_message:default secret message}")
	private String secretMessage;

	@GetMapping("/user")
	public String getUser() {
		return "Updated user Koushik Sarkar";
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
	
	@GetMapping("/health")
	public ResponseEntity getHelathCheck() { 

		if(healthCounter%4==0) {
			healthCounter ++;
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}
		else {
			healthCounter ++;
			return new ResponseEntity(HttpStatus.OK);
		}
		
		

	}
	
	@GetMapping("/readiness/{value}")
	public ResponseEntity<String> getReadinessCheck(@PathVariable String value) {
		
		if(value.equalsIgnoreCase("Y"))
			return new ResponseEntity<String>(HttpStatus.OK);
		else
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);

	}
	
	
}
