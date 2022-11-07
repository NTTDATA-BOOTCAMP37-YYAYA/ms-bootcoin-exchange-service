package com.nttdata.bootcamp.msbootcoinexchange.infrastructure.persistence.entity;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nttdata.bootcamp.msbootcoinexchange.domain.model.Exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "Exchange")
public class ExchangeEntity {

	@Id
	private String exchangeId;
	private String customerId;
	private String walletExchangePublishId;
	private double exchangePublishQuantityAllowed;
	private String exchangePublishDate;
	private String exchangePublishYankiCellPhone;
	private String exchangePublishAccount;
	private String exchangePublishTypePayId;
	
	
	private String walletExchangeApplyForId;
	private String exchangeApplyForYankiCellphone;
	private String exchangeApplyForBank;
	private String exchangeApplyForAccount;
	private double exchangeApplyForAmount;
	private String exchangeApplyForDate;
	
	private String exchangeState;
	private String exchangeAcceptDate;
	private String exchangeTransactionNumber;
	
  /**.*/
  public static Exchange toExchange(ExchangeEntity exchangeEntity) {
	Exchange exchange = new Exchange();
    BeanUtils.copyProperties(exchangeEntity, exchange);
    return exchange;
  }
  
  /**.*/
  public static ExchangeEntity toExchangeEntity(Exchange exchange) {
    ExchangeEntity ExchangeEntity = new ExchangeEntity();
    BeanUtils.copyProperties(exchange, ExchangeEntity);
    return ExchangeEntity;
  }
}
