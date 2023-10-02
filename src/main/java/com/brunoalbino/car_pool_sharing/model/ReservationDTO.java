package com.brunoalbino.car_pool_sharing.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

public class ReservationDTO {

    private java.sql.Timestamp pickupDate;
    private java.sql.Timestamp dropOffDate;

    private Integer car_id;
    private Integer driver_id;

    public ReservationDTO(Timestamp pickupDate, Timestamp dropOffDate, Integer car_id, Integer driver_id) {
        this.pickupDate = pickupDate;
        this.dropOffDate = dropOffDate;
        this.car_id = car_id;
        this.driver_id = driver_id;
    }

    public Timestamp getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Timestamp pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Timestamp getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(Timestamp dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public Integer getCar_id() {
        return car_id;
    }

    public void setCar_id(Integer car_id) {
        this.car_id = car_id;
    }

    public Integer getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Integer driver_id) {
        this.driver_id = driver_id;
    }
}
