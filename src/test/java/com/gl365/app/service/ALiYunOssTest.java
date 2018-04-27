package com.gl365.app.service;

import com.gl365.app.remote.sts.GeiLeStsService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ALiYunOssTest {

	@Autowired
	private GeiLeStsService aliYunOssService;
}
