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

package vn.hoidanit.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import vn.hoidanit.todo.entity.Todo;
import vn.hoidanit.todo.entity.User;

@RestController
public class HelloController {

	@Autowired
	private ObjectMapper om;

	@GetMapping("/")
	public ResponseEntity<String> index() throws Exception {
		// json => object (frontend send data to backend)//
		String json = """
				{
				    "name": "eric",
				    "email": "hoidanit@gmail.com"
				}
				""";

		User test = om.readValue(json, User.class);

		User eric = new User(null, "ERIC", "test@gmail.com");
		String ericJson = om.writeValueAsString(eric);

		return ResponseEntity.ok().body(ericJson);
	}

	@GetMapping("/hoidanit")
	public ResponseEntity<Todo> hoidanit() {
		Todo test = new Todo("hoidanit todo", false);
//		return test;
		return ResponseEntity.ok().body(test);
	}

}
