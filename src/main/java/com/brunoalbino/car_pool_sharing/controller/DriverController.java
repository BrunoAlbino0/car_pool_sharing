package com.brunoalbino.car_pool_sharing.controller;

import com.brunoalbino.car_pool_sharing.model.Driver;
import com.brunoalbino.car_pool_sharing.repository.DriverRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {

    private final DriverRepository driverRepository;

    public DriverController(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @GetMapping("/driver")
    public ResponseEntity getAllCars(){
        return ResponseEntity.ok(this.driverRepository.findAll());
    }

    @PostMapping("/driver")
    public ResponseEntity registCar(@RequestBody Driver driver){
        return ResponseEntity.ok(driverRepository.save(driver));
    }
}
