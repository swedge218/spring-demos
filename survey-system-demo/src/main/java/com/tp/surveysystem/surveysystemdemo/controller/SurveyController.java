package com.tp.surveysystem.surveysystemdemo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tp.surveysystem.surveysystemdemo.configuration.SurveyConfiguration;
import com.tp.surveysystem.surveysystemdemo.model.Question;
import com.tp.surveysystem.surveysystemdemo.service.SurveyService;


@RestController
public class SurveyController {

	@Autowired
	SurveyService surveyService;
	
	@Value("${welcome.message}")
	private String welcomeMessage;
	
	@Autowired
	private SurveyConfiguration surveyConfig;
	
	@GetMapping("/show")
	@ResponseBody
	public String showMessage() {
		return String.format(
				"%s. Survey type: %s, survey title: %s. Min: %d, Max: %d", 
				welcomeMessage, 
				surveyConfig.getType(), 
				surveyConfig.getTitle(),
				surveyConfig.getQuestions().getMin(),
				surveyConfig.getQuestions().getMax()
			);
	}
	
	
	@GetMapping(value="/survey/{id}/questions", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Question> getQuestions(@PathVariable String id) {
		return surveyService.retrieveQuestions(id); 
	}
	
	@GetMapping("/survey/{surveyId}/question/{questionId}")
	public Question getQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
		return surveyService.retrieveQuestion(surveyId, questionId);
	}
	
	@PostMapping("/survey/{surveyId}/question")
	public ResponseEntity<Question> addQuestion(@PathVariable String surveyId,
			@RequestBody Question question) {
		
		Question newQuestion = surveyService.addQuestion(surveyId, question);
		if(newQuestion == null) return ResponseEntity.noContent().build();
		return ResponseEntity.status(201).body(newQuestion);
		
	}
}
