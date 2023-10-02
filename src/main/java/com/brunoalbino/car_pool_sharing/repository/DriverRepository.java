package com.brunoalbino.car_pool_sharing.repository;

import com.brunoalbino.car_pool_sharing.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

}
