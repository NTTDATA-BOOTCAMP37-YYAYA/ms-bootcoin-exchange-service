package com.nttdata.bootcamp.msbootcoinexchange.infrastructure.producer.adapter.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.nttdata.bootcamp.msbootcoinexchange.application.outgoing.SendTransactionBootCoinPort;
import com.nttdata.bootcamp.msbootcoinexchange.domain.model.Exchange;

import lombok.RequiredArgsConstructor;

/**.*/
@Component
@RequiredArgsConstructor
public class TransactionBootCoinProducerAdapter implements SendTransactionBootCoinPort {
  
  final  Logger logger = LoggerFactory.getLogger(TransactionBootCoinProducerAdapter.class);
  
  @Value("${kafka.topic.bootcoin.transaction.create.name}")
  private String topic;

  private  final KafkaTemplate<String, Exchange> kafkaTemplate;
  
  @Override
  public void sendTransactionBootCoin(Exchange exchange) {
    
    ListenableFuture<SendResult<String, Exchange>> future = kafkaTemplate.send(this.topic, exchange);
    
    future.addCallback(new ListenableFutureCallback<SendResult<String, Exchange>>() {

      @Override
      public void onSuccess(SendResult<String, Exchange> result) {
        logger.info("Message {} has been sent", result);
      }

      @Override
      public void onFailure(Throwable ex) {
        logger.error("Something went wrong with the bank transaction");
        
      }

    });
  }


}
