package com.ldms.customer.controller;

import com.ldms.customer.entity.Customer;
import com.ldms.customer.exception.CustomerNotFoundException;
import com.ldms.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ldms.customer.constant.CustomerConstants.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(ROOT_PATH)
public class CustomerController {

    @Autowired
    CustomerService customerService;
    // get
    @GetMapping(value = "/{customerId}")
    public ResponseEntity<Boolean> find(@PathVariable final Long customerId){
        Boolean status = customerService.find(customerId) ;
        if(!status){
            throw new CustomerNotFoundException(String.format("%s: %s",CUST_NOT_FOUND, customerId));
        }
        return new ResponseEntity<>(status , OK);
    }

    // post
    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid final Customer customer){
        customerService.create(customer);
        return new ResponseEntity<>(CUSTOMER_CREATED, CREATED);
    }

}
