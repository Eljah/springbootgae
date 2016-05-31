package ru.kpfu.itis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by ilya on 31.05.16.
 */
@Entity
public class Bicycle  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    long id;
    @Column(name="serial")
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
