package com.os.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenshiftController {
	
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
	public ResponseEntity getHelathCheck() throws IOException {
		
		try {
			FileSystemResource file = new FileSystemResource("/tmp/check");
			BufferedReader br = new BufferedReader(new FileReader(file.getFile()));
			String line;
		    while ((line = br.readLine()) != null) {
		    	if(line.startsWith("health.check")) {
		    		String value = line.substring(line.indexOf("=")+1, line.length());
		    		if(value.equals("false"))
		    			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		    	}
		    		
		    }
		}catch(IOException e) {
			
		}
		return new ResponseEntity(HttpStatus.OK);

	}
	
	@GetMapping("/readiness")
	public ResponseEntity<String> getReadinessCheck() {
		
		try {
			FileSystemResource file = new FileSystemResource("/tmp/check");
			BufferedReader br = new BufferedReader(new FileReader(file.getFile()));
			String line;
		    while ((line = br.readLine()) != null) {
		    	if(line.startsWith("readiness.check")) {
		    		String value = line.substring(line.indexOf("=")+1, line.length());
		    		if(value.equals("false"))
		    			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		    	}
		    		
		    }
		}catch(IOException e) {
			
		}
		return new ResponseEntity<String>(HttpStatus.OK);

	}
	
	
}
