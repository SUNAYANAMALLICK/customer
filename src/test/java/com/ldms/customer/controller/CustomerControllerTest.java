package com.ldms.customer.controller;

import com.ldms.customer.entity.Customer;
import com.ldms.customer.repository.CustomerRepository;
import com.ldms.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    private String validCustomer = "{\"customerId\": 123, \"name\": \"Master John Doe\",  \"dateOfBirth\": \"2024-01-31T14:36:07.158Z\" , \"title\": \"BBB\"}";

    private String invalidCustomer = "{\"customerId\": 12, \"name\": \"John Doe\",  \"dateOfBirth\": \"2020-01-31T14:36:07.158Z\" , \"title\": \"BBB\"}";

   private final String path = "/api/v1/customer";


    private Customer customer;

    @BeforeEach
    void setUp() {
        // Perform setup before each test
         customer = Customer.builder().name("John Daw").customerId(1203L).title("Mr.")
                .dateOfBirth(LocalDateTime.of(1990,12,22,13,20)).build();

    }

    @Test
    void createCustomer_ValidInput_ReturnsCreated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validCustomer))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Customer Created."));
        customerRepository.deleteAll();
    }

   @Test
    void createCustomer_InvalidInput_Invalid_CustomerID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidCustomer))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void createCustomer_ValidInput_Get_ReturnsCreated() throws Exception {

        Long id = customerRepository.save(customer).getId();

        mockMvc.perform(MockMvcRequestBuilders.get(path + "/{customerId}", 1203L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(Boolean.TRUE)));
        customerRepository.deleteById(id);
    }

    @Test
    void getCustomerById_InvalidId_ReturnsNotFound() throws Exception {
        // Mocking the service response for an invalid ID
        mockMvc.perform(MockMvcRequestBuilders.get(path + "/{customerId}", 12L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Customer not found with ID : 12"));

    }


}
