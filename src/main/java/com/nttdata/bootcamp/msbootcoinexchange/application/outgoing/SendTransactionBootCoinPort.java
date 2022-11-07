package com.nttdata.bootcamp.msbootcoinexchange.application.outgoing;

import com.nttdata.bootcamp.msbootcoinexchange.domain.model.Exchange;

public interface SendTransactionBootCoinPort {
	
	void sendTransactionBootCoin(Exchange exchange);

}
