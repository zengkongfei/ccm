package com.ccm.api.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class CurrencyConverterTest {
	
	@Test
	public void priceAsSimpleStringTest() {
	    assertEquals("123.22", CurrencyConverter.priceWithoutDecimal(123.22d));
	    assertEquals("123,123.22", CurrencyConverter.priceWithoutDecimal(123123.22d));
	    assertEquals("123,123.2", CurrencyConverter.priceWithoutDecimal(123123.2d));
	    assertEquals("123", CurrencyConverter.priceWithoutDecimal(123d));
	    
	    assertEquals("123.22", CurrencyConverter.priceWithDecimal(123.22d));
        assertEquals("123,123.22", CurrencyConverter.priceWithDecimal(123123.22d));
        assertEquals("123,123.20", CurrencyConverter.priceWithDecimal(123123.2d));
        assertEquals("123.00", CurrencyConverter.priceWithDecimal(123d));
        
        assertEquals("123.22", CurrencyConverter.priceToString(123.22d));
        assertEquals("123,123.22", CurrencyConverter.priceToString(123123.22d));
        assertEquals("123,123.20", CurrencyConverter.priceToString(123123.2d));
        assertEquals("123", CurrencyConverter.priceToString(123d));
	}
	

}
