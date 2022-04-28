package com.example.petclinic.web;

import java.net.URI;
import java.util.List;


import com.example.petclinic.exceptions.InternalServerException;
import com.example.petclinic.exceptions.OwnerNotFoundException;
import com.example.petclinic.model.Owner;
import com.example.petclinic.service.PetClinicService;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/rest")

public class PetClinicRestController {
    @Autowired
    private PetClinicService petclinicService;

    @RequestMapping(method = RequestMethod.DELETE, value = "/owner/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id) {
        try {
            petclinicService.deleteOwner(id);
            return ResponseEntity.ok().build();
        } catch (OwnerNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex);
        }

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/owner/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable("id") Long id, @RequestBody Owner ownerRequest) {
        try {
            Owner owner = petclinicService.findOwner(id);
            owner.setFirstName(ownerRequest.getFirstName());
            owner.setLastName(ownerRequest.getLastName());
            petclinicService.updateOwner(owner);
            return ResponseEntity.ok().build();

        } catch (OwnerNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/owner")
    public ResponseEntity<URI> createOwner(@RequestBody Owner owner) {
        try {
            petclinicService.createOwner(owner);
            Long id = owner.getId();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owners")
    public ResponseEntity<List<Owner>> getOwners() {
        List<Owner> owners = petclinicService.findOwners();
        return ResponseEntity.ok(owners);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owner")
    public ResponseEntity<List<Owner>> getOwners(@RequestParam("ln") String lastName) {
        List<Owner> owners = petclinicService.findOwners(lastName);
        return ResponseEntity.ok(owners);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owner/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable("id") Long id) {
        try {
            Owner owner = petclinicService.findOwner(id);
            return ResponseEntity.ok(owner);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();

        }
    }


    // @RequestMapping(method = RequestMethod.GET, value = "/owner/{id}", produces = "application/json")
    // public ResponseEntity<?> getOwnerAsHateoasResource(@PathVariable("id") Long id) {
    //     try {
    //         Owner owner = petclinicService.findOwner(id);
    //         EntityModel<Owner> resource = new EntityModel<Owner>(user);

    //         org.springframework.hateoas.Link self = ControllerLinkBuilder.linkTo(PetClinicController.class).slash("/owner/"+id).withSelfRel();
    //         org.springframework.hateoas.Link create = ControllerLinkBuilder.linkTo(PetClinicController.class).slash("/owner/"+id).withRel("create");
    //         org.springframework.hateoas.Link update = ControllerLinkBuilder.linkTo(PetClinicController.class).slash("/owner/"+id).withRel("update");
    //         org.springframework.hateoas.Link delete = ControllerLinkBuilder.linkTo(PetClinicController.class).slash("/owner/"+id).withRel("delete");
    //         CollectionModel<Owner> resource = new Resource<Owner>(owner,self,create,update,delete);

    //         return ResponseEntity.ok(owner);
    //     } catch (OwnerNotFoundException e) {
    //         return ResponseEntity.notFound().build();

    //     }
    // }




}
