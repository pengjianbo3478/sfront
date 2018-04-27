package com.gl365.app.service;

import com.gl365.app.dto.users.LoginCommand;
import com.gl365.app.handler.AgentHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by caoyilong on 2017/6/1.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AgentLoginTest {

	@Autowired
	private AgentHandler agentHandler;

	@Test
	public void loginTest() throws Exception {
		LoginCommand loginCommand = new LoginCommand();
		loginCommand.setUsername("18825182207");
		loginCommand.setPassword("18825182207");
		agentHandler.login(loginCommand, null);
	}
}
