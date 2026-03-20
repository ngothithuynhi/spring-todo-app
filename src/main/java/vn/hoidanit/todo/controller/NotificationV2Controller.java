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

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.todo.service.v2.NotificationService;

@RestController
public class NotificationV2Controller {

	private final Map<String, NotificationService> notificationServices;

	public NotificationV2Controller(Map<String, NotificationService> notificationServices) {
		this.notificationServices = notificationServices;
	}

	@PostMapping("/send-v2")
	public String send(@RequestParam String type, @RequestParam String message, @RequestParam String recipient) {
		NotificationService service = notificationServices.get(type);
		if (service == null) {
			return "Unsupported type: " + type;
		}
		service.sendNotification(message, recipient);
		return "Sent via " + type;
	}
}
