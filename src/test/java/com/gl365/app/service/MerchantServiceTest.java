package com.gl365.app.service;

import com.gl365.app.dto.Merchant;
import com.gl365.app.remote.sales.MerchantService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wangmeihua on 2017/6/15.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MerchantServiceTest {
    @Autowired
    private MerchantService merchantService;

    @Test
    public void findByIdTest(){
       /* Merchant merchant = merchantService.findById("508c6c660a364c8f8b64c61e6310b1a7");
        System.out.print(merchant);*/
    }
}
