package com.utwente.soa.team8MovieProject.controller;


import com.utwente.soa.team8MovieProject.services.InvoicePaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cinema")
@Api(value = "Best Cinema")
public class IndexController {

    @Autowired
    private InvoicePaymentService invoicePaymentService;

    @ApiOperation(value = "Accepts the payment for the specified movie", response = String.class)
    @RequestMapping(path = "/movie/{id}/payment", method = RequestMethod.GET)
    public String buyTicket(@PathVariable String id) {
        return invoicePaymentService.pay(id);
    }

}