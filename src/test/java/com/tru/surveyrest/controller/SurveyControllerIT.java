package com.tru.surveyrest.controller;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.tru.surveyrest.SurveyRestApplication;
import com.tru.surveyrest.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SurveyRestApplication.class,
		webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {
	
	@LocalServerPort
	private int port;
	
	
	
	/*
	 * @Test public void TestJsonAssert() throws JSONException { 
	 * String actual ="{id:1,name:Ranga}";
	 * JSONAssert.assertEquals("{id:2}", actual, false); }
	 */

	@Test
	public void testGet() throws JSONException {
		
		String Test1 = "/surveys/Survey1/questions/Question1";
		String URL = "http://localhost:"+port+Test1;
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		
		//String output = restTemplate.getForObject(URL, String.class);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity entity = new HttpEntity<String>(null,headers);
		
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
		
		System.out.println("Body is "+response.getBody());
		//assertTrue(response.getBody().contains("\"id\":\"Question1\""));
		
		
		String expected = 
		"{id:Question1,description:Largest Country in the World,correctAnswer:Russia,}";
		
		JSONAssert.assertEquals("{id:Question1,description:'Largest Country in the World',correctAnswer:Russia}", response.getBody(), false);
		
		
	}
	
	@Test
	public void retrieveSampleQuestions() {
		
		String Test2 = "/surveys/Survey1/questions";
		String URL = "http://localhost:"+port+Test2;
		TestRestTemplate template = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity entity = new HttpEntity<String>(null,headers);
		ResponseEntity<List<Question>> response = template.exchange(URL, HttpMethod.GET, entity, 
				new ParameterizedTypeReference<List<Question>>() {
		});
		
		Question sampleQuestion = new Question("Question1",
				"Largest Country in the World", "Russia", Arrays.asList(
						"India", "Russia", "United States", "China"));
		
		assertTrue(response.getBody().contains(sampleQuestion));
		
		
	}
	
	@Test
	public void postSampleQuestions() {
		
		String Test3 = "/surveys/Survey1/questions";
		String URL = "http://localhost:"+port+Test3;
		TestRestTemplate template = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		Question sampleQuestion = new Question("Testing",
				"Largest Country in the World", "Russia", Arrays.asList(
						"India", "Russia", "United States", "China"));
		HttpEntity entity = new HttpEntity<Question>(sampleQuestion,headers);
		
		ResponseEntity<String> response = template.exchange(URL, HttpMethod.POST, entity, String.class);
		
		String actual =  response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		System.out.println("Actual URL is "+actual);
		assertTrue(actual.contains("/surveys/Survey1/questions"));
		
		
	}

}
