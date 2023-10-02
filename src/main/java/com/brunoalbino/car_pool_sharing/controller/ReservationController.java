package com.brunoalbino.car_pool_sharing.controller;

import com.brunoalbino.car_pool_sharing.model.Driver;
import com.brunoalbino.car_pool_sharing.model.Car;
import com.brunoalbino.car_pool_sharing.model.Reservation;
import com.brunoalbino.car_pool_sharing.model.ReservationDTO;
import com.brunoalbino.car_pool_sharing.repository.CarRepository;
import com.brunoalbino.car_pool_sharing.repository.DriverRepository;
import com.brunoalbino.car_pool_sharing.repository.ReservationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final DriverRepository driverRepository;

    public ReservationController(ReservationRepository reservationRepository, CarRepository carRepository, DriverRepository driverRepository) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
    }

    @GetMapping("/reservation")
    public ResponseEntity getAllReservations(){
        return ResponseEntity.ok(this.reservationRepository.findAll());
    }

    @PostMapping("/reservation")
    public ResponseEntity makeReservation(@RequestBody ReservationDTO reservation){
        //return ResponseEntity.ok(reservationRepository.save(reservation));
        Car car = carRepository.findById(reservation.getCar_id()).get();
        Driver driver = driverRepository.findById(reservation.getDriver_id()).get();

        Reservation reservation_to_save = new Reservation(reservation.getPickupDate(),reservation.getDropOffDate(), car,driver);
        return ResponseEntity.ok(reservationRepository.save(reservation_to_save));
    }

    @GetMapping("/reservationHistory")
    public ResponseEntity reservationHistory(@RequestBody Reservation_History_Helper input_data){

        /*
        SELECT reservation.reservation_id, reservation.drop_off_date, reservation.pickup_date, reservation.car_id, car.model,car.license_plate, reservation.driver_id, driver.name FROM reservation
        LEFT JOIN car ON reservation.car_id = car.car_id
        LEFT JOIN driver ON reservation.driver_id = driver.driver_id
        WHERE
        CAST(reservation.pickup_date AS DATE) <= CURRENT_DATE
        AND CAST(reservation.drop_off_date AS DATE) >= CURRENT_DATE
        */

       return ResponseEntity.ok("Recebido como input pickup_date: "+input_data.getPickupDate() + " drop_off_data"+ input_data.getDropOffDate()+ " car id: " + input_data.getCar_Id());
    }
}
