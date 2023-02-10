package com.redis.om.skeleton.models.repositories;

import com.redis.om.skeleton.models.Customer;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface CustomerRepository extends RedisDocumentRepository<Customer, String> {
    Iterable<Customer> findByName(String text);
}
