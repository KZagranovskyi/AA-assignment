package com.abnamro.customer;

public interface CustomerService {
    public Customer registerCustomer(CustomerRequest request);

    public String generateToken(String userName) ;

    public Customer logonCustomer(String token);

    public AccountOverview getAccountOverview(String token);
}
