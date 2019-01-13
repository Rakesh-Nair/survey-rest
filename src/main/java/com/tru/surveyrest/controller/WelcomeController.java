package com.tru.surveyrest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tru.surveyrest.configuration.BasicConfiguration;
import com.tru.surveyrest.service.WelcomeService;

@RestController
public class WelcomeController {
	
	@Autowired
	private WelcomeService service;
	
	@Autowired
	private BasicConfiguration basicConfiguration;
	
	@Value("${welcome.message}")
	private String welcomeMessage;
	
	@RequestMapping("/welcome")
	public String welcome() {
		//return service.retrieveWelcomeMessage();
		return welcomeMessage;
	}
	
	@RequestMapping("/dynamic-configuration")
	public Map dynamicConfig() {
		//return service.retrieveWelcomeMessage();
		Map map = new HashMap();
		map.put("message", basicConfiguration.getMessage());
		map.put("number", basicConfiguration.getNumber());
		map.put("value", basicConfiguration.isValue());
		
		return map;
		
	}

}
