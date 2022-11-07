package com.nttdata.bootcamp.msbootcoinexchange.application.outgoing;

import com.nttdata.bootcamp.msbootcoinexchange.domain.model.Exchange;

import reactor.core.publisher.Mono;

public interface ApplyForExchangePort {

	Mono<Exchange> applyForExchange(Exchange exchange,Exchange exchangeUpdate);
}
