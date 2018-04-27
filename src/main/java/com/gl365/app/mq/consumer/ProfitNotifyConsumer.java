package com.gl365.app.mq.consumer;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gl365.app.dto.Merchant;
import com.gl365.app.mq.dto.NotifyProfitProductMQ;
import com.gl365.app.mq.dto.PayProfit;
import com.gl365.app.mq.dto.ProfitNotifyDto;
import com.gl365.app.mq.dto.PushMQ;
import com.gl365.app.mq.producer.JPushProducer;
import com.gl365.app.remote.message.MessageService;
import com.gl365.app.remote.message.dto.MQMessageTypeEnum;
import com.gl365.app.remote.message.dto.MQSystemTypeEnum;
import com.gl365.app.remote.message.dto.MsgReq;
import com.gl365.app.remote.sales.MerchantService;
import com.gl365.app.remote.settlement.enum_type.AccountProfitType;
import com.gl365.app.remote.settlement.enum_type.ProfitType;
import com.gl365.app.utils.BigDecimaluitl;
import com.gl365.app.utils.JsonUtils;

@Component("profit-notify-consumer")
public class ProfitNotifyConsumer extends MQConsumer<NotifyProfitProductMQ> {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JPushProducer jPushProducer;

	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private MessageService messageService;

	@Override
	public void execute(NotifyProfitProductMQ command) {
		logger.info("========profit-notify-consumer=====>begin:{}", JsonUtils.toJsonString(command));
		PushMQ pushMQ = new PushMQ();
		pushMQ.setApp("sfront");
		ProfitNotifyDto profitNotifyDto = getContent(command);
		if(profitNotifyDto != null){
			logger.info("content={}", JsonUtils.toJsonString(profitNotifyDto));
			pushMQ.setUid(profitNotifyDto.getOwnerId());
			pushMQ.setContent(JsonUtils.toJsonString(profitNotifyDto));
			logger.info("========profit-notify-consumer=====>end:{}", JsonUtils.toJsonString(pushMQ));
			buildMsg(pushMQ, MQMessageTypeEnum.MSG_00.getCode(), profitNotifyDto.getSystemType());
			jPushProducer.send(pushMQ);
		}else{
			logger.info("========profit-notify-consumer=====>end,exception{}", JsonUtils.toJsonString(pushMQ));
		}
	}
	
	// 写message服务
		private void buildMsg(PushMQ command, String messageType, String messageResultType) {
			MsgReq req = new MsgReq();
			req.setAlias(command.getUid());
			req.setAppType(command.getApp());
			req.setMessage(command.getContent());
			req.setMessageType(messageType);
			req.setMessageResultType(messageResultType);
			messageService.addMsg(req);
		}

	private ProfitNotifyDto getContent(NotifyProfitProductMQ command) {
		//获取提成明细
		ProfitNotifyDto profitNotifyDto = handleProfitDetail(command);
		//产品说,金额小于0.01时不推送
		if (null == profitNotifyDto || profitNotifyDto.getOwnerProfitAmount() == null
				|| (new BigDecimal("0.01").compareTo(new BigDecimal(profitNotifyDto.getOwnerProfitAmount())) == 1)) {
			logger.info("金额小于0.01{}", JsonUtils.toJsonString(profitNotifyDto));
			return null;
		}
		
		//保留两位
		BigDecimal ownerProfitAmount = new BigDecimal(profitNotifyDto.getOwnerProfitAmount());
		profitNotifyDto.setOwnerProfitAmount(new BigDecimal(BigDecimaluitl.setScaleStr(ownerProfitAmount)));
		
		profitNotifyDto.setSystemType(MQSystemTypeEnum.GL_PAYMENT.getCode());
		if (StringUtils.isBlank(profitNotifyDto.getMerchantName()) && StringUtils.isNotBlank(profitNotifyDto.getMerchantNo())) {
			Merchant merchant = merchantService.findByGeileMerchantSn(profitNotifyDto.getMerchantNo());
			if (merchant != null) {
				profitNotifyDto.setMerchantName(merchant.getExternalOperationName());
			}
		}
		return profitNotifyDto;
	}

	private ProfitNotifyDto handleProfitDetail(NotifyProfitProductMQ command) {
		//提成明细列表
		List<PayProfit> profitList = command.getProfitBody().getPayProfits();
		//组装返回数据
		ProfitNotifyDto target = new ProfitNotifyDto();
		String transType = command.getTransType();
		String ownerId = null;
		String ownerType = null;
		//获取提成对象和提成对象类型
	    for (PayProfit source : profitList) {
	    	if(ProfitType.AGENT_DEVELOP_FEE.value().equals(source.getProfitType())){
	    		ownerId = source.getOwnerId();
	    		ownerType = source.getOwnerType();
	    		BeanUtils.copyProperties(source, target);
	    		target.setPayTime(source.getPayTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	    		target.setTransType(transType);
	    		break;
	    	}
		}
	    
	    //ownerId,ownerType不可为空
	    if(ownerId == null || ownerType == null){
	    	logger.warn("推送消息格式不正确,ownerId={},ownerType={}", ownerId, ownerType);
	    	return null;
	    }
	    
	    //组装提成金额
		if (ownerType.equals(AccountProfitType.SALESMAN_AGENT.getKey())
				|| ownerType.equals(AccountProfitType.COMPANY.getKey())) {
			// ownerType 为业务员和机构时直接推送
			return target;
		} else if (ownerType.equals(AccountProfitType.CITY.getKey())) {
			// ownerType 为市级代理
			BigDecimal ownerProfitAmount = BigDecimal.valueOf(0);
			for (PayProfit source : profitList) {
				if (ownerId.equals(source.getOwnerId())
						&& (ProfitType.AGENT_DEVELOP_FEE.value().equals(source.getProfitType())
								|| ProfitType.CITY_OR_COUNT_SERVICE_FEE.value().equals(source.getProfitType())
								|| ProfitType.CITY_SERVICE_FEE.value().equals(source.getProfitType()))) {
					ownerProfitAmount = ownerProfitAmount.add(source.getOwnerProfitAmount());
				}
			}
			target.setOwnerProfitAmount(ownerProfitAmount);
			return target;
		} else if (ownerType.equals(AccountProfitType.DISTRICT.getKey())) {
			// ownerType 为县级代理
			BigDecimal ownerProfitAmount = BigDecimal.valueOf(0);
			for (PayProfit source : profitList) {
				if (ownerId.equals(source.getOwnerId())
						&& (ProfitType.AGENT_DEVELOP_FEE.value().equals(source.getProfitType())
								|| ProfitType.CITY_OR_COUNT_SERVICE_FEE.value().equals(source.getProfitType()))) {
					ownerProfitAmount = ownerProfitAmount.add(source.getOwnerProfitAmount());
				}
			}
			target.setOwnerProfitAmount(ownerProfitAmount);
			return target;
		}else{
			return null;
		}
		
	}

}
