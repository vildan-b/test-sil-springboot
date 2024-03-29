package com.example.petclinic.service;

import java.util.List;

import com.example.petclinic.dao.OwnerRepository;
import com.example.petclinic.dao.PetRepository;
import com.example.petclinic.exceptions.OwnerNotFoundException;
import com.example.petclinic.model.Owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PetClinicServiceImpl implements PetClinicService {

    private OwnerRepository ownerRepository;
    private PetRepository petRepository;

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Autowired
    public void setPetRepository(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Owner> findOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public List<Owner> findOwners(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public Owner findOwner(Long id) throws OwnerNotFoundException {
        Owner owner = ownerRepository.findById(id);
        if (owner == null)
            throw new OwnerNotFoundException("Owner not found with id: " + id);
        return owner;
    }

    @Override
    public void createOwner(Owner owner) {
        ownerRepository.create(owner);
    }

    @Override
    public void updateOwner(Owner owner) {
        ownerRepository.update(owner);
    }

    @Override
    public void deleteOwner(Long id) {
        petRepository.deleteByOwnerId(id);
        ownerRepository.delete(id);
        // if(true) throw new RuntimeException("testing rollback");
    }

}
