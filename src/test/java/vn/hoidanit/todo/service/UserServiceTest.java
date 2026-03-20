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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import vn.hoidanit.todo.entity.User;
import vn.hoidanit.todo.repository.UserRepository;
import vn.hoidanit.todo.service.impl.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	// fake
	@Mock
	private UserRepository userRepository;

	// UserRepository(fake) => userService
	@InjectMocks
	private UserService userService;

	@Test
	public void createUser_shouldReturnUser_WhenEmailValid() {
		// arrange : chuẩn bị
		User inputUser = new User(null, "eric", "hoidanit@gmail.com");
		User outputUser = new User(1L, "eric", "hoidanit@gmail.com");

		when(this.userRepository.existsByEmail(inputUser.getEmail())).thenReturn(false);

		when(this.userRepository.save(any())).thenReturn(outputUser);

		// act: hành động
		User result = this.userService.createUser(inputUser);

		// assert : so sánh
		assertEquals(1L, result.getId());
	}

	@Test
	public void createUser_shouldThrowException_WhenEmailInvalid() {
		// arrange : chuẩn bị
		User inputUser = new User(null, "eric", "hoidanit@gmail.com");

		when(this.userRepository.existsByEmail(inputUser.getEmail())).thenReturn(true);

		// act: hành động
		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			this.userService.createUser(inputUser);
		});

		// assert : so sánh
		assertEquals("Email already exists", ex.getMessage());

	}

	@Test
	public void updateUser_shouldReturnUser_whenValid() {
		// arrange : chuẩn bị
		Long inputId = 1L;
		User inputUser = new User(1L, "old name", "old@gmail.com");
		User outputUser = new User(1L, "new name", "new@gmail.com");

		when(this.userRepository.findById(inputId)).thenReturn(Optional.of(inputUser));
		when(this.userRepository.save(any())).thenReturn(outputUser);

		// act: hành động
		User result = this.userService.updateUser(inputId, inputUser);

		// assert : so sánh
		assertEquals("new name", result.getName());

	}

	@Test
	public void getAllUsers_shouldReturnAllUsers() {
		// arrange : chuẩn bị
		List<User> outputUsers = new ArrayList<>();
		outputUsers.add(new User(1L, "eric", "hoidanit@gmail.com"));
		outputUsers.add(new User(2L, "hary", "test@gmail.com"));

		when(this.userRepository.findAll()).thenReturn(outputUsers);

		// act: hành động
		List<User> result = this.userService.getAllUsers();

		// assert : so sánh
		assertEquals(2, result.size());
		assertEquals("hoidanit@gmail.com", result.get(0).getEmail());
	}

	@Test
	public void getUserById_shouldReturnOptinalUser() {
		// arrange : chuẩn bị
		Long inputId = 1L;
		User inputUser = new User(1L, "eric", "hoidanit@gmail.com");
		Optional<User> userOptionalOutput = Optional.of(inputUser);

		when(this.userRepository.findById(inputId)).thenReturn(userOptionalOutput);

		// act: hành động

		Optional<User> result = this.userService.getUserById(inputId);

		// assert : so sánh
		assertEquals(true, result.isPresent());
	}

	@Test
	public void deleteUser_ShouldReturnVoid_WhenUserExist() {
		// arrange : chuẩn bị
		Long inputId = 1L;
		when(this.userRepository.existsById(inputId)).thenReturn(true);

		// act: hành động
		this.userService.deleteUser(inputId);

		// assert : so sánh
		verify(this.userRepository).deleteById(1L);

	}

	@Test
	public void deleteUser_ShouldReturnException_WhenUserNotExist() {
		// arrange : chuẩn bị
		Long inputId = 1L;
		when(this.userRepository.existsById(inputId)).thenReturn(false);
		// act: hành động
		Exception ex = assertThrows(NoSuchElementException.class, () -> {
			this.userService.deleteUser(inputId);
		});

		// assert : so sánh
		assertEquals("User not found", ex.getMessage());
	}

}
