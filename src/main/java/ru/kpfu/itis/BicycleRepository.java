package ru.kpfu.itis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by ilya on 31.05.16.
 */
public interface BicycleRepository extends JpaRepository<Bicycle,Long> {
    List<Bicycle> findBySerialNumber(String name);
    //List<Bicycle> findAll();
}
