package ru.abtank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.abtank.services.CustomerService;
import ru.abtank.services.PetService;

@ControllerAdvice
public class SidebarController {

    private final CustomerService customerService;
    private final PetService petService;

    @Autowired
    public SidebarController(CustomerService customerService, PetService petService) {
        this.customerService = customerService;
        this.petService = petService;
    }

    @ModelAttribute
    public void sidebarItem(Model model){
        model.addAttribute("count_customers" , customerService.findAll().size());
        model.addAttribute("count_pets" , petService.findAll().size());
    }
}
