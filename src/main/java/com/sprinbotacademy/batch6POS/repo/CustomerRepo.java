package com.sprinbotacademy.batch6POS.repo;

import com.sprinbotacademy.batch6POS.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByNicEquals(String nic);

    List<Customer> findAllCustmersByActiveStatusEquals(boolean status);

    List<Customer> findAllCustmersByCustomerNameIsAndActiveStatusIs(String name , boolean status);
    //List<Customer> findAllCustmersByCustomerNameIsAndActiveStatusIsTrue(String name);
}
