package com.ldms.customer.service;


import com.ldms.customer.entity.Customer;
import com.ldms.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        // Perform setup before each test
        customer = Customer.builder().id(20L).name("John Daw").customerId(1203L).title("Mr.")
                .dateOfBirth(LocalDateTime.of(1990,12,22,13,20)).build();

    }

    @Test
    void validateCustomerById_ValidId() {

        when(customerRepository.findByCustomerId(customer.getCustomerId())).thenReturn(Optional.of(customer));
        Boolean result = customerService.find(customer.getCustomerId());
        assertNotNull(result);
        assertEquals(Boolean.TRUE, result);
    }



}
