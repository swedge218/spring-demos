package com.tp.surveysystem.surveysystemdemo.model.mapper;

import java.util.HashMap;
import java.util.List;

import com.tp.surveysystem.surveysystemdemo.model.Question;

public class QuestionMapper {

	public HashMap<String, String> toMap(Question q) {
		return new HashMap<String, String>(){{
			this.put("id", q.getId());
			this.put("description", q.getDescription());
			this.put("correctAnswer", q.getCorrectAnswer());
			this.put("options", q.getOptions().toString());
		}};
	}
}