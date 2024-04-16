package com.abnamro.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private Map<String,String> tokenStorage = new HashMap<>();
    private Set<Customer> onlineCustomers = new HashSet<>();

    @Value("${supported.countries}")
    private List<String> supportedCountries;

    @Override
    public Customer registerCustomer(CustomerRequest request) {
        // Check if username already exists
        if (customerRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        // Check if customer is from allowed countries
        if (!supportedCountries.contains(request.getCountry())) {
            throw new IllegalArgumentException("Customers from" + request.getCountry() + "are not allowed to register");
        }
        // Check if customer is above 18 years old Assuming dateOfBirth is in format "yyyy-MM-dd"
        LocalDate dob = LocalDate.parse(request.getDateOfBirth());
        LocalDate now = LocalDate.now();
        if (ChronoUnit.YEARS.between(dob, now) < 18) {
            throw new IllegalArgumentException("Customer must be above 18 years old to register");
        }
        // Generate IBAN
        String iban = generateIBAN();
        // Generate default password
        String password = UUID.randomUUID().toString().substring(0, 8);
        // Create customer entity
        Customer customer = new Customer(request.getName(), request.getAddress(), request.getDateOfBirth(), request.getIdDocument(), request.getUsername(), iban, password);
        customerRepository.save(customer);

        return customer;
    }

    @Override
    public String generateToken(String userName) {
        String token = UUID.randomUUID().toString();
        tokenStorage.put(userName, token);
        return token;
    }

    @Override
    public Customer logonCustomer(String token) {
        if (!tokenStorage.containsKey(token)) {
            throw new IllegalArgumentException("Invalid token");
        }
        String userName = tokenStorage.get(token);
        Customer customer = customerRepository.findByUsername(userName);
        if (customer == null) {
            throw new IllegalArgumentException("Invalid token");
        }
        onlineCustomers.add(customer);

        return customer;
    }

    @Override
    public AccountOverview getAccountOverview(String token) {
        String userName = tokenStorage.get(token);
        Customer customer = customerRepository.findByUsername(userName);
        if (!onlineCustomers.contains(customer)) {
            throw new IllegalArgumentException("Invalid token or customer didn't logon");
        }
        AccountOverview accountOverview = new AccountOverview();
        accountOverview.setAccountNumber(customer.getIban());

        return accountOverview;
    }

    private String generateIBAN() {
        StringBuilder iban = new StringBuilder("NL");
        for (int i = 0; i < 14; i++) {
            iban.append((int) (Math.random() * 10));
        }
        return iban.toString();
    }
}