package car_pool_sharing_client.Models;

import jakarta.persistence.*;

import java.sql.Timestamp;

public class Reservation {
    private Integer reservation_Id;
    private java.sql.Timestamp pickupDate;
    private java.sql.Timestamp dropOffDate;
    private Car car;
    private Driver driver;

    public Reservation(Integer reservation_Id, Timestamp pickupDate, Timestamp dropOffDate, Car car, Driver driver) {
        this.reservation_Id = reservation_Id;
        this.pickupDate = pickupDate;
        this.dropOffDate = dropOffDate;
        this.car = car;
        this.driver = driver;
    }

    public Reservation(){

    }

    public Integer getReservation_Id() {
        return reservation_Id;
    }

    public void setReservation_Id(Integer reservation_Id) {
        this.reservation_Id = reservation_Id;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Reservation:");
        stringBuilder.append("\t Pickup Date: " +getPickupDate() + "\tDropOff Date:" + getDropOffDate());
        stringBuilder.append("\t"+getDriver().toString());
        stringBuilder.append("\t"+getCar().getDetails());
        return stringBuilder.toString();
    }
}
