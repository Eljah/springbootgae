package ru.kpfu.itis;

import org.springframework.stereotype.Repository;

import javax.jdo.FetchGroup;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.List;

/**
 * Created by eljah32 on 6/6/2016.
 */
@Repository
public class BicycleRepositoryImpl implements BicycleRepository {
    @Override
    public List<Bicycle> findBySerialNumber(String name) {
        return null;
    }

    @Override
    public List<Bicycle> findAll() {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query q = pm.newQuery(Bicycle.class);
        //q.setFilter("lastName == lastNameParam");
        q.setOrdering("serialNumber desc");
        //q.declareParameters("String lastNameParam");
        List<Bicycle> results = null;
        try {
            results = (List<Bicycle>) q.execute();
            if (!results.isEmpty()) {
            //    for (Person p : results) {
            //        // Process result p
            //    }
            } else {
                // Handle "no results" case
            }
        } finally {
            q.closeAll();
            return results;
        }

    }

    @Override
    public void save(Bicycle bicycle) {
        PersistenceManager pm = PMF.get().getPersistenceManager();

        try {
            pm.makePersistent(bicycle);
        } finally {
            pm.close();
        }
    }

    @Override
    public Bicycle findOne(long id) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Bicycle returnableBicycle=null;
        try {
            returnableBicycle=pm.getObjectById(Bicycle.class, id);
        } finally {
            pm.close();
        }
        return returnableBicycle;
    }
}
