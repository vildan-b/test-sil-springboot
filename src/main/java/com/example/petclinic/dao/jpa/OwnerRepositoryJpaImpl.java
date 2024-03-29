package com.example.petclinic.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.petclinic.dao.OwnerRepository;
import com.example.petclinic.model.Owner;

import org.springframework.stereotype.Repository;

@Repository("ownerRepository")
public class OwnerRepositoryJpaImpl implements OwnerRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Owner> findAll() {
        return entityManager.createQuery("from Owner", Owner.class).getResultList();
    }

    @Override
    public Owner findById(Long id) {
        return entityManager.find(Owner.class, id);
    }

    @Override
    public List<Owner> findByLastName(String lastName) {
        return entityManager.createQuery("from Owner where lastname = :lastName", Owner.class)
        .setParameter("lastName", lastName).getResultList();
        
    }

    @Override
    public void create(Owner owner) {
entityManager.persist(owner);        
    }

    @Override
    public Owner update(Owner owner) {
return entityManager.merge(owner);        
    }

    @Override
    public void delete(Long id) {
entityManager.remove(entityManager.getReference(Owner.class, id));       
    }
    
}
