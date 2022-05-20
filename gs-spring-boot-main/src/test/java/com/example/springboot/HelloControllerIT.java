package com.example.springboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Base64;



public class HelloControllerIT {

	@Autowired
	private TestRestTemplate template;

	@Test
	public void getHello() throws Exception {
		String password = "password123";
		String passwordEncoded = Base64.getEncoder().encodeToString(password.getBytes());

		byte[] decodedBytes = Base64.getDecoder().decode(passwordEncoded);
		String decodedPassword = new String(decodedBytes);

		Assertions.assertEquals(password, decodedPassword);
	}

}
