package com.tp.surveysystem.surveysystemdemo.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.tp.surveysystem.surveysystemdemo.SurveySystemDemoApplication;
import com.tp.surveysystem.surveysystemdemo.gateway.HeaderFactory;
import com.tp.surveysystem.surveysystemdemo.gateway.TestGateway;
import com.tp.surveysystem.surveysystemdemo.model.Question;
import com.tp.surveysystem.surveysystemdemo.stub.SurveyDataStub;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes=SurveySystemDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class SurveyControllerIT {

	@LocalServerPort
	private int port;
	
	private TestGateway gateway;

//	@BeforeEach
//	public void initializeGateway() {
//		gateway = new TestGateway(port);
//	}
	
	

	@Test
	public void itCanGetASingleQuestionTest() throws Exception {
		String path = "/survey/Survey1/question/Question1";
		HttpHeaders headers = new HeaderFactory()
								.createBasicAuthenticationHeader("user", "secret1")
								.setJSONAccept()
								.build();

		//		System.out.println("HEADER STRING " + headers.toString());
		
		gateway = new TestGateway(port);
		
		ResponseEntity<String> response = gateway.callEndpoint(path, headers);
		
		String expected = new SurveyDataStub().getQuestion1Json();
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	
	@Test
	void itCanGetAllQuestionsTest() throws Exception {
		String path = "/survey/Survey1/questions";
		HttpHeaders headers = new HeaderFactory()
				.createBasicAuthenticationHeader("user", "secret1")
				.setJSONAccept()
				.build();
		List<String> expectedList = new SurveyDataStub().getAllData();
		
		gateway = new TestGateway(port);
		ResponseEntity<String> response = gateway.callEndpoint(path, headers);
		
		Type collectionType = new TypeToken<List<JsonObject>>(){}.getType();
		List<JsonObject> actualArray = new Gson().fromJson(response.getBody(), collectionType);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedList.size(), actualArray.size());
		for(int i=0; i < expectedList.size(); i++) {
			JSONAssert.assertEquals(expectedList.get(i), actualArray.get(i).toString(), true);
		}
	}
	
	@Test
	void itCanGetAllQuestionsTest2() throws Exception {
		String path = "/survey/Survey1/questions";
		HttpHeaders headers = new HeaderFactory()
				.createBasicAuthenticationHeader("user", "secret1")
				.setJSONAccept()
				.build();
		List<String> expectedList = new SurveyDataStub().getAllData();
		
		gateway = new TestGateway(port);
		ResponseEntity<String> response = gateway.callEndpoint(path, headers);
		
		Type collectionType = new TypeToken<List<Question>>(){}.getType();
		List<Question> actualArray = new Gson().fromJson(response.getBody(), collectionType);
		Question sampleQuestion = new Gson().fromJson(expectedList.get(0), Question.class);
		System.out.println("Surveyyy: " + actualArray.get(0).toString());
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedList.size(), actualArray.size());
        assertTrue(actualArray.contains(sampleQuestion));
	}
	
	@Test
    public void retrieveSurveyQuestions() throws Exception {
		
		HttpHeaders headers = new HeaderFactory()
				.createBasicAuthenticationHeader("user", "secret1")
				.setJSONAccept()
				.build();
		MediaType[] mediaTypes = {MediaType.APPLICATION_JSON};
		headers.setAccept(Arrays.asList(mediaTypes));
		
		
		ResponseEntity<List<Question>> response = new RestTemplate().exchange(
                "http://localhost:" + port + "/survey/Survey1/questions/", HttpMethod.GET,
                new HttpEntity<String>("DUMMY_DOESNT_MATTER", headers),
                new ParameterizedTypeReference<List<Question>>() {
                });

        Question sampleQuestion = new Question("Question1",
                "Largest Country in the World", "Russia", Arrays.asList(
                        "India", "Russia", "United States", "China"));

        assertTrue(response.getBody().contains(sampleQuestion));
    }
	
	//Post
	@Test
    public void createSurveyQuestion() throws Exception {
		String path = "/survey/Survey1/question";
        Question question = new Question("vv", "Smallest Number",
                "1", Arrays.asList("1", "2", "3", "4"));
        
        String questionJson = new Gson().toJson(question);
        HttpHeaders headers = new HeaderFactory()
				.createBasicAuthenticationHeader("user", "secret1")
				.setJSONAccept()
				.setJSONContentType()
				.build();
        gateway = new TestGateway(port);
        ResponseEntity<String> response = gateway.callPostEndpoint(path, questionJson, headers);
        
        Question newQuestion = new Gson().fromJson(response.getBody(), Question.class);
        
        assertEquals(201, response.getStatusCodeValue());
        assertNotEquals(question.getId(), newQuestion.getId());
        assertEquals(question.getDescription(), newQuestion.getDescription());
        assertArrayEquals(question.getOptions().toArray(), newQuestion.getOptions().toArray());
    }


}
