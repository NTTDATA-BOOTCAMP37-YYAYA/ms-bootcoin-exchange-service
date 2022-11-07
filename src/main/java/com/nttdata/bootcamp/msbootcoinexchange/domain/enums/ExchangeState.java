package com.nttdata.bootcamp.msbootcoinexchange.domain.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**.*/
@AllArgsConstructor
@NoArgsConstructor
public enum ExchangeState {
	
	
PUBLISH("P"),
APPLYFOR("F"),
ACCEPT("A"),
VALIDATEPROCESS("V"),
EXCHANGESUCCESS("S"),
EXCHANGEERROR("E");
private String value;
public String getValue() {
	 return value;
}
	
}
