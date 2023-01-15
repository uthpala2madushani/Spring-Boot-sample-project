package com.sprinbotacademy.batch6POS.service;

import com.sprinbotacademy.batch6POS.dto.CustomerDTO;
import com.sprinbotacademy.batch6POS.dto.request.RequestUpdateCustomerDTO;

import java.util.List;

public interface CustomerService {
    public String saveCustomer(CustomerDTO customerDTO);

    public String updateCustomer(RequestUpdateCustomerDTO customerDTO);

    public CustomerDTO getCustomerById(int customerId);

    public List<CustomerDTO> getAllCustomers();

    String deleteCustomer(int customerId);

    CustomerDTO getCustomerByNic(String nic);

    List<CustomerDTO> getAllCustomersByState(boolean status);

    List<CustomerDTO> getAllCustomersByNameAndState(String name);
}
