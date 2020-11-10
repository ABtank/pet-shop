package ru.abtank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.abtank.exceptions.NotFoundException;
import ru.abtank.model.Customer;
import ru.abtank.repo.CustomerRepository;
import ru.abtank.repo.CustomerRepr;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public List<CustomerRepr> findAll() {
        return customerRepository.findAll().stream().map(CustomerRepr::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<CustomerRepr> findById(Long id) {
        return customerRepository.findById(id).map(CustomerRepr::new);
    }

    @Override
    @Transactional
    public Optional<CustomerRepr> findByLogin(String login) {
        return customerRepository.findByLogin(login).map(CustomerRepr::new);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(CustomerRepr customerRepr) {
        Customer customer = (customerRepr.getId() != null) ? customerRepository.findById(customerRepr.getId())
                .orElseThrow(NotFoundException::new) : new Customer();
        customer.setId(customerRepr.getId());
        customer.setLogin(customerRepr.getLogin());
        customerRepository.save(customer);
    }
}
