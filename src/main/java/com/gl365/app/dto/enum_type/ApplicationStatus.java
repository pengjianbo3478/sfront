package com.gl365.app.dto.enum_type;

import com.gl365.app.dto.ValuedEnum;

/**
 * 申请状态
 *
 * @author Chen, Zhuhui
 */
public enum ApplicationStatus implements ValuedEnum<Integer> {

	/**
	 * 新申请
	 */
	NEW(1),
	/**
	 * 业务员申请, 仅仅针对发展业务员需要邀请人审核
	 */
	PERSONAL_ASSESSMENT_PENDING(10),
	/**
	 * 业务员申请驳回
	 */
	PERSONAL_ASSESSMENT_REJECTED(15),
	/**
	 * （机具申请）风控审核通过
	 */
	MP_RISK_ASSESSMENT_PASSED(310),

	/**
	 * （机具申请）风控审核驳回
	 */
	MP_RISK_ASSESSMENT_REJECTED(315),

	/**
	 * （机具申请）运营审核通过
	 */
	MP_OPERATOR_ASSESSMENT_PASSED(320),

	/**
	 * （机具申请）运营审核驳回
	 */
	MP_OPERATOR_ASSESSMENT_REJECTED(325),


	/**
	 * （机具申请）支付公司审核通过
	 */
	MP_PAYMENT_COMPANY_ASSESSMENT_PASSED(330),

	/**
	 * （机具申请）支付公司审核不通过
	 */
	MP_PAYMENT_COMPANY_ASSESSMENT_REJECTED(335),

	/**
	 * 审核已通过
	 */
	PASSED(999),

	/**
	 * 审核未通过
	 */
	REJECTED(998),

	/**
	 * 审核撤回
	 */
	REVOKED(997);

	private final Integer value;

	ApplicationStatus(Integer value) {
		this.value = value;
	}

	@Override
	public Integer value() {
		return this.value;
	}

}
