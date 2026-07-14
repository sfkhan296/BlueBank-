package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;

@Controller
public class CustomerController {

private final CustomerService customerService;

public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
}

    // Home Page
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("totalCustomers",
                customerService.totalCustomers());

        model.addAttribute("totalBalance",
                customerService.totalBalance());

        model.addAttribute("savings",
                customerService.savingsCount());

        model.addAttribute("current",
                customerService.currentCount());

        model.addAttribute("fixedDeposit",
                customerService.fixedDepositCount());

        model.addAttribute("customers",
                customerService.getAllCustomers());

        return "dashboard";
    }

    // Show Add Customer Form
    @GetMapping("/addCustomer")
    public String addCustomerForm(Model model) {

        model.addAttribute("customer", new Customer());

        return "addCustomer";
    }

    // Save Customer
    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute Customer customer) {

        customerService.saveCustomer(customer);

        return "redirect:/customers";
    }

    // Display All Customers
    @GetMapping("/customers")
    public String viewCustomers(Model model) {

        List<Customer> customerList = customerService.getAllCustomers();

        model.addAttribute("customers", customerList);

        return "customers";
    }

    // Edit Customer
    @GetMapping("/editCustomer/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {

        Optional<Customer> customer = customerService.getCustomerById(id);

        if (customer.isPresent()) {

            model.addAttribute("customer", customer.get());

            return "editCustomer";
        }

        return "redirect:/customers";
    }

    // Update Customer
    @PostMapping("/updateCustomer")
    public String updateCustomer(@ModelAttribute Customer customer) {

        customerService.updateCustomer(customer);

        return "redirect:/customers";
    }

    // Delete Customer
    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable Long id) {

        customerService.deleteCustomer(id);

        return "redirect:/customers";
    }

}