package ru.abtank.repo;

import ru.abtank.model.Customer;
import ru.abtank.model.Pet;

import java.util.List;
import java.util.Objects;

public class CustomerRepr {

    private Long id;

    private String login;

    private List<Pet> pets;

    public CustomerRepr() {
    }

    public CustomerRepr(Customer customer) {
        this.id = customer.getId();
        this.login = customer.getLogin();
        this.pets = customer.getPets();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerRepr that = (CustomerRepr) o;
        return id.equals(that.id) &&
                login.equals(that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login);
    }
}
