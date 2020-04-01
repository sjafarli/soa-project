package com.utwente.soa.team8MovieProject.exceptions;

import java.util.NoSuchElementException;

public class PaymentUnsuccessfulException extends NoSuchElementException {
    public PaymentUnsuccessfulException() {
        super("Payment unsuccessful, proxy not responding ");
    }
}
