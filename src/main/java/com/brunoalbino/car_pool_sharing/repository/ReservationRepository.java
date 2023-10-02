package com.brunoalbino.car_pool_sharing.repository;
import com.brunoalbino.car_pool_sharing.model.Car;
import com.brunoalbino.car_pool_sharing.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

}
