package com.example.pass.services;

import org.springframework.stereotype.Service;

@Service
public class ServicePass {

	private static final int MAXIMUMSZIE = 9;
	private static final int MINIMUMSIZE = 1;

	public String validPassword(String password, Object validPassword) {

		String passwordValidate = validPassword.toString();

		if (!checkpassword(password)) {
			passwordValidate = "SENHA INVALIDA";

			if (!checkLettersUpperCase(password)) {
				passwordValidate = passwordValidate + " TER AO MENOS 1 LETRA MAIUSCULA -";
			}
			if (!checkLettersLowerCase(password)) {
				passwordValidate = passwordValidate + " TER AO MENOS 1 LETRA MINUSCULA -";
			}
			if (!checkSpecialCharacter(password)) {
				passwordValidate = passwordValidate + " TER 1 CARACTER";
			}

		}
		if (!checkSize(password)) {
			passwordValidate = passwordValidate + " TER AO MENOS 1 DIGITO -";
		}
		if (!checkSizepassword(password)) {
			passwordValidate = passwordValidate + " TER 9 OU MAIS CARACTERES -";
		}

		if (!checkRepeatedCharacter(password)) {
			passwordValidate = passwordValidate + " NÃO POSSUIR CARACTERES REPETIDOS -";
		}

		if (checkSpace(password)) {
			passwordValidate = passwordValidate + " NAO CONTER ESPAÇOS";
		}

		return passwordValidate;
	}

	private boolean checkpassword(String password) {

		return (password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*()-+]).{9,}$"));
	}

	private boolean checkSizepassword(String password) {
		return password.length() >= MAXIMUMSZIE;
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
