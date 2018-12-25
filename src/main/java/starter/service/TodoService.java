package starter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import starter.dao.TodoDAO;
import starter.vo.TodoVO;

@Service
public class TodoService {
	
	@Autowired
	private TodoDAO todoDAO;
	
	public List<TodoVO> readTodos() {
		return todoDAO.readTodos();
	}
	
	public TodoVO readTodo(int todoId) {
		return todoDAO.readTodo(todoId);
	}
	
	public int createTodo(TodoVO todo) {
		return todoDAO.createTodo(todo);
	}
	
	public int updateTodo(TodoVO todo) {
		return todoDAO.updateTodo(todo);
	}
	
	public int deleteTodo(int todoId) {
		return todoDAO.deleteTodo(todoId);
	}
}