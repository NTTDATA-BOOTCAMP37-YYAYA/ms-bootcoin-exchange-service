package com.nttdata.bootcamp.msbootcoinexchange.application.incoming;

import com.nttdata.bootcamp.msbootcoinexchange.domain.model.Exchange;

import reactor.core.publisher.Mono;

public interface ApplyForExchangeUseCase {

	Mono<Exchange> applyForExchange(Exchange exchange,Exchange exchangeUpdate);
}
