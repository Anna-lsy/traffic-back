package cn.edu.usst.anna.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ownersban {
    @Id
    private int id;
    private String carID;
    private String time;
    private double fine;
    private String message;
    private String status;
    private String copID;
    private String oid;
    private String city;

    public Ownersban(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopID() {
        return copID;
    }

    public void setCopID(String copID) {
        this.copID = copID;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
