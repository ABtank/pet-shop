package ru.abtank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.abtank.services.CustomerService;
import ru.abtank.services.PetService;

@Controller
@RequestMapping("/")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final CustomerService customerService;
    private final PetService petService;

    @Autowired
    public MainController(CustomerService customerService, PetService petService) {
        this.customerService = customerService;
        this.petService = petService;
    }

    @GetMapping
    public String welcomePage(Model model) {
        model.addAttribute("activePage", "Main");
        model.addAttribute( "customers", customerService.findAll());
        return "index";
    }


}
