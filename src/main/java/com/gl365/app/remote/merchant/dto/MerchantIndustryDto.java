package com.gl365.app.remote.merchant.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by caoyilong on 2017/6/8.
 */
public class MerchantIndustryDto implements Serializable {

	private static final long serialVersionUID = 5322434689942700443L;

	public static class MerchantIndustryResp {
		private Integer categoryId;

		private Integer parentCategoryId;

		private Integer categoryEvel;

		private String categoryName;

		private String flag;
		
		private BigDecimal minGlFeeRate;

	    private BigDecimal maxGlFeeRate;
	    
	    private BigDecimal minSaleRate;

	    private BigDecimal maxSaleRate;

		public Integer getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(String categoryId) {
			this.categoryId = Integer.valueOf(categoryId, 10);
		}

		public Integer getParentCategoryId() {
			return parentCategoryId;
		}

		public void setParentCategoryId(String parentCategoryId) {
			this.parentCategoryId = Integer.valueOf(parentCategoryId, 10);
		}

		public Integer getCategoryEvel() {
			return categoryEvel;
		}

		public void setCategoryEvel(String categoryEvel) {
			this.categoryEvel = Integer.valueOf(categoryEvel, 10);
		}

		public String getCategoryName() {
			return categoryName;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}

		public String getFlag() {
			return flag;
		}

		public void setFlag(String flag) {
			this.flag = flag;
		}

		public BigDecimal getMinGlFeeRate() {
			return minGlFeeRate;
		}

		public void setMinGlFeeRate(BigDecimal minGlFeeRate) {
			this.minGlFeeRate = minGlFeeRate;
		}

		public BigDecimal getMaxGlFeeRate() {
			return maxGlFeeRate;
		}

		public void setMaxGlFeeRate(BigDecimal maxGlFeeRate) {
			this.maxGlFeeRate = maxGlFeeRate;
		}

		public BigDecimal getMinSaleRate() {
			return minSaleRate;
		}

		public void setMinSaleRate(BigDecimal minSaleRate) {
			this.minSaleRate = minSaleRate;
		}

		public BigDecimal getMaxSaleRate() {
			return maxSaleRate;
		}

		public void setMaxSaleRate(BigDecimal maxSaleRate) {
			this.maxSaleRate = maxSaleRate;
		}
		
	}

	private String result;

	private String description;

	private List<MerchantIndustryResp> data;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<MerchantIndustryResp> getData() {
		return data;
	}

	public void setData(List<MerchantIndustryResp> data) {
		this.data = data;
	}
}
