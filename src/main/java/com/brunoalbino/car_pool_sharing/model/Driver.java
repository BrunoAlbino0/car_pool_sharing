package com.brunoalbino.car_pool_sharing.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="driver")
public class Driver {
    @Id
    @SequenceGenerator(
            name = "driver_id_sequence",
            sequenceName = "driver_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "driver_id_sequence"
    )
    private Integer driver_Id;
    private String name;
    private String contact;
    private String licenseNumber;

    public Driver(String name, String contact, String licenseNumber) {
        this.name = name;
        this.contact = contact;
        this.licenseNumber = licenseNumber;
    }

    public Driver(){

    }

    public Integer getDriver_Id() {
        return driver_Id;
    }

    public void setDriver_Id(Integer driver_Id) {
        this.driver_Id = driver_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
