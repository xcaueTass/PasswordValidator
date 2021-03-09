package com.example.pass;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.pass.responses.CustomError;
import com.example.pass.services.ServicePass;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PassApplication.class)
class PassApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(PassApplicationTests.class);

	@Test
	void testServiceRules() {

		try {
			logger.info("Validando testes regra de negócio");
			ServicePass service = new ServicePass();

			String password = "AbTp9!fok";
			Object validPassword = "SENHA VALIDA";
			assertFalse(password.isEmpty());
			assertEquals("SENHA VALIDA", validPassword);
			assertTrue(password.length() > 0);
			assertTrue(password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*()-+]).{9,}$"));
			assertTrue(password.matches("(?=.*?[A-Z]).{1,}$"));
			assertTrue(password.matches("(?=.*?[a-z]).{1,}$"));
			assertTrue(password.matches("(?=.*?[0-9]).{1,}$"));
			assertFalse(password.indexOf(" ") >= 0);
			assertEquals("SENHA VALIDA", (service.validPassword(password, validPassword)));

			logger.info("Testes de regra de negócio validada com sucesso");
		} catch (Exception e) {
			logger.error(String.format("Erro ao efetuar testes: %s", e.getMessage()));
		}
	}

	@Test
	void testCustomResponse() {

		try {

			logger.info("Testando requisição BAD_REQUEST");
			CustomError customError = new CustomError();
			customError.setMessage("SENHA INVALIDA");
			customError.setPath("Erro: SENHA INVALIDA");
			customError.setStatus(HttpStatus.BAD_REQUEST.value());
			customError.setTimestamp(LocalDateTime.now());

			assertTrue(customError.getMessage().length() > 1);
			assertTrue(customError.getPath().length() > 1);
			assertThat(customError.getStatus());
			assertThat(customError.getTimestamp());

			logger.info("BAD_REQUEST executado com sucesso");
		} catch (Exception e) {
			logger.error(String.format("Erro ao efetuar requisição: %s", e.getMessage()));
		}

	}

}
