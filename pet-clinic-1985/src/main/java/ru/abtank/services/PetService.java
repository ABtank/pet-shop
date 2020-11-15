package ru.abtank.services;

import ru.abtank.repo.PetRepr;

import java.util.List;
import java.util.Optional;

public interface PetService {

    List<PetRepr> findAll();

    List<PetRepr> findAllByCustomerId(Long id);

    Optional<PetRepr> findById(Integer id);

    Optional<PetRepr> findByName(String name);

    void delete(Integer id);

    void save(PetRepr petRepr);
}
