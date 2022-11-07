package com.nttdata.bootcamp.msbootcoinexchange.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.msbootcoinexchange.application.incoming.AcceptExchangeUseCase;
import com.nttdata.bootcamp.msbootcoinexchange.application.incoming.ApplyForExchangeUseCase;
import com.nttdata.bootcamp.msbootcoinexchange.application.incoming.FindAllPublishExchangeUseCase;
import com.nttdata.bootcamp.msbootcoinexchange.application.incoming.FindExchangeByIdAndStateUseCase;
import com.nttdata.bootcamp.msbootcoinexchange.application.incoming.PublishExchangeUseCase;
import com.nttdata.bootcamp.msbootcoinexchange.application.incoming.UpdateExchangeUseCase;
import com.nttdata.bootcamp.msbootcoinexchange.application.outgoing.AcceptExchangePort;
import com.nttdata.bootcamp.msbootcoinexchange.application.outgoing.ApplyForExchangePort;
import com.nttdata.bootcamp.msbootcoinexchange.application.outgoing.FindAllPublishExchangePort;
import com.nttdata.bootcamp.msbootcoinexchange.application.outgoing.FindExchangeByIdAndStatePort;
import com.nttdata.bootcamp.msbootcoinexchange.application.outgoing.PublishExchangePort;
import com.nttdata.bootcamp.msbootcoinexchange.application.outgoing.SendTransactionBootCoinPort;
import com.nttdata.bootcamp.msbootcoinexchange.application.outgoing.UpdateExchangePort;
import com.nttdata.bootcamp.msbootcoinexchange.domain.model.Exchange;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**.*/
@Service
public class ExchangeService implements PublishExchangeUseCase,
									ApplyForExchangeUseCase,
									AcceptExchangeUseCase,
									   FindAllPublishExchangeUseCase,
									   UpdateExchangeUseCase,
									   FindExchangeByIdAndStateUseCase{

	final  Logger logger = LoggerFactory.getLogger(ExchangeService.class);
	
	
	@Autowired
	private PublishExchangePort publishExchangePort;
	
	@Autowired
	private FindAllPublishExchangePort findAllPublishExchangePort;
	
	@Autowired
	private UpdateExchangePort updateExchangePort;
	
	@Autowired
	private FindExchangeByIdAndStatePort findExchangeByIdAndStatePort;
	
	@Autowired
	private ApplyForExchangePort applyForExchangePort;
	
	@Autowired
	private AcceptExchangePort acceptExchangePort;
	
	@Autowired
	private SendTransactionBootCoinPort sendTransactionBootCoinPort;
	
	@Override
	public Mono<Exchange> findExchangeByIdAndState(String exchangeId, String exchangeState) {
		return findExchangeByIdAndStatePort.findExchangeByIdAndState(exchangeId)
				.filter(e -> e.getExchangeState().equals(exchangeState));
	}
	
	@Override
	public Mono<Exchange> updateExchange(Exchange exchange,Exchange updatedExchange) {
		return updateExchangePort.updateExchange(exchange);
	}
	
	@Override
	public Flux<Exchange> findAllPublishExchange() {
		return findAllPublishExchangePort.findAllPublishExchange();
	}
	
	@Override
	public Mono<Exchange> publishExchange(Exchange exchange) {
		return publishExchangePort.publishExchange(exchange);
	}

	@Override
	public Mono<Exchange> acceptExchange(Exchange exchange,Exchange exchangeUpdate) {
		return acceptExchangePort.acceptExchange(exchange,exchangeUpdate)
				.map(exchangeAccept -> {
					sendTransactionBootCoinPort.sendTransactionBootCoin(exchangeAccept);
					return exchangeAccept;
				}
				);
	}

	@Override
	public Mono<Exchange> applyForExchange(Exchange exchange,Exchange exchangeUpdate) {
		return applyForExchangePort.applyForExchange(exchange,exchangeUpdate);
	}









}
