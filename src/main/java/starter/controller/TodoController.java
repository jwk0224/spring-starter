package starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import starter.service.TodoService;
import starter.vo.TodoVO;

@Controller
@RequestMapping("/todo")
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
	@GetMapping("/list")
	public ModelAndView readTodos() {
		return new ModelAndView("todos").addObject("todos", todoService.readTodos());
	}
	
	@GetMapping("/list/{todoId}")
	public ModelAndView readTodo(@PathVariable int todoId) {
		return new ModelAndView("todo").addObject("todo", todoService.readTodo(todoId));
	}
	
	@PostMapping("/create")
	public String createTodo(TodoVO todo) {
		todoService.createTodo(todo);
		return "ok";
	}
	
	@PostMapping("/update/{todoId}")
	public String updateTodo(@PathVariable int todoId, TodoVO todo) {
		todo.setTodoId(todoId);
		todoService.updateTodo(todo);
		return "ok";
	}
	
	@GetMapping("/delete/{todoId}")
	public String deleteTodo(@PathVariable int todoId) {
		todoService.deleteTodo(todoId);
		return "ok";
	}
}