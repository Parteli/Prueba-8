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

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.parteli.exceptions.ResourceNotFoundException;
import com.parteli.models.Client;
import com.parteli.repositories.ClientRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id") String clientDni)
        throws ResourceNotFoundException
    {
        Client client = clientRepository.findById(clientDni )
          .orElseThrow(
            () -> new ResourceNotFoundException("Client not found for this id :: " + clientDni)
          );
        return ResponseEntity.ok().body(client);
    }
    
    @PostMapping("/clients")
    public Client createClient(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable(value = "id") String clientDni,
         @Valid @RequestBody Client clientDetails) throws ResourceNotFoundException
    {
        Client client = clientRepository.findById(clientDni)
        .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientDni));

        client.setDni(clientDetails.getDni());
        client.setName(clientDetails.getName());
        client.setSurname(clientDetails.getSurname());
        client.setAddress(clientDetails.getAddress());
        client.setRegion(clientDetails.getRegion());
        client.setPhone(clientDetails.getPhone());
        client.setEmail(clientDetails.getEmail());
        
        final Client updatedEmployee = clientRepository.save(client);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/clients/{id}")
    public Map<String, Boolean> deleteClient(@PathVariable(value = "id") String clientDni)
         throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientDni)
       .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientDni));

        clientRepository.delete(client);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
