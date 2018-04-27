package com.gl365.app.dto;

import com.gl365.app.dto.enum_type.DeviceType;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ryan on 2017/6/16.
 */
public class MerchantApplicationSimple {

    private MerchantSimple merchant;

    private Integer numMobileDevice;

    private Integer numImmobileDevice;

    private Integer numSmartDevice;

    private Boolean applyForQrcode;

    public MerchantApplicationSimple(){

    }

    public MerchantApplicationSimple(MerchantApplication merchantApplication) {
        this.merchant = new MerchantSimple(merchantApplication.getMerchant());
        this.numMobileDevice = merchantApplication.getNumMobileDevice();
        this.numImmobileDevice = merchantApplication.getNumImmobileDevice();
        this.numSmartDevice = merchantApplication.getNumSmartDevice();
        this.applyForQrcode = merchantApplication.getApplyForQrcode();
    }

    @ApiModelProperty("商户详细信息")
    public MerchantSimple getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantSimple merchant) {
        this.merchant = merchant;
    }

    @ApiModelProperty("可移动设备数量，是否POS机申请，则判断numMobileDevice+numImmobileDevice+numSmartDevice是否大于0")
    public Integer getNumMobileDevice() {
        return numMobileDevice;
    }

    public void setNumMobileDevice(Integer numMobileDevice) {
        this.numMobileDevice = numMobileDevice;
    }

    @ApiModelProperty("固定设备数量")
    public Integer getNumImmobileDevice() {
        return numImmobileDevice;
    }

    public void setNumImmobileDevice(Integer numImmobileDevice) {
        this.numImmobileDevice = numImmobileDevice;
    }

    @ApiModelProperty("智能设备数量")
    public Integer getNumSmartDevice() {
        return numSmartDevice;
    }

    public void setNumSmartDevice(Integer numSmartDevice) {
        this.numSmartDevice = numSmartDevice;
    }

    @ApiModelProperty("是否申请二维码。0：不申请；1：申请")
    public Boolean getApplyForQrcode() {
        return applyForQrcode;
    }

    public void setApplyForQrcode(Boolean applyForQrcode) {
        this.applyForQrcode = applyForQrcode;
    }
}
