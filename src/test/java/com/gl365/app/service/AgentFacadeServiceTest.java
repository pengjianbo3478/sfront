package com.gl365.app.service;

import com.gl365.app.dto.command.AgentRegisterCommand;
import com.gl365.app.dto.enum_type.InvitePartnerType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by caoyilong on 2017/6/7.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AgentFacadeServiceTest {

	@Autowired
	private AgentFacadeService agentFacadeService;

	@Test
	public void create() {
		AgentRegisterCommand agentRegisterDto = new AgentRegisterCommand();
		agentRegisterDto.setCity(1);
		agentRegisterDto.setPassword("123456");
		agentRegisterDto.setDetailedAddress("黄埔村");
		agentRegisterDto.setIdCardNo("454478");
		agentRegisterDto.setProvince(1);
		agentRegisterDto.setDistrict(1);
		agentRegisterDto.setUpstreamAgentId("2001");
		agentRegisterDto.setName("long");
		agentRegisterDto.setMobile("18825182252");
		agentRegisterDto.setRegisterType(InvitePartnerType.PERSONAL);
		agentFacadeService.registerAgent(agentRegisterDto);
	}
}
