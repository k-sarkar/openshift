package com.os.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenshiftController {

	@GetMapping("/user")
	public String getUser() {
		return "Koushik Sarkar";
	}
}
