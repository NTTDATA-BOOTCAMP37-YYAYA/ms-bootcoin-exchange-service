package com.nttdata.bootcamp.msbootcoinexchange.application.outgoing;

import com.nttdata.bootcamp.msbootcoinexchange.domain.model.Exchange;

import reactor.core.publisher.Mono;

public interface FindExchangeByIdAndStatePort {

	Mono<Exchange> findExchangeByIdAndState(String exchangeId);
}
