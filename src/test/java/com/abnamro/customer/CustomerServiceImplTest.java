package com.abnamro.customer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    List<String> availableCountries; ;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterCustomer() {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setUsername("user");
        customerRequest.setCountry("Netherlands");
        customerRequest.setDateOfBirth(LocalDate.now().minusYears(20).toString());

        when(customerRepository.existsByUsername(customerRequest.getUsername())).thenReturn(false);
        when(availableCountries.contains("Netherlands")).thenReturn(true);
        when(customerRepository.existsByUsername(customerRequest.getUsername())).thenReturn(false);

        Customer customer = customerService.registerCustomer(customerRequest);
        assertEquals(customerRequest.getUsername(), customer.getUsername());

    }

    @Test
    public void testGenerateToken() {
        String username = "user";
        String token = customerService.generateToken(username);
        assertEquals(36, token.length());
    }


}