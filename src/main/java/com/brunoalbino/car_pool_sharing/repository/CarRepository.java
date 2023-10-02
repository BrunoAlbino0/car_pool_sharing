package com.brunoalbino.car_pool_sharing.repository;
import com.brunoalbino.car_pool_sharing.model.Car;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query(value = "SELECT car.car_id, car.brand, car.model, car.current_autonomy, car.engine_type, car.inactive, car.license_plate, car.seats FROM car LEFT JOIN reservation ON car.car_id = reservation.car_id WHERE reservation.reservation_id IS NULL OR (reservation.pickup_date < CURRENT_TIMESTAMP AND reservation.drop_off_date < CURRENT_TIMESTAMP)",
    nativeQuery = true)
    List<Car> findAvailableCarsNative();
}
