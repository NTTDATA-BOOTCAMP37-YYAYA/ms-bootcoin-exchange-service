package com.nttdata.bootcamp.msbootcoinexchange.infrastructure.persistence.adapter.mongo;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.msbootcoinexchange.infrastructure.persistence.entity.ExchangeEntity;

public interface ReactiveMongoExchangeRepository extends ReactiveMongoRepository<ExchangeEntity, String>{

 

}