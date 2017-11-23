package com.skipthedishes.api.repositories.customer;

import com.skipthedishes.api.entities.Customer;

import java.util.List;

public interface CustomerRepositoryQuery {

    List<Customer> find(String text);

}
