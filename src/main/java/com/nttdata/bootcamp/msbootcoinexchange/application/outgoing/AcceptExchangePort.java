package com.nttdata.bootcamp.msbootcoinexchange.application.outgoing;

import com.nttdata.bootcamp.msbootcoinexchange.domain.model.Exchange;

import reactor.core.publisher.Mono;

public interface AcceptExchangePort {

	Mono<Exchange> acceptExchange(Exchange exchange,Exchange exchangeUpdate);
}
