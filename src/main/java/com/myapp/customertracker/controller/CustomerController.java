package com.myapp.customertracker.controller;

import com.myapp.customertracker.entity.Customer;
import com.myapp.customertracker.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "customer-form";
        }
        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate/{customerId}")
    public String showFormForUpdate(@PathVariable("customerId") long id, Model model) {
        if(customerService.getCustomer(id).isPresent()) {
            Customer customer = customerService.getCustomer(id).get();
            model.addAttribute("customer", customer);
            return "customer-form";
        }
        return "redirect:/customer/list";
    }

    @GetMapping("/delete/{customerId}")
    public String deleteCustomer(@PathVariable("customerId") long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customer/list";
    }
}
