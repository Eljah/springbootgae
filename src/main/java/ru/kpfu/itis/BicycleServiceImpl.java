package ru.kpfu.itis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ilya on 31.05.16.
 */
@Service
@Transactional
public class BicycleServiceImpl implements BicycleService{
    @Autowired
    private BicycleRepository bikeRepository;
    //@Autowired
    //private BookOwnRepository bookOwnRepository; TODO

    public List<Bicycle> findAll(){
        return bikeRepository.findAll();
    }
    public List<Bicycle> findBySerialNumber(String name) {
        return bikeRepository.findBySerialNumber(name);
    }

    public void saveBicycle(Bicycle bike){
        bikeRepository.save(bike);
    }
    public Bicycle findOne(long id){
        return bikeRepository.findOne(id);
    }
    //public void delete(long id){
    //    bookRepository.delete(id);
    //}
}