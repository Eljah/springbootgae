package ru.kpfu.itis;

import java.util.List;

/**
 * Created by ilya on 31.05.16.
 */
public interface BicycleService  {
    public List<Bicycle> findAll();
    public void saveBicycle(Bicycle book);
    public Bicycle findOne(long id);
    //public void delete(long id);
    public List<Bicycle> findBySerialNumber(String serialNumber);
}