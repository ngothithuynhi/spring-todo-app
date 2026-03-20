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

package vn.hoidanit.todo.service.v2;

import org.springframework.stereotype.Service;

@Service("sms")
public class SmsNotificationService implements NotificationService {
	@Override
	public void sendNotification(String message, String recipient) {
		// Logic gửi SMS
		System.out.println("Sending SMS to " + recipient + " with message: " + message);
	}
}
