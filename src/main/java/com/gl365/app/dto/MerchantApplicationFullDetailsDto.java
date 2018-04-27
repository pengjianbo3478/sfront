package com.gl365.app.dto;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wangmeihua on 2017/6/6.
 */
public class MerchantApplicationFullDetailsDto extends MerchantApplication{

    /**
     * 对外经营名称
     */
    private String externalOperationName;
    /**
     * 税务登记证图片URL
     */
    private String taxRegPicUrl;
    /**
     * 营业执照图片URL
     */
    private String licensePicUrl;
    /**
     * 法人身份证正面图片URL
     */
    private String legalPersonIdCardFrontPicUrl;
    /**
     * 法人身份证背面图片URL
     */
    private String legalPersonIdCardBackPicUrl;
    /**
     * 组织机构代码证图片URL
     */
    private String orgCodePicUrl;
    /**
     * 协议照片URL
     */
    private String contractPicUrl;
    /**
     * 门头照图片URL
     */
    private String facadeOfShopPicUrl;
    /**
     * 地址照图片URL
     */
    private String addressPicUrl;
    /**
     * 经营内容图片URL
     */
    private String scopeOfOperationPicUrl;
    /**
     * 上传小票图片URL
     */
    private String ticketPicUrl;
    /**
     * 对公法人账号姓名
     */
    private String publpAccountName;
    /**
     * 对公法人账号号码
     */
    private String publpAccountNo;
    /**
     * 法人身份证号码
     */
    private String publpIdCardNo;
    /**
     * 开户行
     */
    private String publpAccountBank;
    /**
     * 对私法人账号姓名
     */
    private String prilpAccountName;
    /**
     * 对私法人账号号码
     */
    private String prilpAccountNo;
    /**
     * 法人身份证号码
     */
    private String prilpIdCardNo;
    /**
     * 开户行
     */
    private String prilpAccountBank;
    /**
     * 对私非法人账号姓名
     */
    private String prinlpAccountName;
    /**
     * 对私非法人账号号码
     */
    private String prinlpAccountNo;
    /**
     * 对私非法人-法人身份证号码
     */
    private String prinlpIdCardNo;
    /**
     * 对私非法人-非法人身份证号码
     */
    private String prinlpIdCardNo2;
    /**
     * 开户行
     */
    private String prinlpAccountBank;
    /**
     * 法人委托书图片URL
     */
    private String prinlpEngagementLetterPicUrl;
    /**
     * 法人持身份证照图片URL
     */
    private String prinlpIdHoldingPicUrl;
    /**
     * 营业执照有效期
     */
    private String licensePicVldDate;
    /**
     * 注册地址
     */
    private String registeredAddress;
    /**
     * 法人证件用效期
     */
    private String legalPersonIdCardVldDate;
    
    //开户许可证（非必填）
  	private String openLicensePicUrl;
  	//给乐商户合作协议（非必填）
  	private String contractNoPicUrl;
  	//收银台/前台照（必填）
  	private String cashierDeskPicUrl;
  	//经营产品/经营场地（必填）
  	private String operateProductPicUrl;
  	//经营场地/大楼外观照（必填）
  	private String operateFieldPicUrl;
  	//对私法人银行卡正面（必填）
  	private String prilpBankFrontPicUrl;
  	//对私法人银行卡反面（必填）
  	private String prilpBankBackPicUrl;
  	//对私非法人身份证正面（必填）
  	private String prinlpIdCardFrontPicUrl;
  	//对私非法人身份证反面（必填）
  	private String prinlpIdCardBackPicUrl;
  	//对私非法人银行卡正面（必填）
  	private String prinlpBankFrontPicUrl;
  	//对私非法人银行卡反面（必填）
  	private String prinlpBankBackPicUrl;
  	//预先绑定二维码
  	private String barCode;
  	
  	private Integer property;// 开户性质:1:对私法人，2对私非法人，3对公
	private String isPartner;// 合伙人商户,是:Y
	private String taxRegCerNo;// 税务登记号（企业，必填）

	private String busContacts;// 企业联系人姓名（企业，必填）
	private String busContactsPerNo;// 企业联系人身份证号（企业，必填）
	private String busContactsMobile;// 企业联系人手机号（企业，必填）
	private String prinlpMobile;// 对私非法人手机号
	private String legalPerMobile;// 企业为法人手机号
	
	 @ApiModelProperty("人均消费")
	private BigDecimal avgPrice;//人均消费
	 
	 @ApiModelProperty("补充给乐商户合作协议")
	private String additionalContractNoPicUrl;//补充给乐商户合作协议
	
