package com.skipthedishes.api.repositories;

import com.skipthedishes.api.entities.Customer;
import com.skipthedishes.api.repositories.customer.CustomerRepositoryQuery;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String>, CustomerRepositoryQuery {

    Customer findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);


}