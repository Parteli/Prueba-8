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

import com.parteli.models.Sale;
import com.parteli.repositories.SaleRepository;
import com.parteli.exceptions.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class SaleController {
    @Autowired
    private SaleRepository saleRepository;
    
    @GetMapping("/sales")
    public List<Sale> getAllSales(){
        return saleRepository.findAll();
    }
    
    @GetMapping("/sales/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable(value="id") int saleID)
    throws ResourceNotFoundException
    {
        Sale sale = saleRepository.findById(saleID)
        .orElseThrow(
            ()-> new ResourceNotFoundException("Sale not found for this id :: " + saleID)
        );
        return ResponseEntity.ok().body(sale);
    }
    
    @PostMapping("/sales")
    public Sale createSale(@Valid @RequestBody Sale sale)
    {
        return saleRepository.save(sale);
    }
    
    @PutMapping("/sales/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable(value="id") int saleId,
                                        @Valid @RequestBody Sale saleDetails)
            throws ResourceNotFoundException
    {
        Sale sale = saleRepository.findById(saleId)
            .orElseThrow( 
                ()-> new ResourceNotFoundException("Sale not found for this id :: " + saleId)
            );
        
        sale.setId(saleDetails.getId());
        sale.setClient(saleDetails.getClient());
        sale.setEmployee(saleDetails.getEmployee());
        sale.setPet(saleDetails.getPet());
        
        final Sale updated = saleRepository.save(sale);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/sales/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") int saleId)
        throws ResourceNotFoundException
    {
        Sale sale = saleRepository.findById(saleId)
            .orElseThrow(
                () -> new ResourceNotFoundException("Sale not found for this id :: " + saleId)
            );

        saleRepository.delete(sale);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    
}
