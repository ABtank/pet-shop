package ru.abtank.services;

import ru.abtank.repo.CustomerRepr;

import java.util.List;
import java.util.Optional;


public interface CustomerService {

    List<CustomerRepr> findAll();

    Optional<CustomerRepr> findById(Long id);

    Optional<CustomerRepr> findByLogin(String login);

    void delete(Long id);

    void save(CustomerRepr customerRepr);
}
