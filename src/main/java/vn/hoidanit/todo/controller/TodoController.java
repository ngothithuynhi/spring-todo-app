/*
 * Author: Hỏi Dân IT - @hoidanit 
 *
 * This source code is developed for the course
 * "Java Spring RESTful APIs - Xây Dựng Backend với Spring Boot".
 * It is intended for educational purposes only.
 * Unauthorized distribution, reproduction, or modification is strictly prohibited.
 *
 * Copyright (c) 2025 Hỏi Dân IT. All Rights Reserved.
 */

package vn.hoidanit.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.todo.entity.Todo;
import vn.hoidanit.todo.service.TodoService;

@RestController
public class TodoController {

	private final TodoService todoService;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping("/todos/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
		Todo todoData = this.todoService.getTodoById(id);
		return ResponseEntity.ok().body(todoData);
	}

	@GetMapping("/todos")
	public ResponseEntity<List<Todo>> getTodos() {
		List<Todo> listTodo = this.todoService.handleGetTodo();
		return ResponseEntity.ok().body(listTodo);
	}

	@PostMapping("/todos")
	public ResponseEntity<Todo> createTodo(@RequestBody Todo input) {
		Todo newTodo = this.todoService.handleCreateTodo(input);
		return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
	}

	@PutMapping("/todos/{id}")
	public ResponseEntity<String> updateTodo(@PathVariable Long id, @RequestBody Todo input) {
		this.todoService.handleUpdateTodo(id, input);
		return ResponseEntity.ok().body("update succeed.");
	}

	@DeleteMapping("/todos/{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
		this.todoService.handleDeleteTodo(id);
		return ResponseEntity.ok().body("delete a todo with id = " + id);
	}
}
