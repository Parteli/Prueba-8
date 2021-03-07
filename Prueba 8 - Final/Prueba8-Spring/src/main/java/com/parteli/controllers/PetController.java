/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parteli.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parteli.repositories.PetRepository;
import com.parteli.models.Pet;
import com.parteli.exceptions.ResourceNotFoundException;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PetController {
    @Autowired
    private PetRepository petRepository;

    @GetMapping("/pets")
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable(value = "id") int petId)
        throws ResourceNotFoundException
    {
        Pet pet = petRepository.findById(petId )
          .orElseThrow(
            () -> new ResourceNotFoundException("Pet not found for this id :: " + petId)
          );
        return ResponseEntity.ok().body(pet);
    }
    
    @PostMapping("/pets")
    public Pet createPet(@Valid @RequestBody Pet pet) {
        return petRepository.save(pet);
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable(value = "id") int petId,
         @Valid @RequestBody Pet petDetails) throws ResourceNotFoundException
    {
        Pet pet = petRepository.findById(petId)
        .orElseThrow(
            () -> new ResourceNotFoundException("pet not found for this id :: " + petId)
        );

        pet.setId(petDetails.getId());
        pet.setName(petDetails.getName());
        pet.setType(petDetails.getType());
        pet.setPrice(petDetails.getPrice());
        pet.setSold(petDetails.isSold());
        
        final Pet updatedEmployee = petRepository.save(pet);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/pets/{id}")
    public Map<String, Boolean> deletePet(@PathVariable(value = "id") int petId)
         throws ResourceNotFoundException {
        Pet pet = petRepository.findById(petId)
       .orElseThrow(
            () -> new ResourceNotFoundException("Pet not found for this id :: " + petId)
       );

        petRepository.delete(pet);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
