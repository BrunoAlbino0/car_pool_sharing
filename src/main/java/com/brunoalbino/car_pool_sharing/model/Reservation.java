package com.brunoalbino.car_pool_sharing.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import com.brunoalbino.car_pool_sharing.model.Car;
import com.brunoalbino.car_pool_sharing.model.Driver;
@Data
@Entity
@Table(name ="reservation")
public class Reservation {

    @Id
    @SequenceGenerator(
            name = "reservation_id_sequence",
            sequenceName = "reservation_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reservation_id_sequence"
    )
    private Integer reservation_Id;

    private java.sql.Timestamp pickupDate;
    private java.sql.Timestamp dropOffDate;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    public Reservation(Timestamp pickupDate, Timestamp dropOffDate, Car car, Driver driver) {
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


}
