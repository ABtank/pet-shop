package ru.abtank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.model.Pet;
import ru.abtank.repo.CustomerRepository;
import ru.abtank.repo.PetRepository;
import ru.abtank.repo.PetRepr;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public PetServiceImpl(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public List<PetRepr> findAll() {
        return petRepository.findAll().stream().map(PetRepr:: new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<PetRepr> findAllByCustomerId(Long id) {
        return petRepository.findAllByCustomerId(id).stream().map(PetRepr::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<PetRepr> findById(Integer id) {
        return petRepository.findById(id).map(PetRepr::new);
    }

    @Override
    @Transactional
    public Optional<PetRepr> findByName(String name) {
        return petRepository.findByName(name).map(PetRepr::new);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        petRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(PetRepr petRepr) {
        Pet pet = (petRepr.getId() != null) ? petRepository.findById(petRepr.getId())
                .orElseThrow(NotFoundException::new) : new Pet();
        pet.setId(petRepr.getId());
        pet.setName(petRepr.getName());
        pet.setCustomer(petRepr.getCustomer());
        petRepository.save(pet);
    }
}
