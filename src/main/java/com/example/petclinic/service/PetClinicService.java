package com.example.petclinic.service;

import java.util.List;

import com.example.petclinic.exceptions.OwnerNotFoundException;
import com.example.petclinic.model.Owner;

public interface PetClinicService {
    List<Owner> findOwners();
    List<Owner> findOwners(String lastName);
    Owner findOwner(Long id) throws OwnerNotFoundException;
    void createOwner(Owner owner);
    Owner updateOwner(Owner owner);
    void deleteOwner(Long id);
}
