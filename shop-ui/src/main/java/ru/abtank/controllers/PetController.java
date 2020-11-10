package ru.abtank.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.model.Pet;
import ru.abtank.repo.PetRepr;
import ru.abtank.services.CustomerService;
import ru.abtank.services.PetService;

@Controller
@RequestMapping("/pets")
public class PetController {

    private static final Logger logger = LoggerFactory.getLogger(PetController.class);
    private final PetService petService;
    private final CustomerService customerService;

    @Autowired
    public PetController(PetService petService, CustomerService customerService) {
        this.petService = petService;
        this.customerService = customerService;
    }

    @GetMapping
    public String petsPage(Model model) {
        model.addAttribute("activePage", "Pets");
        model.addAttribute("pets", petService.findAll());
        return "pets";
    }

    @GetMapping("create")
    public String createpet(Model model) {
        model.addAttribute("activePage", "Pets");
        model.addAttribute("Create", true);
        model.addAttribute("pet", new PetRepr());
        model.addAttribute("customers", customerService.findAll());
        return "pet_form";
    }

    @GetMapping("/{id}/update")
    public String updatePet(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("activePage", "Pets");
        model.addAttribute("Update", true);
        model.addAttribute("pet", petService.findById(id)
                .orElseThrow(() -> new NotFoundException(id.toString(), Pet.class.getSimpleName())));
        model.addAttribute("customers", customerService.findAll());
        return ("pet_form");
    }

    @DeleteMapping("/{id}/delete")
    public String deletePet(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("activePage", "Pets");
        petService.delete(id);
        return "redirect:/pets";
    }

    @PostMapping("/save")
    public String savePet(Model model, PetRepr pet, RedirectAttributes redirectAttributes) {
        model.addAttribute("activePage", "Pets");
        logger.info("come to try");
        try {
            logger.info("try save");
            petService.save(pet);
        } catch (Exception ex) {
            logger.error("Problem with creating or updating pet", ex);
            redirectAttributes.addFlashAttribute("error", true);
            if (pet.getId() == null) {
                return "redirect:/pets/create";
            }
            return "redirect:/pets/" + pet.getId() + "/update";
        }
        return "redirect:/pets";

    }
}
