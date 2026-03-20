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

package vn.hoidanit.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.todo.entity.Todo;
import vn.hoidanit.todo.repository.TodoRepository;

@Service
public class TodoService {

	private final TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public Todo getTodoById(Long id) {
		Optional<Todo> todoOptional = this.todoRepository.findById(id);
		return todoOptional.isPresent() ? todoOptional.get() : null;
	}

	public Todo handleCreateTodo(Todo todo) {
		// do something
		Todo createdTodo = this.todoRepository.save(todo);
		return createdTodo;
	}

	public List<Todo> handleGetTodo() {
		return this.todoRepository.findAll();
	}

	public void handleUpdateTodo(Long id, Todo inputTodo) {

		Optional<Todo> todoOptional = this.todoRepository.findById(id);
		if (todoOptional.isPresent()) {
			Todo currentTodo = todoOptional.get();

			currentTodo.setCompleted(inputTodo.isCompleted());
			currentTodo.setUsername(inputTodo.getUsername());

			this.todoRepository.save(currentTodo);
		}

	}

	public void handleDeleteTodo(Long id) {
		this.todoRepository.deleteById(id);
	}
}
