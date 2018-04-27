package com.gl365.app.web;

import com.gl365.app.dto.ApiRequest;
import com.gl365.app.dto.command.FindMyAgentPartnerCommand;
import com.gl365.app.dto.enum_type.AgentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wangmeihua on 2017/6/8.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MyPartnerControllerTest {
    @Autowired
    private PartnerController partnerController;

    /**
     * 查询市代的县代伙伴
     */
    @Test
    public void findCountyForCity(){
        ApiRequest<Void> request = new ApiRequest<Void>();
        //partnerController.findCountyByCity(request);
    }

    /**
     * 查询所有代理商的商户伙伴
     */
    @Test
    public void findMerchantForAgent(){

        ApiRequest<Void> request = new ApiRequest<Void>();
        //partnerController.findMerchantPartner(request);
    }

    /**
     * 查询直接业务员伙伴
     */
    @Test
    public void findPersonalForCityOrCounty(){

        ApiRequest<Void> request = new ApiRequest<Void>();
        //partnerController.findPersonalPartner(request);
    }

    /**
     * 查询所有代理商的用户伙伴
     */
    @Test
    public void findCustomerForAgent(){
        ApiRequest<Void> request = new ApiRequest<Void>();
        //partnerController;
    }
}
