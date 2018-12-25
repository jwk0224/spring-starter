package starter.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import starter.vo.TodoVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*.xml")
public class TodoServiceTest {
	
	@Autowired
	private TodoService todoService;
	
	@Test
	public void testReadTodos() {
		Assert.assertNotNull(todoService.readTodos());
	}
	
	@Test
	public void testReadTodo() {
		Assert.assertNotNull(todoService.readTodo(1));
	}
	
	@Test
	@Ignore
	public void testCreateTodo() {
		Assert.assertEquals(1, todoService.createTodo(new TodoVO(1, "test", false)));
	}
	
	@Test
	public void testUpdateTodo() {
		Assert.assertEquals(1, todoService.updateTodo(new TodoVO(1, "test2", true)));
	}
	
	@Test
	@Ignore
	public void testDeleteTodo() {
		Assert.assertEquals(1, todoService.deleteTodo(1));
	}
}