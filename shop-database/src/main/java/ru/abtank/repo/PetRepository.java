package ru.abtank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.abtank.model.Pet;

import java.util.List;
import java.util.Optional;


@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

    Optional<Pet> findByName(String name);
    List<Pet> findAllByCustomerId(Long id);
}
