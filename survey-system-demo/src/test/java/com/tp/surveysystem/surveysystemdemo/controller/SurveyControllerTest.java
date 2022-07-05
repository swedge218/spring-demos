package com.tp.surveysystem.surveysystemdemo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.tp.surveysystem.surveysystemdemo.SurveySystemDemoApplication;
import com.tp.surveysystem.surveysystemdemo.configuration.SurveyConfiguration;
import com.tp.surveysystem.surveysystemdemo.model.Question;
import com.tp.surveysystem.surveysystemdemo.service.SurveyService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.is;


//@ExtendWith(SpringExtension.class)
@WebMvcTest(value = SurveyController.class)
class SurveyControllerTest {

	@Autowired
	private MockMvc mockMvc;  //used when unit testing only the controller/web layer
	
	@MockBean
	private SurveyService surveyService;
	
	@MockBean
	private SurveyConfiguration surveyConfiguration;
	
	@WithMockUser(username="user", password = "secret1")
	@Test
	void testGetQuestion() throws Exception {
		Question question1 = new Question("Question1",
		"Largest Country in the World", "Russia", Arrays.asList(
				"India", "Russia", "United States", "China"));

		when(surveyService.retrieveQuestion(Mockito.anyString(), Mockito.anyString())).thenReturn(question1);

		String url = "/survey/Survey1/question/Question1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String questionJSON = new Gson().toJson(question1, Question.class);
		JSONAssert.assertEquals(questionJSON, result.getResponse().getContentAsString(), false);
	}
	
	@WithMockUser(username="user", password = "secret1")
	@Test
	void testGetQuestion_ResultActions() throws Exception {
		Question question1 = new Question("Question1",
		"Largest Country in the World", "Russia", Arrays.asList(
				"India", "Russia", "United States", "China"));

		when(surveyService.retrieveQuestion(Mockito.anyString(), Mockito.anyString())).thenReturn(question1);

		String url = "/survey/Survey1/question/Question1";
		
		//The perform statement returns an object of class ResultActions which the andExpect chain methods can be called on
		mockMvc.perform(MockMvcRequestBuilders.get(url)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.correctAnswer", is("Russia")));
	}
	
	@WithMockUser(username="user", password = "secret1")
	@Test
	void testAddQuestion_ResultActions() throws Exception {
		Question question1 = new Question("Question1",
		"Largest Country in the World", "Russia", Arrays.asList(
				"India", "Russia", "United States", "China"));

		String questionJson = new Gson().toJson(question1, Question.class);
		when(surveyService.addQuestion(Mockito.anyString(), Mockito.any(Question.class))).thenReturn(question1);

		String url = "/survey/Survey1/question";
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(questionJson);
		
		mockMvc.perform(requestBuilder)
				.andExpect(status().isCreated())
				.andExpect(content().json(questionJson));
				
	}
	
	@WithMockUser(username="user", password = "secret1")
	@Test
	void testAddQuestion() throws Exception {
		Question question1 = new Question("Question1",
		"Largest Country in the World", "Russia", Arrays.asList(
				"India", "Russia", "United States", "China"));

		String questionJson = new Gson().toJson(question1, Question.class);
		when(surveyService.addQuestion(Mockito.anyString(), Mockito.any(Question.class))).thenReturn(question1);

		String url = "/survey/Survey1/question";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(questionJson);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(201, response.getStatus());
		JSONAssert.assertEquals(questionJson, result.getResponse().getContentAsString(), false);

	}

}