	private List<String> barCodes;
	
    public List<String> getBarCodes() {
		return barCodes;
	}

	public void setBarCodes(List<String> barCodes) {
		this.barCodes = barCodes;
	}
	
	@ApiModelProperty("对外经营名称")
    public String getExternalOperationName() {
        return externalOperationName;
    }

    public void setExternalOperationName(String externalOperationName) {
        this.externalOperationName = externalOperationName;
    }

    @ApiModelProperty("税务登记证图片URL")
    public String getTaxRegPicUrl() {
        return taxRegPicUrl;
    }

    public void setTaxRegPicUrl(String taxRegPicUrl) {
        this.taxRegPicUrl = taxRegPicUrl;
    }

    @ApiModelProperty("营业执照图片URL")
    public String getLicensePicUrl() {
        return licensePicUrl;
    }

    public void setLicensePicUrl(String licensePicUrl) {
        this.licensePicUrl = licensePicUrl;
    }

    @ApiModelProperty("法人身份证正面图片URL")
    public String getLegalPersonIdCardFrontPicUrl() {
        return legalPersonIdCardFrontPicUrl;
    }

    public void setLegalPersonIdCardFrontPicUrl(String legalPersonIdCardFrontPicUrl) {
        this.legalPersonIdCardFrontPicUrl = legalPersonIdCardFrontPicUrl;
    }

    @ApiModelProperty("法人身份证背面图片URL")
    public String getLegalPersonIdCardBackPicUrl() {
        return legalPersonIdCardBackPicUrl;
    }

    public void setLegalPersonIdCardBackPicUrl(String legalPersonIdCardBackPicUrl) {
        this.legalPersonIdCardBackPicUrl = legalPersonIdCardBackPicUrl;
    }

    @ApiModelProperty("组织机构代码证图片URL")
    public String getOrgCodePicUrl() {
        return orgCodePicUrl;
    }

    public void setOrgCodePicUrl(String orgCodePicUrl) {
        this.orgCodePicUrl = orgCodePicUrl;
    }

    @ApiModelProperty("协议照片URL")
    public String getContractPicUrl() {
        return contractPicUrl;
    }

    public void setContractPicUrl(String contractPicUrl) {
        this.contractPicUrl = contractPicUrl;
    }

    @ApiModelProperty("门头照URL")
    public String getFacadeOfShopPicUrl() {
        return facadeOfShopPicUrl;
    }

    public void setFacadeOfShopPicUrl(String facadeOfShopPicUrl) {
        this.facadeOfShopPicUrl = facadeOfShopPicUrl;
    }

    @ApiModelProperty("地址照图片URL")
    public String getAddressPicUrl() {
        return addressPicUrl;
    }

    public void setAddressPicUrl(String addressPicUrl) {
        this.addressPicUrl = addressPicUrl;
    }

    @ApiModelProperty("经营内容图片URL")
    public String getScopeOfOperationPicUrl() {
        return scopeOfOperationPicUrl;
    }

    public void setScopeOfOperationPicUrl(String scopeOfOperationPicUrl) {
        this.scopeOfOperationPicUrl = scopeOfOperationPicUrl;
    }

    @ApiModelProperty("上传小票图片URL")
    public String getTicketPicUrl() {
        return ticketPicUrl;
    }

    public void setTicketPicUrl(String ticketPicUrl) {
        this.ticketPicUrl = ticketPicUrl;
    }

    @ApiModelProperty("对公法人账号姓名")
    public String getPublpAccountName() {
        return publpAccountName;
    }

    public void setPublpAccountName(String publpAccountName) {
        this.publpAccountName = publpAccountName;
    }

    @ApiModelProperty("对公法人账号号码")
    public String getPublpAccountNo() {
        return publpAccountNo;
    }

    public void setPublpAccountNo(String publpAccountNo) {
        this.publpAccountNo = publpAccountNo;
    }

    @ApiModelProperty("法人身份证号码")
    public String getPublpIdCardNo() {
        return publpIdCardNo;
    }

    public void setPublpIdCardNo(String publpIdCardNo) {
        this.publpIdCardNo = publpIdCardNo;
    }

    @ApiModelProperty("开户行")
    public String getPublpAccountBank() {
        return publpAccountBank;
    }

    public void setPublpAccountBank(String publpAccountBank) {
        this.publpAccountBank = publpAccountBank;
    }

    @ApiModelProperty("对私法人账号姓名")
    public String getPrilpAccountName() {
        return prilpAccountName;
    }

