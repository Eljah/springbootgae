package ru.kpfu.itis;

import javax.jdo.annotations.*;
import java.io.Serializable;

/**
 * Created by ilya on 31.05.16.
 */

@PersistenceCapable
public class Bicycle  implements Serializable {
    private static final long serialVersionUID = 1L;

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    long id;

    @Persistent
    String serialNumber;
    //Getters and Setters Here


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
