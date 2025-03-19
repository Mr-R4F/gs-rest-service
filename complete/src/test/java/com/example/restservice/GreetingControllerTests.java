/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.restservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerTests {

	@Autowired
	private MockMvc mockMvc;

	/* 
	 * O método abaixo, realiza uma requisição "GET" e espera receber a URL "/Greeting". Em caso de sucesso durante o processo de requisição, o endpoint 
	 * espera retornar o código 200 (OK) e também um json com um campo "Content" cujo o valor deve ser "Hello, World!". Qualquer outro método 
	 * de requisição que não seja "GET", uma URL diferente ou valor diferente, ocasionará  em um erro. A API irá informar esse erro com um json contendo os campos 
	 * (Ou seja, o método vai 'lançar' uma Exception).
	 * 
	 * O ojetivo geral do método é efetuar um teste na URL "/Greeting" e verificar se o código de retorno e o valor são os esperados.
	 */
	@Test
	public void noParamGreetingShouldReturnDefaultMessage() throws Exception {

		this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Hello, World!"));
	}

	/* O método abaixo, realiza uma requisição "GET" e espera receber a URL "/Greeting" e um parâmetro ("name" com o valor "Spring Community"). No processo 
	 * de requisição, o endpoint espera o retorno do código 200 (OK) e também um json com um campo "Content" cujo o valor deve ser "Hello, Spring Community!",
	 * e o parametro "name" deve ser "Spring Community" para que o teste seja bem sucedido.
	 * Qualquer outro método de requisição que não seja "GET", uma URL diferente ou paramêtros diferentes, ocasionará em um código de erro. A API irá informar esse 
	 * erro. (Ou seja, o método vai 'lançar' uma Exception).
	 * 
	 * O ojetivo geral do método é efetuar um teste na URL "/Greeting", validar se os parâmetros estão corretos, o status e verificar se o valor é o esperado.
	 */
	@Test
	public void paramGreetingShouldReturnTailoredMessage() throws Exception {

		this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
	}

}
