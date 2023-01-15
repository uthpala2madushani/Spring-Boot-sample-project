package com.sprinbotacademy.batch6POS.service.impl;

import com.sprinbotacademy.batch6POS.dto.CustomerDTO;
import com.sprinbotacademy.batch6POS.dto.request.RequestUpdateCustomerDTO;
import com.sprinbotacademy.batch6POS.entity.Customer;
import com.sprinbotacademy.batch6POS.repo.CustomerRepo;
import com.sprinbotacademy.batch6POS.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerSalary(),
                customerDTO.getNic(),
                customerDTO.getContactNumbers(),
                customerDTO.isActiveStatus()
        );
        if (!customerRepo.existsById(customer.getCustomerId())) {
            customerRepo.save(customer);
            return customer.getCustomerName() + " saved";
        } else {
            throw new DuplicateKeyException("Customer already exists");
        }
    }

    @Override
    public String updateCustomer(RequestUpdateCustomerDTO customerDTO) {
        if (customerRepo.existsById(customerDTO.getCustomerId())) {
            Customer customer = customerRepo.getById(customerDTO.getCustomerId());
            customer.setCustomerName(customerDTO.getCustomerName());
            customer.setCustomerAddress(customerDTO.getCustomerAddress());
            customer.setCustomerSalary(customerDTO.getCustomerSalary());
            customerRepo.save(customer);
            return "Saved " + customerDTO.getCustomerId() + " " + customerDTO.getCustomerName();
        } else {
            throw new RuntimeException("Customer not in database");
        }
    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {
//        Customer customer = customerRepo.getReferenceById(customerId);
////        if(customer != null){
////            CustomerDTO customerDTO = new CustomerDTO(
////                    customer.getCustomerId(),
////                    customer.getCustomerName(),
////                    customer.getCustomerAddress(),
////                    customer.getCustomerSalary(),
////                    customer.getNic(),
////                    customer.getContactNumbers(),
////                    customer.isActiveState()
////            );
////            return customerDTO;
////        }else {
////          throw new RuntimeException("Not found a customer for this Id" + customerId);
////        }

        Optional<Customer> customer = customerRepo.findById(customerId);
        if (customer.isPresent()) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.get().getCustomerId(),
                    customer.get().getCustomerName(),
                    customer.get().getCustomerAddress(),
                    customer.get().getCustomerSalary(),
                    customer.get().getNic(),
                    customer.get().getContactNumbers(),
                    customer.get().isActiveStatus()
            );
            return customerDTO;
        } else {
            throw new RuntimeException("Not found");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> getCustomers = customerRepo.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : getCustomers) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getNic(),
                    customer.getContactNumbers(),
                    customer.isActiveStatus()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }

    @Override
    public String deleteCustomer(int customerId) {
        if (customerRepo.existsById(customerId)){
            customerRepo.deleteById(customerId);
            return "Delete success";
        }else {
            return "Not found a custmer for this custmer Id";
        }
    }

    @Override
    public CustomerDTO getCustomerByNic(String nic) {
        Optional<Customer> customer = customerRepo.findByNicEquals(nic);

        if (customer.isPresent()) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.get().getCustomerId(),
                    customer.get().getCustomerName(),
                    customer.get().getCustomerAddress(),
                    customer.get().getCustomerSalary(),
                    customer.get().getNic(),
                    customer.get().getContactNumbers(),
                    customer.get().isActiveStatus()
            );
            return customerDTO;
        } else {
            throw new RuntimeException("Not found");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomersByState(boolean status) {
        List<Customer> getCustomers = customerRepo.findAllCustmersByActiveStatusEquals(status);
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : getCustomers) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getNic(),
                    customer.getContactNumbers(),
                    customer.isActiveStatus()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }

    @Override
    public List<CustomerDTO> getAllCustomersByNameAndState(String name) {
        List<Customer> getCustomers = customerRepo.findAllCustmersByCustomerNameIsAndActiveStatusIs(name,true);
       // List<Customer> getCustomers = customerRepo.findAllCustmersByCustomerNameIsAndActiveStatusIsTrue(name);
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : getCustomers) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomerSalary(),
                    customer.getNic(),
                    customer.getContactNumbers(),
                    customer.isActiveStatus()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }


}
