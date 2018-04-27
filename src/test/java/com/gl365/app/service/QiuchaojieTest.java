package com.gl365.app.service;

import com.gl365.app.dto.MyPosesDto;
import com.gl365.app.dto.command.BaseCommand;
import com.gl365.app.dto.command.QueryBankInfoCommand;
import com.gl365.app.remote.sales.PosService;
import com.gl365.app.remote.settlement.ApiRequest.QueryBankListCommand;
import com.gl365.app.remote.settlement.ApiRequest.UpdateBankAccountCommand;
import com.gl365.app.handler.AgentHandler;
import com.gl365.app.remote.gd.GdService;
import com.gl365.app.remote.gd.dto.AreaBankDto;
import com.gl365.app.remote.gd.dto.AreaDataDto;
import com.gl365.app.remote.profit.ProfitConfigService;
import com.gl365.app.remote.profit.command.QueryOneCommand;
import com.gl365.app.remote.profit.command.UpdateProfitConfigCommand;
import com.gl365.app.remote.profit.dto.OneAgentProfitDto;
import com.gl365.app.remote.settlement.ApiResponse.BanksDto;
import com.gl365.app.remote.settlement.ApiResponse.SingleBankDto;
import com.gl365.app.remote.settlement.ApiResponse.SynchronizeBankResultDto;
import com.gl365.app.remote.settlement.BankAccountInfoService;
import com.gl365.app.remote.withdraw.dto.BaseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by qiuchaojie on 2017/6/8.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class QiuchaojieTest {


    @Autowired
    private ValidatorFacadeService validatorFacadeService;

    @Autowired
    private GdService gdService;

    @Autowired
    private BankAccountInfoService bankAccountInfoService;

    @Autowired
    private ProfitConfigService profitConfigService;

    @Autowired
    private AgentHandler agentHandler;

    @Autowired
    private PosService posService;


    @Test
    public void validBankCardTest() {
        boolean isOk = validatorFacadeService.validBankCard("6217003230030368397", "440981199308213919", "邱朝杰");
        System.out.println(isOk);
    }


    @Test
    public void synchronizeBankInfo(){

        UpdateBankAccountCommand command = new UpdateBankAccountCommand();
        command.setOwnerId("3000005");
        command.setOwnerType("3");
        command.setBankAccountNo("6217003230030368397");
        command.setBankAccountName("邱朝杰");
        command.setBankName("建设银行支行");
        command.setBankNo("105");
        command.setBankAccountType("02");
        command.setCertNo("440981199308213919");
        SynchronizeBankResultDto dto = agentHandler.updateBankAccount(command, "440981199308213919", "邱朝杰");
        System.out.println(dto);
    }


    @Test
    public void querySingleBankAccountTest(){

        BaseCommand command = new BaseCommand();
        command.setOwnerId("3000005");
        command.setOwnerType("3");
        System.out.println(bankAccountInfoService.querySingle(command));
    }


    @Test
    public void queryBankListTest(){

        QueryBankListCommand command = new QueryBankListCommand();
        command.setOwnerType("3");
        BanksDto dto = bankAccountInfoService.queryBankList(command);
        System.out.println(dto);
    }


    @Test
    public void queryBankInfoTest(){
        QueryBankInfoCommand command = new QueryBankInfoCommand();
        command.setCurPage(1);
        command.setPageSize(20);
        command.setAreaId(1703);//这个不能为null，代表银行大类。如：102-工商银行，103-农业银行，104-中国银行，105-建设银行，301-交通银行，302-中信银行
        command.setBankNo("102");
        command.setBankName("");
        AreaBankDto dto = gdService.queryBankInfo(command);
        System.out.println(dto);
    }


    @Test
    public void singleNextLevelAreaTest(){
        List<AreaDataDto> list = gdService.getAreaInfoById(6);
        System.out.println(list);
    }


    @Test
    public void queryProfit(){
        QueryOneCommand command = new QueryOneCommand();
        command.setAgentNo("3000005");
        command.setAgentType("3");
        OneAgentProfitDto dto = profitConfigService.queryOne(command);
        System.out.println(dto);
    }


    @Test
    public void updateProfit(){

        UpdateProfitConfigCommand command = new UpdateProfitConfigCommand();
        command.setAgentNo("3000005");
        command.setAgentType("3");
        command.setDevMerchantFirstRate(new BigDecimal(4));
        command.setDevMerchantSecondRate(new BigDecimal(21));

        BaseDto dto = profitConfigService.setProfitConfig(command);
        System.out.println(dto);
    }


    @Test
    public void findPosList(){
        MyPosesDto dto = posService.myList("4000010", 1, 20);
        System.out.println(dto);
    }

}
