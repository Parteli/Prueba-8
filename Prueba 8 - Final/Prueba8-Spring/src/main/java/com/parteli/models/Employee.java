/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Murilo
 */
package com.parteli.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "employees")
public class Employee {

    private String id;
    private String password;
    private String name;
    private String surname;
    private String phone;
    private boolean admin;

    public Employee(String id, String password, String name, String surname, String phone, boolean admin) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.admin = admin;
    }
 
    public Employee() {
  
    }
 
    @Id
    @Column(name = "id", nullable = false)
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
 
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
 
    @Column(name = "surname", nullable = false)
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
 
    @Column(name = "phone", nullable = false)
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
 
    @Column(name = "admin", nullable = false)
    public boolean getAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return (admin?"Admin":"Employee") +"{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", phone=" + phone + '}';
    }

    
 
}