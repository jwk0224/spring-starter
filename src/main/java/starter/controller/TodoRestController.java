package starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import starter.service.TodoService;
import starter.vo.TodoVO;

@RestController
@RequestMapping("/todos")
public class TodoRestController {
	
	@Autowired
	TodoService todoService;
	
	@GetMapping("")
	public Object readTodos() {
		return todoService.readTodos();
	}
	
	@GetMapping("/{todoId}")
	public Object readUser(@PathVariable int todoId) {
		return todoService.readTodo(todoId);
	}
	
	@PostMapping("")
	public Object createTodo(@RequestBody TodoVO todo) {
		return todoService.createTodo(todo);
	}
	
	@PutMapping("/{todoId}")
	public Object updateTodo(@PathVariable int todoId, @RequestBody TodoVO todo) {
		todo.setTodoId(todoId);
		return todoService.updateTodo(todo);
	}
	
	@DeleteMapping("/{todoId}")
	public Object deleteTodo(@PathVariable int todoId) {
		return todoService.deleteTodo(todoId);
	}
}
