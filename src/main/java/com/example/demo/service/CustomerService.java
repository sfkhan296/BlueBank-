package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    // Constructor Injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Save Customer
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Get All Customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get Customer By ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Update Customer
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Delete Customer
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    // ===========================
    // Dashboard Statistics
    // ===========================

    public long totalCustomers() {
        return customerRepository.count();
    }

    public long savingsCount() {
        return customerRepository.countByAccountType("Savings");
    }

    public long currentCount() {
        return customerRepository.countByAccountType("Current");
    }

    public long fixedDepositCount() {
        return customerRepository.countByAccountType("Fixed Deposit");
    }

    public double totalBalance() {
        return customerRepository.findAll()
                .stream()
                .mapToDouble(Customer::getBalance)
                .sum();
    }
}