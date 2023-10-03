package com.brunoalbino.car_pool_sharing.controller;

import com.brunoalbino.car_pool_sharing.model.Car;
import com.brunoalbino.car_pool_sharing.repository.CarRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @GetMapping("/car")
    public ResponseEntity getAllCars(){
        return ResponseEntity.ok(this.carRepository.findAll());
    }

    @GetMapping("/car_details")
    public ResponseEntity getCarDetails(@RequestParam Integer id){
        return ResponseEntity.ok(this.carRepository.findById(id));
    }

    @PostMapping("/car")
    public ResponseEntity registCar(@RequestBody Car car){
        return ResponseEntity.ok(carRepository.save(car));
    }

    @PutMapping("/inactivatecar")
    public ResponseEntity inactivateCar(@RequestParam Integer id){
        if (id > 0){
           Car car = carRepository.findById(id).get();
           if (car != null){
               car.setInactive(true);
               carRepository.save(car);
               return ResponseEntity.ok(car);
           }
            return new ResponseEntity<>("Car not found", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>("ID of car can't be less than Zero", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/activatecar")
    public ResponseEntity activateCar(@RequestParam Integer id){
        if (id > 0){
            Car car = carRepository.findById(id).get();
            if (car != null){
                car.setInactive(false);
                carRepository.save(car);
                return ResponseEntity.ok(car);
            }
            return new ResponseEntity<>("Car not found", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>("ID of car can't be less than Zero", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/availablecars")
    public ResponseEntity getAvailableCars(@RequestParam java.sql.Timestamp pickcupDate, @RequestParam java.sql.Timestamp dropOffDate){
   // public ResponseEntity getAvailableCars(@RequestParam java.sql.Timestamp pickcupDate,@RequestParam java.sql.Timestamp drop_offDate){

       // return ResponseEntity.ok(carRepository.findAvailableCars(pickcupDate, dropOffDate));
        return ResponseEntity.ok("Recebido a seguinte data de pickup: " + pickcupDate + " Data de dropOff: "+ dropOffDate);
    }
}
