package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringBoot1Application.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeRestControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void test1EmployeeSave() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/employee/").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"Manoharan1\",\"location\": \"Frankfurt\",\"country\":\"Germany\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").exists()).andDo(print());
		mockMvc.perform(MockMvcRequestBuilders.post("/employee/").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"Manoharan2\",\"location\": \"Frankfurt\",\"country\":\"Germany\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").exists()).andDo(print());
	}

	@Test
	public void test2AllEmployeesTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/employee/").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", IsCollectionWithSize.hasSize(2))).andDo(print());
	}

	@Test
	public void test3EmployeeById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/employee/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.country").value("Germany")).andDo(print());
	}

	@Test
	public void test4InvalidEmployee() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/employee/3").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(204)).andDo(print());
	}

	@Test
	public void test5DeleteEmployeeById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/employee/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(204)).andDo(print());
	}

}
