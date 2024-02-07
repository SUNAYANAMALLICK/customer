package com.ldms.customer.service;

import com.ldms.customer.entity.Customer;
import com.ldms.customer.exception.InvalidCustomerException;
import com.ldms.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.ldms.customer.constant.CustomerConstants.ID_LENGTH;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    private void validateCustomerId(Long customerId) {
        if (customerId == null || customerId<=99){
            throw new InvalidCustomerException(ID_LENGTH + customerId);
        } else if (customerRepository.findByCustomerId(customerId).isPresent()) {
            throw new InvalidCustomerException(ID_LENGTH + customerId);
        }
    }

    public void create(final Customer customer) {
        validateCustomerId(customer.getCustomerId());
        customerRepository.save(customer);
    }

    public Boolean find(final Long customerId) {
        return customerRepository.findByCustomerId(customerId).isPresent();
        }

}
