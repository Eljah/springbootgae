package ru.kpfu.itis;

import java.util.List;

/**
 * Created by ilya on 31.05.16.
 */
public interface BicycleRepository  {
    List<Bicycle> findBySerialNumber(String name);
    List<Bicycle> findAll();
    void save(Bicycle bicycle);
    Bicycle findOne(long id);

}
