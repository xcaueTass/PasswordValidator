package com.example.pass.services;

import org.springframework.stereotype.Service;

@Service
public class ServicePass {

	private static final int TamanhoMinimoPass = 9;
	private static final int TamanhoMinimo = 1;

	public String validPass(String pass, Object validpass) {

		String passw = validpass.toString();

		if (!checkPass(pass)) {
			passw = "SENHA INVALIDA";

			if (!checkLettersUpperCase(pass)) {
				passw = passw + " TER AO MENOS 1 LETRA MAIUSCULA -";
			}
			if (!checkLettersLowerCase(pass)) {
				passw = passw + " TER AO MENOS 1 LETRA MINUSCULA -";
			}
			if (!checkSpecialCharacter(pass)) {
				passw = passw + " TER 1 CARACTER";
			}

		}
		if (!checkSize(pass)) {
			passw = passw + " TER AO MENOS 1 DIGITO -";
		}
		if (!checkSizePass(pass)) {
			passw = passw + " TER 9 OU MAIS CARACTERES -";
		}

		if (!checkRepeatedCharacter(pass)) {
			passw = passw + " NÃO POSSUIR CARACTERES REPETIDOS -";
		}

		if (checkSpace(pass)) {
			passw = passw + " NAO CONTER ESPAÇOS";
		}

		return passw;
	}

	private boolean checkPass(String pass) {

		return (pass.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*()-+]).{9,}$"));
	}

	private boolean checkSizePass(String pass) {
		return pass.length() >= TamanhoMinimoPass;
	}

	private boolean checkSize(String pass) {
		return pass.length() >= TamanhoMinimo;
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
