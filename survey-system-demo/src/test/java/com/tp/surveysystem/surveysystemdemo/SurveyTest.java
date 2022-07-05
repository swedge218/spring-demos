package com.tp.surveysystem.surveysystemdemo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tp.surveysystem.surveysystemdemo.model.Question;
import com.tp.surveysystem.surveysystemdemo.model.Survey;
import com.tp.surveysystem.surveysystemdemo.service.SurveyService;

@ExtendWith(MockitoExtension.class)
class SurveyTest {

	@Mock
	Survey surveyMock;
	
	@InjectMocks
	SurveyService surveyService;
	
	@Test
	void testAddQuestion() {
//		List<String> options = new ArrayList<String>();
//		options.add("Option1");
//		options.add("Option2");
//		options.add("Option3");
//		options.add("correctAnswer");
//		
//		 Question question = new Question(
//				 "testQuestion",
//				 "My Test Question",
//				 "correctAnswer",
//				 options
//			);
		 
//		List<Question> existingQuestions = surveyMock.getQuestions();
//		 
//		when(surveyMock.getQuestions()).thenReturn(new ArrayList<Question>());
//		assertEquals(question.toStringNoId(), 
//					surveyService.addQuestion("Survey1", question).toStringNoId());
	}

}
