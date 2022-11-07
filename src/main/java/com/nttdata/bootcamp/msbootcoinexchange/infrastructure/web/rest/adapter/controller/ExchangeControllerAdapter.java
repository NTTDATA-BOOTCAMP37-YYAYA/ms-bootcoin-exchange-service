package com.nttdata.bootcamp.msbootcoinexchange.infrastructure.web.rest.adapter.controller;


import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.msbootcoinexchange.application.incoming.AcceptExchangeUseCase;
import com.nttdata.bootcamp.msbootcoinexchange.application.incoming.ApplyForExchangeUseCase;
import com.nttdata.bootcamp.msbootcoinexchange.application.incoming.FindAllPublishExchangeUseCase;
import com.nttdata.bootcamp.msbootcoinexchange.application.incoming.FindExchangeByIdAndStateUseCase;
import com.nttdata.bootcamp.msbootcoinexchange.application.incoming.PublishExchangeUseCase;
import com.nttdata.bootcamp.msbootcoinexchange.domain.enums.ExchangeState;
import com.nttdata.bootcamp.msbootcoinexchange.domain.model.Exchange;
import com.nttdata.bootcamp.msbootcoinexchange.infrastructure.web.rest.adapter.controller.response.ResponseExchange;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**.*/
@RestController
@RequestMapping("/bootcoinExchange")
public class ExchangeControllerAdapter {

  final Logger logger = LoggerFactory.getLogger(ExchangeControllerAdapter.class);
  
  @Autowired
  private  PublishExchangeUseCase publishExchangeUseCase;

  @Autowired
  private  FindAllPublishExchangeUseCase findAllPublishExchangeUseCase;

  @Autowired
  private  FindExchangeByIdAndStateUseCase findExchangeByIdAndStateUseCase;

  @Autowired
  private  ApplyForExchangeUseCase appplyForExchangeUseCase;

  @Autowired
  private  AcceptExchangeUseCase acceptExchangeUseCase;

  /**.*/
  @PostMapping("/publishExchange")
  public Mono<ResponseEntity<ResponseExchange>> publishExchange(@RequestBody Exchange exchange) {
    return   publishExchangeUseCase.publishExchange(exchange)
                    .flatMap(newExchange -> Mono.just(new ResponseEntity<ResponseExchange>(
                    new ResponseExchange(HttpStatus.SC_NOT_FOUND, newExchange, "Exchange has been publish"),
                    null, HttpStatus.SC_NOT_FOUND)))
    .onErrorResume(e->Mono.just(new ResponseEntity<ResponseExchange>(
        new ResponseExchange(HttpStatus.SC_INTERNAL_SERVER_ERROR, null, e.getMessage()),
        null, HttpStatus.SC_INTERNAL_SERVER_ERROR)));
  
  }
  
  /**.*/
  @PatchMapping("/applyForExchange")
  public Mono<ResponseEntity<ResponseExchange>> applyExchange(@RequestBody Exchange exchange) {
	  return  findExchangeByIdAndStateUseCase.findExchangeByIdAndState(exchange.getExchangeId(),ExchangeState.PUBLISH.getValue())
			    .flatMap(e -> appplyForExchangeUseCase.applyForExchange(e,exchange)
	                    .flatMap(updateExchange -> Mono.just(new ResponseEntity<ResponseExchange>(
	                            new ResponseExchange(HttpStatus.SC_NOT_FOUND, updateExchange, "Exchange apply for exchange succesfully, wait for accept"),
	                            null, HttpStatus.SC_NOT_FOUND))))
			    .switchIfEmpty(Mono.just(new ResponseEntity<ResponseExchange>(
	                    new ResponseExchange(HttpStatus.SC_NOT_FOUND, null, "Exchange in publish state not found "),
	                    null, HttpStatus.SC_NOT_FOUND)))
			    .onErrorResume(e -> Mono.just(new ResponseEntity<ResponseExchange>(
			            new ResponseExchange(HttpStatus.SC_INTERNAL_SERVER_ERROR, null, e.getMessage()),
			            null, HttpStatus.SC_INTERNAL_SERVER_ERROR)));
  
  }
  
  @GetMapping("/findPublishees")
  public  Flux<Exchange> FindAllExchangePendingToAccept(){
	  return findAllPublishExchangeUseCase.findAllPublishExchange();
  }

  @PatchMapping("/acceptExchange")
  public Mono<ResponseEntity<ResponseExchange>> acceptRequestExchange(@RequestBody Exchange exchange){
	  return  findExchangeByIdAndStateUseCase.findExchangeByIdAndState(exchange.getExchangeId(),ExchangeState.APPLYFOR.getValue())
			    .flatMap(e -> acceptExchangeUseCase.acceptExchange(e,exchange)
	                    .flatMap(updateExchange -> Mono.just(new ResponseEntity<ResponseExchange>(
	                            new ResponseExchange(HttpStatus.SC_NOT_FOUND, updateExchange, "Exchange accept exchange succesfully, wait for validate proccess"),
	                            null, HttpStatus.SC_NOT_FOUND))))
			    .switchIfEmpty(Mono.just(new ResponseEntity<ResponseExchange>(
	                    new ResponseExchange(HttpStatus.SC_NOT_FOUND, null, "Exchange in apply for state not found "),
	                    null, HttpStatus.SC_NOT_FOUND)))
			    .onErrorResume(e -> Mono.just(new ResponseEntity<ResponseExchange>(
			            new ResponseExchange(HttpStatus.SC_INTERNAL_SERVER_ERROR, null, e.getMessage()),
			            null, HttpStatus.SC_INTERNAL_SERVER_ERROR)));

  }

}
