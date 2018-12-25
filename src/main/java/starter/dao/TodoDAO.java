package starter.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import starter.vo.TodoVO;

@Repository
public class TodoDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	public List<TodoVO> readTodos() {
		return sqlSession.selectList("mappers.todo-mapper.readTodos");
	}
	
	public TodoVO readTodo(int todoId) {
		return sqlSession.selectOne("mappers.todo-mapper.readTodo", todoId);
	}
	
	public int createTodo(TodoVO todo) {
		return sqlSession.insert("mappers.todo-mapper.createTodo", todo);
	}
	
	public int updateTodo(TodoVO todo) {
		return sqlSession.update("mappers.todo-mapper.updateTodo", todo);
	}
	
	public int deleteTodo(int todoId) {
		return sqlSession.delete("mappers.todo-mapper.deleteTodo", todoId);
	}
}
