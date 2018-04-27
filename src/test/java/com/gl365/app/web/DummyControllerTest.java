package com.gl365.app.web;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

//import com.gl365.app.vo.Dummy;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class DummyControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testSave() throws Exception {
//		Dummy dummy = new Dummy();
//		dummy.setLocalDateP(LocalDate.now());
//		dummy.setLocalDateTimeP(LocalDateTime.now());

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/health");
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		
		params.add("localDateP", "" + LocalDate.now());
		
		builder.params(params);
		builder.accept(MediaType.ALL);
		ResultActions resultActions = this.mockMvc.perform(builder);
		resultActions.andDo(MockMvcResultHandlers.print());
		resultActions.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
