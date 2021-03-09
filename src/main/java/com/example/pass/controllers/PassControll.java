package com.example.pass.controllers;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pass.presenters.DataPresenter;
import com.example.pass.responses.CustomError;
import com.example.pass.services.ServicePass;

@RestController
@RequestMapping(value = "/validate")
public class PassControll {

	private static final Logger logger = LoggerFactory.getLogger(PassControll.class);

	private static final Object VALIDPASSWORD = "SENHA VALIDA";
	@Autowired
	ServicePass service;

	@GetMapping("/{password}")
	public ResponseEntity<?> validatePassword(HttpServletRequest request, @PathVariable String password) {
		logger.info("Validando regras de senha");
		String response = service.validPassword(password, VALIDPASSWORD);
		if (response.equals(VALIDPASSWORD)) {

			logger.info("senha atende os requisítos");
			DataPresenter data = new DataPresenter(VALIDPASSWORD);
			return ResponseEntity.status(HttpStatus.OK).body(data);

		} else {

			CustomError customError = new CustomError();
			customError.setMessage("SENHA INVALIDA");
			customError.setPath(String.format("Erro :%s", response));
			customError.setStatus(HttpStatus.BAD_REQUEST.value());
			customError.setTimestamp(LocalDateTime.now());

			logger.error(String.format("Senha nao atende os requisitos: %s", customError));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customError);

		}

	}

}
