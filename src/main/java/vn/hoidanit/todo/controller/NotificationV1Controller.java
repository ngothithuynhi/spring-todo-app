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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.todo.service.v1.EmailNotificationService;
import vn.hoidanit.todo.service.v1.SmsNotificationService;

@RestController
public class NotificationV1Controller {

	@Autowired
	private EmailNotificationService emailService;

	@Autowired
	private SmsNotificationService smsService;

	@PostMapping("/send-v1")
	public ResponseEntity<String> sendNotification(@RequestParam String type, @RequestParam String message,
			@RequestParam String recipient) {

		if ("email".equalsIgnoreCase(type)) {
			emailService.sendEmail(message, recipient);
		} else if ("sms".equalsIgnoreCase(type)) {
			smsService.sendSms(message, recipient);
		} else {
			return ResponseEntity.badRequest().body("Unsupported notification type");
		}

		return ResponseEntity.ok("Notification sent via " + type);
	}
}
