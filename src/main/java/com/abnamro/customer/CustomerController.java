package com.abnamro.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerRequest request) {
        try {
            Customer customer = customerService.registerCustomer(request);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to register customer: " + e.getMessage());
        }
    }

    @GetMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody TokenRequest request) {
        String token = customerService.generateToken(request.getUserName());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/logon")
    public ResponseEntity<?> logonCustomer(@RequestHeader("Authorization") String token) {
        try {

            Customer customer = customerService.logonCustomer(token);

            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

    @GetMapping("/overview")
    public ResponseEntity<?> getAccountOverview(@RequestHeader("Authorization") String token) {
        try {

            AccountOverview accountOverview = customerService.getAccountOverview(token);
            return ResponseEntity.ok(accountOverview);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve account overview: " + e.getMessage());
        }
    }
}
