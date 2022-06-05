package com.example.springboot;

import com.example.springboot.controllers.LoginController;
import com.example.springboot.controllers.SurveyController;
import com.example.springboot.services.SurveyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Base64;


@SpringBootTest
public class HelloControllerIT {

	@Autowired
	private SurveyController surveyController;

	@Autowired
	private LoginController loginController;

	@Autowired
	private SurveyService surveyService;

	@Test
	public void testEncryption() throws Exception {
		String password = "password123";
		String passwordEncoded = Base64.getEncoder().encodeToString(password.getBytes());

		byte[] decodedBytes = Base64.getDecoder().decode(passwordEncoded);
		String decodedPassword = new String(decodedBytes);

		Assertions.assertEquals(password, decodedPassword);
	}

	@Test
	public void testLoginController() throws Exception {
		Assertions.assertNotNull(loginController);
	}

	@Test
	public void testSurveyController() throws Exception {
		Assertions.assertNotNull(surveyController);
	}

	@Test
	public void testDateConverter() throws Exception {
		String date = "2022-05-19T18:46";
		String convertedDate = surveyService.dateToLocalDate(date);
		Assertions.assertEquals("2022-05-19", convertedDate);
	}



}
