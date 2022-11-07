package com.nttdata.bootcamp.msbootcoinexchange.infrastructure.persistence.adapter.mongo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.msbootcoinexchange.application.outgoing.AcceptExchangePort;
import com.nttdata.bootcamp.msbootcoinexchange.application.outgoing.ApplyForExchangePort;
import com.nttdata.bootcamp.msbootcoinexchange.application.outgoing.FindAllPublishExchangePort;
import com.nttdata.bootcamp.msbootcoinexchange.application.outgoing.FindExchangeByIdAndStatePort;
import com.nttdata.bootcamp.msbootcoinexchange.application.outgoing.PublishExchangePort;
import com.nttdata.bootcamp.msbootcoinexchange.application.outgoing.UpdateExchangePort;
import com.nttdata.bootcamp.msbootcoinexchange.domain.enums.ExchangeState;
import com.nttdata.bootcamp.msbootcoinexchange.domain.model.Exchange;
import com.nttdata.bootcamp.msbootcoinexchange.infrastructure.persistence.entity.ExchangeEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ExchangeRepositoryAdapter implements PublishExchangePort,
												  ApplyForExchangePort,
												  AcceptExchangePort,
													FindAllPublishExchangePort,
													UpdateExchangePort,
													FindExchangeByIdAndStatePort{



  @Autowired
  private ReactiveMongoExchangeRepository reactiveMongoDB;

@Override
public Mono<Exchange> findExchangeByIdAndState(String exchangeId) {
	return reactiveMongoDB.findById(exchangeId).map(ExchangeEntity::toExchange);
}

@Override
public Mono<Exchange> updateExchange(Exchange exchange) {
	
	return reactiveMongoDB.save(ExchangeEntity.toExchangeEntity(exchange))
			 .map(ExchangeEntity::toExchange);
}

@Override
public Flux<Exchange> findAllPublishExchange() {
	return reactiveMongoDB.findAll()
			.flatMapIterable(e -> Arrays.asList(ExchangeEntity.toExchange(e)));
}

@Override
public Mono<Exchange> publishExchange(Exchange exchange) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
	LocalDateTime now = LocalDateTime.now();
    String createDate = now.format(formatter);
    exchange.setExchangePublishDate(createDate);
    exchange.setExchangeState(ExchangeState.PUBLISH.getValue());
	return reactiveMongoDB.insert(ExchangeEntity.toExchangeEntity(exchange))
			  .map(ExchangeEntity::toExchange);
}

@Override
public Mono<Exchange> acceptExchange(Exchange exchange,Exchange exchangeUpdate) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
	LocalDateTime now = LocalDateTime.now();
	String acceptDate = now.format(formatter);
	exchange.setExchangeAcceptDate(acceptDate);
	exchange.setExchangeState(ExchangeState.ACCEPT.getValue());
	Long exchangeTransactionNumber=System.currentTimeMillis();
	exchange.setExchangeTransactionNumber(String.valueOf(exchangeTransactionNumber));
	return reactiveMongoDB.save(ExchangeEntity.toExchangeEntity(exchange))
			 .map(ExchangeEntity::toExchange);
}

@Override
public Mono<Exchange> applyForExchange(Exchange exchange,Exchange exchangeUpdate) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
	LocalDateTime now = LocalDateTime.now();
	String applyForDate = now.format(formatter);
	exchange.setExchangeApplyForDate(applyForDate);
	exchange.setExchangeState(ExchangeState.APPLYFOR.getValue());
	exchange.setExchangeApplyForAmount(exchangeUpdate.getExchangeApplyForAmount());
	exchange.setWalletExchangeApplyForId(exchangeUpdate.getWalletExchangeApplyForId());
	exchange.setExchangeApplyForYankiCellphone(exchangeUpdate.getExchangeApplyForYankiCellphone());
	exchange.setExchangeApplyForAccount(exchangeUpdate.getExchangeApplyForAccount());
	exchange.setExchangeApplyForBank(exchangeUpdate.getExchangeApplyForBank());
	exchange.setExchangeApplyForAmount(exchangeUpdate.getExchangeApplyForAmount());
	return reactiveMongoDB.save(ExchangeEntity.toExchangeEntity(exchange))
			 .map(ExchangeEntity::toExchange);
}

}
