/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parteli.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Murilo
 */
@Entity
@Table(name = "sales")
public class Sale {
    
    private int id;
    private int pet;
    private String client;
    private String employee;

    
    public Sale(){}
    public Sale(int id, int pet, String client, String employee) {
        this.id = id;
        this.pet = pet;
        this.client = client;
        this.employee = employee;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "pet", nullable = false)
    public int getPet() {
        return pet;
    }

    public void setPet(int pet) {
        this.pet = pet;
    }

    @Column(name = "client", nullable = false)
    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @Column(name = "employee", nullable = false)
    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Sale{" + "id=" + id + ", pet=" + pet + ", client=" + client + ", employee=" + employee + '}';
    }
    
    
}
