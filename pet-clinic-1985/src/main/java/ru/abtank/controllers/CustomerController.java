package ru.abtank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.model.Customer;
import ru.abtank.repo.CustomerRepr;
import ru.abtank.repo.PetRepr;
import ru.abtank.services.CustomerService;
import ru.abtank.services.PetService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;
    private final PetService petService;

    @Autowired
    public CustomerController(CustomerService customerService, PetService petService) {
        this.customerService = customerService;
        this.petService = petService;
    }

    @GetMapping
    public String customersPage(Model model){
        model.addAttribute("activePage", "Customers");
        model.addAttribute( "customers", customerService.findAll());
        return "customers";
    }

    @GetMapping("create")
    public String createCustomer(Model model){
        model.addAttribute("activePage", "Customers");
        model.addAttribute("Create", true);
        model.addAttribute("customer", new CustomerRepr());
        return "customer_form";
    }

    @GetMapping("/{id}/update")
    public String updateCustomer(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Customers");
        model.addAttribute("Update", true);
        model.addAttribute("customer", customerService.findById(id)
                .orElseThrow(() -> new NotFoundException(id.toString(), Customer.class.getSimpleName())));
        model.addAttribute("pets", petService.findAllByCustomerId(id));
        return ("customer_form");
    }

    @DeleteMapping("/{id}/delete")
    public String deleteCustomer(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Customers");
        customerService.delete(id);
        return "redirect:/customers";
    }

    @PostMapping("/save")
    public String saveCustomer(Model model, @Valid CustomerRepr customer, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        model.addAttribute("activePage", "Сustomers");
        if (bindingResult.hasErrors()) {
            return "customer_form";
        }
        logger.info("come to try");
        try {
            logger.info("try save");
            customerService.save(customer);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating customer", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (customer.getId() == null) {
                return "redirect:/customers/create";
            }
            return "redirect:/customers/" + customer.getId() + "/update";
        }
        return "redirect:/customers";

    }
}
