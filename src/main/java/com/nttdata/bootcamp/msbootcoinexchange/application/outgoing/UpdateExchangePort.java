package com.nttdata.bootcamp.msbootcoinexchange.application.outgoing;

import com.nttdata.bootcamp.msbootcoinexchange.domain.model.Exchange;

import reactor.core.publisher.Mono;

public interface UpdateExchangePort {
	Mono<Exchange> updateExchange(Exchange exchange);
}
