package com.example.petclinic.dao.mem;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.petclinic.dao.OwnerRepository;
import com.example.petclinic.model.Owner;

import org.springframework.stereotype.Repository;

@Repository
public class OwnerRepositoryInMemoryImpl implements OwnerRepository {

    private Map<Long, Owner> ownersMap = new HashMap<>();

    public OwnerRepositoryInMemoryImpl() {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Vildan");
        owner1.setLastName("Bay");
        
        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Hilmi");
        owner2.setLastName("Bay");

        Owner owner3 = new Owner();
        owner3.setId(3L);
        owner3.setFirstName("Azra");
        owner3.setLastName("Bay");

        Owner owner4 = new Owner();
        owner4.setId(4L);
        owner4.setFirstName("Gulush");
        owner4.setLastName("Bay");

        ownersMap.put(owner1.getId(), owner1);
        ownersMap.put(owner2.getId(), owner2);
        ownersMap.put(owner3.getId(), owner3);
        ownersMap.put(owner4.getId(), owner4);


    }

    @Override
    public List<Owner> findAll() {
        return new ArrayList<>(ownersMap.values());
    }

    @Override
    public Owner findById(Long id) {
        return ownersMap.get(id);
    }

    @Override
    public List<Owner> findByLastName(String lastName) {
        return ownersMap.values().stream().filter(o->o.getLastName().equals(lastName)).collect(Collectors.toList());
    }

    @Override
    public void create(Owner owner) {
        owner.setId(new Date().getTime());
        ownersMap.put(owner.getId(), owner);
    }

    @Override
    public Owner update(Owner owner) {
        return ownersMap.replace(owner.getId(), owner);
    }

    @Override
    public void delete(Long id) {
        ownersMap.remove(id);
        }

}
