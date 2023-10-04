package com.brunoalbino.car_pool_sharing.repository;
import com.brunoalbino.car_pool_sharing.model.Car;
import com.brunoalbino.car_pool_sharing.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
   /*
        SELECT reservation.reservation_id, reservation.drop_off_date, reservation.pickup_date, reservation.car_id, car.model,car.license_plate, reservation.driver_id, driver.name FROM reservation
        LEFT JOIN car ON reservation.car_id = car.car_id
        LEFT JOIN driver ON reservation.driver_id = driver.driver_id
        WHERE
        CAST(reservation.pickup_date AS DATE) <= CURRENT_DATE
        AND CAST(reservation.drop_off_date AS DATE) >= CURRENT_DATE
        */

    @Query(
            "SELECT r FROM Reservation r LEFT JOIN Car c ON r.car.car_Id = c.car_Id LEFT JOIN Driver d ON r.driver.driver_Id = d.driver_Id WHERE CAST(r.pickupDate AS DATE) >= :startDate AND CAST(r.dropOffDate AS DATE) <= :endDate AND r.car.car_Id = :carID"
    )
    List<Reservation> findReservationHistory(@Param("startDate") java.sql.Timestamp startDate, @Param("endDate") java.sql.Timestamp endDate, @Param("carID") Integer carId);

}
