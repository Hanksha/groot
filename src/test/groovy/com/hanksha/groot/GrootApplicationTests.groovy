package com.hanksha.groot

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*

import groovy.json.JsonOutput
import groovy.json.JsonSlurper

@RunWith(SpringRunner)
@SpringBootTest
class GrootApplicationTests {

	@Autowired
	WebApplicationContext webApplicationContext

	MockMvc mockMvc

	JsonSlurper slurper

	@Before
	void setup() {
		mockMvc = new MockMvcBuilders().webAppContextSetup(webApplicationContext).alwaysExpect(status().isOk())
				.build()

		slurper = new JsonSlurper()
	}

	@Test
	void testBisection() {
		mockMvc.perform(get('/groot/bisection')
				.param('func','x - cos(x)').param('x0','-10').param('x1','10')
				.accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath('$.root', is(0.739085d)))
			.andDo({slurper.parseText(it.response.contentAsString).steps.each {println it}})
	}
}
