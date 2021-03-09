package com.example.pass.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ServicePass {

	private static final int MAXIMUMSIZE = 9;
	private static final int MINIMUMSIZE = 1;

	private static final Logger logger = LoggerFactory.getLogger(ServicePass.class);

	public String validPassword(String password, Object validPassword) {

		String passwordValidate = validPassword.toString();

		logger.info("Iniciado processo de validação das regras de senha");
		if (!checkpassword(password)) {
			passwordValidate = "SENHA INVALIDA";

			logger.info("Validando letra maiuscula");
			if (!checkLettersUpperCase(password)) {
				passwordValidate = passwordValidate + " TER AO MENOS 1 LETRA MAIUSCULA -";
				logger.error(String.format("Senha invalida - %s", passwordValidate));
			}

			logger.info("Validando letra Minuscula");
			if (!checkLettersLowerCase(password)) {
				passwordValidate = passwordValidate + " TER AO MENOS 1 LETRA MINUSCULA -";
				logger.error(String.format("Senha invalida - %s", passwordValidate));
			}

			logger.info("Validando caracter");
			if (!checkSpecialCharacter(password)) {
				passwordValidate = passwordValidate + " TER 1 CARACTER";
				logger.error(String.format("Senha invalida - %s", passwordValidate));
			}

		}

		logger.info("Validando digito");
		if (!checkSize(password)) {
			passwordValidate = passwordValidate + " TER AO MENOS 1 DIGITO -";
			logger.error(String.format("Senha invalida - %s", passwordValidate));
		}

		logger.info("Validando se existe 9 caracteres ou mais");
		if (!checkSizepassword(password)) {
			passwordValidate = passwordValidate + " TER 9 OU MAIS CARACTERES -";
			logger.error("Senha invalida - %s", passwordValidate);
		}

		logger.info("Validando letras repitidas");
		if (!checkRepeatedCharacter(password)) {
			passwordValidate = passwordValidate + " NÃO POSSUIR CARACTERES REPETIDOS -";
			logger.error(String.format("Senha invalida - %s", passwordValidate));
		}

		logger.info("Validando espaços");
		if (checkSpace(password)) {
			passwordValidate = passwordValidate + " NAO CONTER ESPAÇOS";
			logger.error(String.format("Senha invalida - %s", passwordValidate));
		}

		return passwordValidate;
	}

	private boolean checkpassword(String password) {

		return (password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*()-+]).{9,}$"));
	}

	private boolean checkSizepassword(String password) {
		return password.length() >= MAXIMUMSIZE;
	}

	private boolean checkSize(String password) {
		return password.length() >= MINIMUMSIZE;
	}

	private boolean checkLettersUpperCase(String password) {
		return password.matches("(?=.*?[A-Z]).{1,}$");

	}

	private boolean checkLettersLowerCase(String password) {
		return password.matches("(?=.*?[a-z]).{1,}$");

	}

	private boolean checkSpecialCharacter(String password) {
		return password.matches("(?=.*?[!@#$%^&*()-+]).{1,}$");

	}

	private boolean checkSpace(String password) {
		return password.indexOf(" ") >= 0;
	}

	private boolean checkRepeatedCharacter(String password) {
		return validLetterToLetter(password);
	}

	private boolean validLetterToLetter(String password) {
		for (int i = 0; i < password.length(); i++) {
			for (int j = i + 1; j < password.length(); j++) {

				if (password.charAt(i) == password.charAt(j)) {
					return false;
				} else {
					continue;
				}
			}
		}
		return true;
	}

}