    public void setPrilpAccountName(String prilpAccountName) {
        this.prilpAccountName = prilpAccountName;
    }

    @ApiModelProperty("对私法人账号号码")
    public String getPrilpAccountNo() {
        return prilpAccountNo;
    }

    public void setPrilpAccountNo(String prilpAccountNo) {
        this.prilpAccountNo = prilpAccountNo;
    }

    @ApiModelProperty("对私法人身份证号码")
    public String getPrilpIdCardNo() {
        return prilpIdCardNo;
    }

    public void setPrilpIdCardNo(String prilpIdCardNo) {
        this.prilpIdCardNo = prilpIdCardNo;
    }

    @ApiModelProperty("对私法人银行开户行")
    public String getPrilpAccountBank() {
        return prilpAccountBank;
    }

    public void setPrilpAccountBank(String prilpAccountBank) {
        this.prilpAccountBank = prilpAccountBank;
    }

    @ApiModelProperty("对私非法人银行账号名")
    public String getPrinlpAccountName() {
        return prinlpAccountName;
    }

    public void setPrinlpAccountName(String prinlpAccountName) {
        this.prinlpAccountName = prinlpAccountName;
    }

    @ApiModelProperty("对私非法人银行账号")
    public String getPrinlpAccountNo() {
        return prinlpAccountNo;
    }

    public void setPrinlpAccountNo(String prinlpAccountNo) {
        this.prinlpAccountNo = prinlpAccountNo;
    }

    @ApiModelProperty("对私非法人-法人身份证号")
    public String getPrinlpIdCardNo() {
        return prinlpIdCardNo;
    }

    public void setPrinlpIdCardNo(String prinlpIdCardNo) {
        this.prinlpIdCardNo = prinlpIdCardNo;
    }

    @ApiModelProperty("对私非法人-非法人身份证号")
    public String getPrinlpIdCardNo2() {
        return prinlpIdCardNo2;
    }

    public void setPrinlpIdCardNo2(String prinlpIdCardNo2) {
        this.prinlpIdCardNo2 = prinlpIdCardNo2;
    }

    @ApiModelProperty("对私非法人开户行")
    public String getPrinlpAccountBank() {
        return prinlpAccountBank;
    }

    public void setPrinlpAccountBank(String prinlpAccountBank) {
        this.prinlpAccountBank = prinlpAccountBank;
    }

    @ApiModelProperty("法人委托书图片URL")
    public String getPrinlpEngagementLetterPicUrl() {
        return prinlpEngagementLetterPicUrl;
    }

    public void setPrinlpEngagementLetterPicUrl(String prinlpEngagementLetterPicUrl) {
        this.prinlpEngagementLetterPicUrl = prinlpEngagementLetterPicUrl;
    }

    @ApiModelProperty("法人持身份证照图片URL")
    public String getPrinlpIdHoldingPicUrl() {
        return prinlpIdHoldingPicUrl;
    }

    public void setPrinlpIdHoldingPicUrl(String prinlpIdHoldingPicUrl) {
        this.prinlpIdHoldingPicUrl = prinlpIdHoldingPicUrl;
    }

    @ApiModelProperty("营业执照有效期")
	public String getLicensePicVldDate() {
		return licensePicVldDate;
	}

	public void setLicensePicVldDate(String licensePicVldDate) {
		this.licensePicVldDate = licensePicVldDate;
	}

	@ApiModelProperty("注册地址")
	public String getRegisteredAddress() {
		return registeredAddress;
	}

	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	@ApiModelProperty("法人证件用效期")
	public String getLegalPersonIdCardVldDate() {
		return legalPersonIdCardVldDate;
	}

	public void setLegalPersonIdCardVldDate(String legalPersonIdCardVldDate) {
		this.legalPersonIdCardVldDate = legalPersonIdCardVldDate;
	}
	@ApiModelProperty("开户许可证（非必填）")
	public String getOpenLicensePicUrl() {
		return openLicensePicUrl;
	}

	public void setOpenLicensePicUrl(String openLicensePicUrl) {
		this.openLicensePicUrl = openLicensePicUrl;
	}

	@ApiModelProperty("给乐商户合作协议（非必填）")
	public String getContractNoPicUrl() {
		return contractNoPicUrl;
	}

	public void setContractNoPicUrl(String contractNoPicUrl) {
		this.contractNoPicUrl = contractNoPicUrl;
	}
	
	@ApiModelProperty("收银台/前台照（必填）")
	public String getCashierDeskPicUrl() {
		return cashierDeskPicUrl;
	}

