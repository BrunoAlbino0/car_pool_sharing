package car_pool_sharing_client.Models;

import lombok.ToString;

public class Car {
    private Integer car_Id;
    private String brand;
    private String model;
    private Integer seats;
    private String licensePlate;
    private EngineType engineType;
    private Integer currentAutonomy;
    private Boolean inactive;

    public Car(Integer car_Id, String brand, String model, Integer seats, String licensePlate, EngineType engineType, Integer currentAutonomy, Boolean inactive) {
        this.car_Id = car_Id;
        this.brand = brand;
        this.model = model;
        this.seats = seats;
        this.licensePlate = licensePlate;
        this.engineType = engineType;
        this.currentAutonomy = currentAutonomy;
        this.inactive = inactive;
    }

    public Car(){

    }

    public Integer getCar_Id() {
        return car_Id;
    }

    public void setCar_Id(Integer car_Id) {
        this.car_Id = car_Id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public Integer getCurrentAutonomy() {
        return currentAutonomy;
    }

    public void setCurrentAutonomy(Integer currentAutonomy) {
        this.currentAutonomy = currentAutonomy;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public String getDetails(){
        StringBuilder info = new StringBuilder();
        info.append("Car: ");
        info.append("\tID: " + getCar_Id());
        info.append("\tBrand: " + getBrand());
        info.append("\tModel: " + getModel());
        info.append("\tEngine type: " + getEngineType());
        info.append("\tSeats: " + getSeats());
        info.append("\tCurrent Autonomy: " + getCurrentAutonomy());
        info.append("\tLicense Plate: " + getLicensePlate());
        info.append("\tInactive: " + getInactive());
        return info.toString();
    }
    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("Car: ");
        info.append("\t\tID: " + getCar_Id());
        info.append("\t\tLicense Plate: " + getLicensePlate());
        info.append("\t\tInactive: " + getInactive());
        return info.toString();

    }
}
