package cn.edu.usst.anna.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ban {
    @Id
    private int id;
    private String carID;
    private String time;
    private String fine;
    private String message;
    private String status;
    private String copID;
    private String city;

    public Ban(){}
    public Ban(String carID, String time, String fine, String message, String status, String copID, String city){
        this.carID = carID;
        this.time = time;
        this.fine = fine;
        this.message = message;
        this.status = status;
        this.copID = copID;
        this.city = city;
    }

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

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
