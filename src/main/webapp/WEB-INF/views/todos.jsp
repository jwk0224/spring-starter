<%@page import="java.util.List"%>
<%@page import="starter.vo.TodoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>todo list</h1>
	<table>
		<tr>
			<th>todoId</th>
			<th>content</th>
			<th>done</th>
		</tr>
	<%
		@SuppressWarnings("unchecked")
		List<TodoVO> todos = (List<TodoVO>) request.getAttribute("todos");
		for(int i = 0; i < todos.size(); i++) {
			TodoVO todo = todos.get(i);
	%>
		<tr>
			<td><%= todo.getTodoId() %></td>
			<td><%= todo.getContent() %></td>
			<td><%= todo.isDone() %></td>
		</tr>
	<%		
		}
	%>
	</table>
</body>
</html>