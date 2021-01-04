package com.example.pass;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.pass.controllers.PassControll;

public class PassControllTest extends PassApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private PassControll passControll;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(passControll).build();
	}

	@Test
	public void testGETIndexSalarioMinimoController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/validate")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGETSaveSalarioMinimoController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/validate/{pass}"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("AbTp9!fok"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
