package com.gl365.app.remote.withdraw;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl365.app.dto.ApiResponse;
import com.gl365.app.dto.ReturnCode;
import com.gl365.app.remote.withdraw.command.QueryVoucherListCommand;
import com.gl365.app.remote.withdraw.command.VcPayDetailCommand;
import com.gl365.app.remote.withdraw.dto.DetailDto;
import com.gl365.app.remote.withdraw.dto.VcPayDto;
import com.gl365.app.remote.withdraw.dto.VoucherListDto;

/**
 * Created by qiuchaojie on 2017/6/16.
 */
@Component("withDrawHandler")
public class WithDrawHandler {

    @Autowired
    private WithDrawService withDrawService;
    public ApiResponse detail(String voucherId) {

        DetailDto dto = new DetailDto();

        QueryVoucherListCommand queryVoucherListCommand = new QueryVoucherListCommand();
        queryVoucherListCommand.setQueryType("1");
        queryVoucherListCommand.setVoucherId(voucherId);
        queryVoucherListCommand.setPageNum(1);
        queryVoucherListCommand.setNumPerPage(10);
        VoucherListDto voucherListDto = withDrawService.list(queryVoucherListCommand);
        if (!"000000".equals(voucherListDto.getResultCode())) {
            return ApiResponse.createResponseWithMessage(ReturnCode.System.SYSTEM_ERROR);
        }
        if (voucherListDto.getData().size() == 0) {
            return ApiResponse.createResponseWithMessage(ReturnCode.Voucher.NOT_FOUND_ERROR);
        }
        dto.setCreationTime(voucherListDto.getData().get(0).getCreateTime());
        dto.setCash(voucherListDto.getData().get(0).getCashMoney());
        dto.setFeeMoney(voucherListDto.getData().get(0).getFeeMoney());
        dto.setTaxMoney(voucherListDto.getData().get(0).getTaxMoney());
        dto.setRealMoney(voucherListDto.getData().get(0).getRealMoney());
        dto.setState(voucherListDto.getData().get(0).getState());
        dto.setErrorInfo(voucherListDto.getData().get(0).getRemark());
        dto.setMobile(voucherListDto.getData().get(0).getMobile());
       /* CashCommand cashCommand = new CashCommand();
        cashCommand.setCashMoney(voucherListDto.getData().get(0).getCashMoney());
        CalculateVcTxnDto calculateVcTxnDto = withDrawService.calculateVcTxn(cashCommand);
        if (!"000000".equals(calculateVcTxnDto.getResultCode())) {
            return ApiResponse.createResponseWithMessage(ReturnCode.System.SYSTEM_ERROR);
        }*/

        VcPayDetailCommand vcPayDetailCommand = new VcPayDetailCommand();
        vcPayDetailCommand.setVoucherId(voucherId);
        vcPayDetailCommand.setPageNum(1);
        vcPayDetailCommand.setNumPerPage(10);
        VcPayDto vcPayDto = withDrawService.vcPayDetail(vcPayDetailCommand);
        if (!"000000".equals(vcPayDto.getResultCode())) {
            return ApiResponse.createResponseWithMessage(ReturnCode.System.SYSTEM_ERROR);
        }
        
        if (CollectionUtils.isNotEmpty(vcPayDto.getData())) {
            dto.setName(vcPayDto.getData().get(0).getBankAccountName());
            dto.setBankAccountNo(vcPayDto.getData().get(0).getBankAccountNo());
            dto.setState(vcPayDto.getData().get(0).getState());
            dto.setPayTime(vcPayDto.getData().get(0).getPayTime());
            dto.setErrorInfo(vcPayDto.getData().get(0).getErrorInfo());
        }

        return ApiResponse.createSuccess(dto);
    }


}
