package com.example.pass.services;

import org.springframework.stereotype.Service;

@Service
public class ServicePass {

	private static final int TAMANHOMAXIMO = 9;
	private static final int TAMANHOMINIMO = 1;

	public String validPassword(String password, Object validpass) {

		String passwordValidate = validpass.toString();

		if (!checkPass(password)) {
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
		if (!checkSizePass(password)) {
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

	private boolean checkPass(String pass) {

		return (pass.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*()-+]).{9,}$"));
	}

	private boolean checkSizePass(String pass) {
		return pass.length() >= TAMANHOMAXIMO;
	}

	private boolean checkSize(String pass) {
		return pass.length() >= TAMANHOMINIMO;
	}

	private boolean checkLettersUpperCase(String pass) {
		return pass.matches("(?=.*?[A-Z]).{1,}$");

	}

	private boolean checkLettersLowerCase(String pass) {
		return pass.matches("(?=.*?[a-z]).{1,}$");

	}

	private boolean checkSpecialCharacter(String pass) {
		return pass.matches("(?=.*?[!@#$%^&*()-+]).{1,}$");

	}

	private boolean checkSpace(String pass) {
		return pass.indexOf(" ") >= 0;
	}

	private boolean checkRepeatedCharacter(String pass) {
		return validLetterToLetter(pass);
	}

	private boolean validLetterToLetter(String pass) {
		for (int i = 0; i < pass.length(); i++) {
			for (int j = i + 1; j < pass.length(); j++) {

				if (pass.charAt(i) == pass.charAt(j)) {
					return false;
				} else {
					continue;
				}
			}
		}
		return true;
	}

}
