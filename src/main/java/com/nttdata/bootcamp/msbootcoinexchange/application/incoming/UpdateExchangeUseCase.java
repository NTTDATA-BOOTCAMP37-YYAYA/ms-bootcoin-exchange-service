package com.nttdata.bootcamp.msbootcoinexchange.application.incoming;

import com.nttdata.bootcamp.msbootcoinexchange.domain.model.Exchange;

import reactor.core.publisher.Mono;

public interface UpdateExchangeUseCase {
	Mono<Exchange> updateExchange(Exchange exchange,Exchange exchangeUpdate);
}
