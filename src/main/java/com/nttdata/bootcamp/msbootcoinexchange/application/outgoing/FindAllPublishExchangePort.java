package com.nttdata.bootcamp.msbootcoinexchange.application.outgoing;

import com.nttdata.bootcamp.msbootcoinexchange.domain.model.Exchange;

import reactor.core.publisher.Flux;

public interface FindAllPublishExchangePort {

	Flux<Exchange> findAllPublishExchange();
}
