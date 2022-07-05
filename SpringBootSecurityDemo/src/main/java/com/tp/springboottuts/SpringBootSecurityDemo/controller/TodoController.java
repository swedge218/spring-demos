package com.tp.springboottuts.SpringBootSecurityDemo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tp.springboottuts.SpringBootSecurityDemo.model.Todo;
import com.tp.springboottuts.SpringBootSecurityDemo.security.AuthUtils;
import com.tp.springboottuts.SpringBootSecurityDemo.security.IAuthUtils;
import com.tp.springboottuts.SpringBootSecurityDemo.service.TodoService;


@Controller
public class TodoController {

	@Autowired
	TodoService todoService;
	
	@Autowired
	IAuthUtils authUtils;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	
	@RequestMapping(value="/list-todos", method=RequestMethod.GET)
	public String showTodos(ModelMap model) {
		String username = authUtils.getLoggedInUserName(); 
		model.put("todos", todoService.retrieveTodos(username));
		model.put("name", username);
		return "list-todos";
	}


//	private String getLoggedInUserName(ModelMap model) {
//		return (String) model.get("name");
//	}
	
	@RequestMapping(value="/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model){
		model.addAttribute("todo", new Todo(0, authUtils.getLoggedInUserName(), "Default Desc",
				new Date(), false));
		return "todo";
	}
	
	@RequestMapping(value="/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) throws Exception{
		if(id==1) {
			System.out.println("USER ID " + authUtils.getLoggedInUserName());
			throw new Exception("Cannot delete that record");
		}
			
		todoService.deleteTodo(id);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todoService.retrieveTodo(id);
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}
		
		todo.setUser(authUtils.getLoggedInUserName());
		
		todoService.updateTodo(todo);

		return "redirect:/list-todos";
	}

	
	@RequestMapping(value="/add-todo", method=RequestMethod.POST)
	public String addTodo(ModelMap model,@Valid Todo todo, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "todo";
		}
		
		todoService.addTodo(authUtils.getLoggedInUserName(), todo.getDesc(), todo.getTargetDate(), false);
		return "redirect:/list-todos";
	}
	
	
}
