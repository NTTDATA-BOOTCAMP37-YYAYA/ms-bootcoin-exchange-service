package com.nttdata.bootcamp.msbootcoinexchange.infrastructure.web.rest.adapter.controller.response;

import com.nttdata.bootcamp.msbootcoinexchange.domain.model.Exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**.*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseExchange {
  
  private int httpStatus;
  private Exchange exchange;
  private String message;

}