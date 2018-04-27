package com.gl365.app.dto;

/**
 * Created by zhouhui on 2017/11/21.
 */
public class MerchantProgressDetail {
    private String merchantName;
    private String provinceDesc;
    private String cityDesc;
    private String districtDesc;
    private String contactName;
    private String contactMobile;
    //商户号
    private String merchantSn;
    // 是否申请二维码。0：不申请；1：申请 ,
    private boolean applyForQrcode;
    //固定设备数量
    private int numImmobileDevice;
    //可移动设备数量
    private int numMobileDevice;
    //智能设备数量
    private int numSmartDevice;
    private String avatarUrl;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getProvinceDesc() {
        return provinceDesc;
    }

    public void setProvinceDesc(String provinceDesc) {
        this.provinceDesc = provinceDesc;
    }

    public String getCityDesc() {
        return cityDesc;
    }

    public void setCityDesc(String cityDesc) {
        this.cityDesc = cityDesc;
    }

    public String getDistrictDesc() {
        return districtDesc;
    }

    public void setDistrictDesc(String districtDesc) {
        this.districtDesc = districtDesc;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getMerchantSn() {
        return merchantSn;
    }

    public void setMerchantSn(String merchantSn) {
        this.merchantSn = merchantSn;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isApplyForQrcode() {
        return applyForQrcode;
    }

    public void setApplyForQrcode(boolean applyForQrcode) {
        this.applyForQrcode = applyForQrcode;
    }

    public int getNumImmobileDevice() {
        return numImmobileDevice;
    }

    public void setNumImmobileDevice(int numImmobileDevice) {
        this.numImmobileDevice = numImmobileDevice;
    }

    public int getNumMobileDevice() {
        return numMobileDevice;
    }

    public void setNumMobileDevice(int numMobileDevice) {
        this.numMobileDevice = numMobileDevice;
    }

    public int getNumSmartDevice() {
        return numSmartDevice;
    }

    public void setNumSmartDevice(int numSmartDevice) {
        this.numSmartDevice = numSmartDevice;
    }
}
