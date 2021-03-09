package com.example.pass;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.pass.responses.CustomError;
import com.example.pass.services.ServicePass;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PassApplication.class)
class PassApplicationTests {

	@Test
	void testServiceRules() {
		ServicePass service = new ServicePass();

		String password = "Ao@23ertuio";
		Object validPassword = "SENHA VALIDA";
		assertTrue(service.validPassword(password, validPassword).isEmpty());
		assertTrue(password.length() > 0);
		assertTrue(password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*()-+]).{9,}$"));
		assertTrue(password.matches("(?=.*?[A-Z]).{1,}$"));
		assertTrue(password.matches("(?=.*?[a-z]).{1,}$"));
		assertTrue(password.matches("(?=.*?[0-9]).{1,}$"));
		assertFalse(password.indexOf(" ") >= 0);
	}

	@Test
	void testCustomResponse() {
		CustomError customError = new CustomError();
		customError.setMessage("SENHA INVALIDA");
		customError.setPath("Erro: SENHA INVALIDA");
		customError.setStatus(HttpStatus.BAD_REQUEST.value());
		customError.setTimestamp(LocalDateTime.now());

		assertTrue(customError.getMessage().length() > 1);
		assertTrue(customError.getPath().length() > 1);
		assertThat(customError.getStatus());
		assertThat(customError.getTimestamp());
	}

}
