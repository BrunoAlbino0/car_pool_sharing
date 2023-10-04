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
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public ResponseEntity reservationHistory(@RequestParam String StartDate, @RequestParam String EndDate,@RequestParam int car_id){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm");

        java.sql.Timestamp timestamp_startDate = null;
        java.sql.Timestamp timestamp_endDate = null;
        Date date = null;
        try {
            date = dateFormat.parse(StartDate);
            timestamp_startDate = new Timestamp(date.getTime());
            date = dateFormat.parse(EndDate);
            timestamp_endDate = new Timestamp(date.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(reservationRepository.findReservationHistory(timestamp_startDate, timestamp_endDate, car_id));
    }
}
