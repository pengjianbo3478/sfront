package com.gl365.app.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoyilong on 2017/6/3.
 */
public class MerchantIndustry implements Serializable {

	private static final long serialVersionUID = 4664925018788284928L;

	private Integer categoryId;

	private String categoryName;
	
	private BigDecimal minGlFeeRate;

    private BigDecimal maxGlFeeRate;
    
    private BigDecimal minSaleRate;

    private BigDecimal maxSaleRate;

	private List<MerchantIndustry> subCategory = new ArrayList<>();

	@ApiModelProperty("行业ID")
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@ApiModelProperty("行业名称")
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@ApiModelProperty("子行业")
	public List<MerchantIndustry> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(List<MerchantIndustry> subCategory) {
		this.subCategory = subCategory;
	}

	@ApiModelProperty("最小返佣率%")
	public BigDecimal getMinGlFeeRate() {
		return minGlFeeRate;
	}

	public void setMinGlFeeRate(BigDecimal minGlFeeRate) {
		this.minGlFeeRate = minGlFeeRate;
	}

	@ApiModelProperty("最大返佣率%")
	public BigDecimal getMaxGlFeeRate() {
		return maxGlFeeRate;
	}

	public void setMaxGlFeeRate(BigDecimal maxGlFeeRate) {
		this.maxGlFeeRate = maxGlFeeRate;
	}

	@ApiModelProperty("最小返利率(返豆率)")
	public BigDecimal getMinSaleRate() {
		return minSaleRate;
	}

	public void setMinSaleRate(BigDecimal minSaleRate) {
		this.minSaleRate = minSaleRate;
	}

	@ApiModelProperty("最大返利率(返豆率)")
	public BigDecimal getMaxSaleRate() {
		return maxSaleRate;
	}

	public void setMaxSaleRate(BigDecimal maxSaleRate) {
		this.maxSaleRate = maxSaleRate;
	}
	
	
}
