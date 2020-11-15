package ru.abtank.repo;

import ru.abtank.model.Customer;
import ru.abtank.model.Pet;

public class PetRepr {

    private Integer id;

    private String name;

    private Customer customer;

    public PetRepr() {
    }

    public PetRepr(Pet pet) {
        this.id = pet.getId();
        this.name = pet.getName();
        this.customer = pet.getCustomer();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
