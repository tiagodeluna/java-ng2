package com.skipthedishes.api.resources;


import com.skipthedishes.api.entities.Customer;
import com.skipthedishes.api.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerResource {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer) {
        Customer customerSaved = customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerSaved);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(customerList);

    }

    @GetMapping(params = "name")
    public ResponseEntity<List<Customer>> findByText(@RequestParam(name = "name") String name) {
        List<Customer> customerList = customerRepository.find(name);
        return customerList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(customerList);

    }


}
