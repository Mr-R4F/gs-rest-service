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

	//Os métodos fornecidos são testes unitários escritos para verificar o comportamento de um endpoint da aplicação

	/*
	 * Este teste faz uma requisição GET ao endpoint /greeting sem fornecer nenhum parâmetro.
	 * Ele verifica se a resposta tem o status HTTP 200 OK (status().isOk()).
	 * Verifica também se o campo "content" do corpo JSON da resposta contém o valor "Hello, World!".
	 * 
	 * Em resumo, testa se o endpoint retorna a mensagem padrão "Hello, World!" quando nenhum parâmetro é passado.
	 * 
	 * Objetivo: Validar que, quando o endpoint é chamado sem parâmetros, ele retorna uma resposta padrão adequada, ou seja, a mensagem "Hello, World!".
	 * Isso ajuda a evitar erros ou comportamentos inesperados quando usuários fazem requisições sem passar parâmetros.
	 */
	@Test
	public void noParamGreetingShouldReturnDefaultMessage() throws Exception {

		this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Hello, World!"));
	}


	/*
	 * Este teste faz uma requisição GET ao endpoint /greeting, mas desta vez fornece um parâmetro de requisição chamado name com o valor "Spring Community".
	 * Ele verifica se a resposta tem o status HTTP 200 OK (status().isOk()).
	 * Verifica também se o campo "content" do corpo JSON da resposta contém o valor "Hello, Spring Community!".
	 * 
	 * Em resumo, testa se o endpoint personaliza a mensagem para incluir o valor do parâmetro name.
	 * 
	 * Objetivo: Garantir que o endpoint pode personalizar a mensagem retornada de acordo com o valor do parâmetro name passado na requisição.
	 * Neste caso, ao passar o valor "Spring Community", o teste verifica que a resposta seja "Hello, Spring Community!".
	 */
	@Test
	public void paramGreetingShouldReturnTailoredMessage() throws Exception {

		this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
	}

}
