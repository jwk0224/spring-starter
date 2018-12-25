package starter.vo;

public class TodoVO {
	private int todoId;
	private String content;
	private boolean done;
	
	public TodoVO() {}

	public TodoVO(int todoId, String content, boolean done) {
		super();
		this.todoId = todoId;
		this.content = content;
		this.done = done;
	}

	public int getTodoId() {
		return todoId;
	}

	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "TodoVO [todoId=" + todoId + ", content=" + content + ", done=" + done + "]";
	}
}
