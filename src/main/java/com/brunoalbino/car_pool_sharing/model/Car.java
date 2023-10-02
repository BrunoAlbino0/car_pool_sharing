package com.brunoalbino.car_pool_sharing.model;

import jakarta.persistence.*;
import lombok.Data;

enum EngineType {
    COMBUSTION,
    ELECTRIC,
    HYBRID
}

@Data
@Entity
@Table(name ="car")
public class Car {
    @Id
    @SequenceGenerator(
            name = "car_id_sequence",
            sequenceName = "car_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "car_id_sequence"
    )
    private Integer car_Id;
    private String brand;
    private String model;
    private Integer seats;
    private String licensePlate;
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    private Integer currentAutonomy;

    //@Lob
   // @Basic(fetch = FetchType.LAZY)
    //private byte[] image;

    private Boolean inactive;

    public Car(String brand, String model, Integer seats, String licensePlate, EngineType engineType, Integer currentAutonomy) {
        this.brand = brand;
        this.model = model;
        this.seats = seats;
        this.licensePlate = licensePlate;
        this.engineType = engineType;
        this.currentAutonomy = currentAutonomy;
        this.inactive = false;
    }

    public Integer getCar_Id() {
        return car_Id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getSeats() {
        return seats;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public Integer getCurrentAutonomy() {
        return currentAutonomy;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setCar_Id(Integer car_Id) {
        this.car_Id = car_Id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public void setCurrentAutonomy(Integer currentAutonomy) {
        this.currentAutonomy = currentAutonomy;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public Car(){

    }
}
