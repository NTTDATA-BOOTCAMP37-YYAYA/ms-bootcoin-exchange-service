package com.nttdata.bootcamp.msbootcoinexchange.infrastructure.web.rest.adapter.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFindPendingAccept {
	
	private String exchangeId;
	private double exchangeAmount;
	private String exchangeTypePayId;
	private String exchangeCreateDate;
	private String exchangeState;
  
}
