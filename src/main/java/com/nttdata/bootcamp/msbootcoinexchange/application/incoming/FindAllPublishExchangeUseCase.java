package com.nttdata.bootcamp.msbootcoinexchange.application.incoming;

import com.nttdata.bootcamp.msbootcoinexchange.domain.model.Exchange;

import reactor.core.publisher.Flux;

public interface FindAllPublishExchangeUseCase {

	Flux<Exchange> findAllPublishExchange();
}
