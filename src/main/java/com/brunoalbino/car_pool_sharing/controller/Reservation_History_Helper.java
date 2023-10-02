package com.brunoalbino.car_pool_sharing.controller;

import java.sql.Date;

public class Reservation_History_Helper {

    private java.sql.Date pickupDate;
    private java.sql.Date dropOffDate;
    private Integer car_Id;

    public Reservation_History_Helper(Date pickupDate, Date dropOffDate, Integer car_Id) {
        this.pickupDate = pickupDate;
        this.dropOffDate = dropOffDate;
        this.car_Id = car_Id;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Date getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(Date dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public Integer getCar_Id() {
        return car_Id;
    }

    public void setCar_Id(Integer car_Id) {
        this.car_Id = car_Id;
    }
}
