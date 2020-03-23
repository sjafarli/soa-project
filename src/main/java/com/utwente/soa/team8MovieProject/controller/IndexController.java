package com.utwente.soa.team8MovieProject.controller;


import com.utwente.soa.team8MovieProject.services.InvoicePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class IndexController {

    @Autowired
    private InvoicePaymentService invoicePaymentService;

    @RequestMapping (path = "/{id}/payment", method = RequestMethod.GET)

    public String buyTicket(@PathVariable int id){
       return invoicePaymentService.pay(id);
    }

}