	public void setCashierDeskPicUrl(String cashierDeskPicUrl) {
		this.cashierDeskPicUrl = cashierDeskPicUrl;
	}

	@ApiModelProperty("经营产品/经营场地（必填）")
	public String getOperateProductPicUrl() {
		return operateProductPicUrl;
	}

	public void setOperateProductPicUrl(String operateProductPicUrl) {
		this.operateProductPicUrl = operateProductPicUrl;
	}

	@ApiModelProperty("经营场地/大楼外观照（必填）")
	public String getOperateFieldPicUrl() {
		return operateFieldPicUrl;
	}

	public void setOperateFieldPicUrl(String operateFieldPicUrl) {
		this.operateFieldPicUrl = operateFieldPicUrl;
	}

	@ApiModelProperty("对私法人银行卡正面（必填）")
	public String getPrilpBankFrontPicUrl() {
		return prilpBankFrontPicUrl;
	}

	public void setPrilpBankFrontPicUrl(String prilpBankFrontPicUrl) {
		this.prilpBankFrontPicUrl = prilpBankFrontPicUrl;
	}

	@ApiModelProperty("对私法人银行卡反面（必填）")
	public String getPrilpBankBackPicUrl() {
		return prilpBankBackPicUrl;
	}

	public void setPrilpBankBackPicUrl(String prilpBankBackPicUrl) {
		this.prilpBankBackPicUrl = prilpBankBackPicUrl;
	}

	@ApiModelProperty("对私非法人身份证正面（必填）")
	public String getPrinlpIdCardFrontPicUrl() {
		return prinlpIdCardFrontPicUrl;
	}

	public void setPrinlpIdCardFrontPicUrl(String prinlpIdCardFrontPicUrl) {
		this.prinlpIdCardFrontPicUrl = prinlpIdCardFrontPicUrl;
	}

	@ApiModelProperty("对私非法人身份证反面（必填）")
	public String getPrinlpIdCardBackPicUrl() {
		return prinlpIdCardBackPicUrl;
	}

	public void setPrinlpIdCardBackPicUrl(String prinlpIdCardBackPicUrl) {
		this.prinlpIdCardBackPicUrl = prinlpIdCardBackPicUrl;
	}

	@ApiModelProperty("对私非法人银行卡正面（必填）")
	public String getPrinlpBankFrontPicUrl() {
		return prinlpBankFrontPicUrl;
	}

	public void setPrinlpBankFrontPicUrl(String prinlpBankFrontPicUrl) {
		this.prinlpBankFrontPicUrl = prinlpBankFrontPicUrl;
	}

	@ApiModelProperty("对私非法人银行卡反面（必填）")
	public String getPrinlpBankBackPicUrl() {
		return prinlpBankBackPicUrl;
	}

	public void setPrinlpBankBackPicUrl(String prinlpBankBackPicUrl) {
		this.prinlpBankBackPicUrl = prinlpBankBackPicUrl;
	}
	@ApiModelProperty("预先绑定二维码")
	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Integer getProperty() {
		return property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}

	public String getIsPartner() {
		return isPartner;
	}

	public void setIsPartner(String isPartner) {
		this.isPartner = isPartner;
	}

	public String getTaxRegCerNo() {
		return taxRegCerNo;
	}

	public void setTaxRegCerNo(String taxRegCerNo) {
		this.taxRegCerNo = taxRegCerNo;
	}

	public String getBusContacts() {
		return busContacts;
	}

	public void setBusContacts(String busContacts) {
		this.busContacts = busContacts;
	}

	public String getBusContactsPerNo() {
		return busContactsPerNo;
	}

	public void setBusContactsPerNo(String busContactsPerNo) {
		this.busContactsPerNo = busContactsPerNo;
	}

	public String getBusContactsMobile() {
		return busContactsMobile;
	}

	public void setBusContactsMobile(String busContactsMobile) {
		this.busContactsMobile = busContactsMobile;
	}

	public String getPrinlpMobile() {
		return prinlpMobile;
	}

	public void setPrinlpMobile(String prinlpMobile) {
		this.prinlpMobile = prinlpMobile;
	}

	public String getLegalPerMobile() {
		return legalPerMobile;
	}

	public void setLegalPerMobile(String legalPerMobile) {
		this.legalPerMobile = legalPerMobile;
	}

	public BigDecimal getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getAdditionalContractNoPicUrl() {
		return additionalContractNoPicUrl;
	}

	public void setAdditionalContractNoPicUrl(String additionalContractNoPicUrl) {
		this.additionalContractNoPicUrl = additionalContractNoPicUrl;
	}
	
	
}
