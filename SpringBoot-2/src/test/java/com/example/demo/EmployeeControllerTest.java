package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeControllerTest {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void test1SaveEmployee() throws Exception {
		mockMvc.perform(post("/employee/").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"Manoharan10\",\"location\":\"Frankfurt\",\"country\":\"Germany\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").exists()).andDo(print());
	}

	@Test
	public void test2GetAllEmployees() throws Exception {

		mockMvc.perform(get("/employee/").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", IsCollectionWithSize.hasSize(6))).andDo(print());
	}

	@Test
	public void test3GetEmployeeById() throws Exception {

		mockMvc.perform(get("/employee/3").accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.name").exists()).andDo(print());

	}

	@Test
	public void test4GetInvalidEmployeeById() throws Exception {
		mockMvc.perform(get("/employee/999").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.errorCode").value(404)).andDo(print());
	}
}
