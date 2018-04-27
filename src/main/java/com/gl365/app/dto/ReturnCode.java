package com.gl365.app.dto;

/**
 * @author caoyilong
 */
public class ReturnCode {

	public enum System {
		/**
		 * 系统保留100以下的错误码,提示可以调整,code不能变
		 */
		SUCCESS("000000", "成功"),

		NEED_LOGIN("000001", "请先登录"),

		PARAM_NULL("000002", "参数为空"),

		PARAM_ERROR("000003", "参数非法"),

		VERIFY_SIGN_FAIL("000004", "验签失败"),

		TOKEN_TIMEOUT("000005", "Token失效"),

		REQUEST_IS_NULL("000006", "错误请求"),

		NO_PERMISSION("000007", "您没有执行此操作的权限"),

		SYSTEM_DATA_EXECEPTION("000008", "系统数据异常"),

		SYSTEM_TIME_OUT("000098", "请求频繁"),

		SYSTEM_ERROR("000099", "服务器错误，请稍后重试"),;

		private String code;

		private String msg;

		private System(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

	public enum AgentUser {
		ID_PWD_MATCHING_ERROR("100001", "用户名或密码不正确"),
		VALID_ID_CARD_ERROR("100002", "实名认证不通过"),
		VALID_MOBILE_ERROR("100003", "手机验证不通过"),
		NO_DEVICE_UNUSER_ERROR("100004", "设备不常用"),
		AUTH_CODE_ERROR("100005", "验证码错误"),
		PWD_MATCHING_ERROR("100006", "旧密码不正确"),
		PWD_SAME_ERROR("100007", "新旧密码不能相同"),
		PHONE_SAME_ERROR("100008", "手机号不能相同"),
		ID_CARD_ALREADY_REGISTER_ERROR("100009", "身份证信息已经被注册"),
		PHONE_ALREADY_REGISTER_ERROR("100010", "手机号已经被注册"),
		NO_USER_INFO_ERROR("100011", "手机号不存在,请确认手机号是否正确"),
		IMAGE_CODE_TIME_OUT("100012", "图片验证超时"),
		IMAGE_CODE_ERROR("100013", "图片验证不正确"),
		PASSWORD_COUNT_ERROR("100014", "您的输错次数已超限，请明天登录"),
		USER_STATUS_ERROR("100015", "用户状态异常,可能被禁用或者已注销"),
		NO_ADD_USER_ERROR("100016", "该用户未关联商家,请联系商家管理员"),
		NO_AGENT_INFO_ERROR("100017", "没有用户信息"),
		PHONE_NOREGIST_ERROR("100018", "手机号未注册"),
		VALID_BANK_ERROR("100019", "银行卡认证不通过"),
		USER_PENDING("100020", "您的申请正在审核中"),
		ID_CARD_DISACCORD_ERROR("100020", "您的身份信息和实名信息不一致"),
		;
		private String code;

		private String msg;

		private AgentUser(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

	public enum Merchant {
		INCORRECT_ERROR("200001", "手机号不存在,请确认手机号是否正确"),;
		private String code;

		private String msg;

		private Merchant(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

    public enum Voucher {
        NOT_FOUND_ERROR("300001", "找不到该申请单"),;

        private String code;
        private String msg;

        Voucher(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public enum Bank {

        BANK_CARD_NOT_VALIDATE("400001", "对不起,输入内容验证失败,请检查开户名,卡号和开户银行名称是否正确"),
        BANK_CARD_BIN_VALIDATE("400002", "银行卡与开户行不匹配"),
        ;

        private String code;
        private String msg;

        Bank(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
