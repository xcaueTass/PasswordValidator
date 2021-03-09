package com.example.pass.controllers;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.pass.PassApplication;

public class PassControllTest extends PassApplication {

	private MockMvc mockMvc;

	@Autowired
	private PassControll passControll;

	private static final Logger logger = LoggerFactory.getLogger(PassControllTest.class);

	@Before
	public void setUp() {
		logger.info("Iniciando controller");
		this.mockMvc = MockMvcBuilders.standaloneSetup(passControll).build();
	}

	@Test
	public void testGETIndexSalarioMinimoController() throws Exception {

		logger.info("Validado metodo GET - controller");
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/validate/AbTp9!fok"))
					.andExpect(MockMvcResultMatchers.status().isOk());

		} catch (Exception e) {
			logger.error(String.format("Erro ao efetuar o metodo get: %s", e.getMessage()));
		}
	}

}
