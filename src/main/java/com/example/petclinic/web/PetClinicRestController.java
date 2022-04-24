package com.example.petclinic.web;

import java.util.List;

import com.example.petclinic.exceptions.OwnerNotFoundException;
import com.example.petclinic.model.Owner;
import com.example.petclinic.service.PetClinicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class PetClinicRestController {
    @Autowired
    private PetClinicService petclinicService;
    
    @RequestMapping(method=RequestMethod.GET, value="/owners")
    public ResponseEntity<List<Owner>> getOwners(){
        List<Owner> owners=petclinicService.findOwners();
        return ResponseEntity.ok(owners);
    }

    @RequestMapping(method=RequestMethod.GET, value="/owner")
    public ResponseEntity<List<Owner>> getOwners(@RequestParam("ln") String lastName){
        List<Owner> owners=petclinicService.findOwners(lastName);
        return ResponseEntity.ok(owners);
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/owner/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable("id") Long id){
        try {
        Owner owner=petclinicService.findOwner(id);
        return ResponseEntity.ok(owner);
    } catch(OwnerNotFoundException e){
        return ResponseEntity.notFound().build();

    }
    }
}
