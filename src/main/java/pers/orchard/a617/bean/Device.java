package pers.orchard.a617.bean;

import org.springframework.lang.NonNull;

public class Device {
    private Integer ID;
    private String name;

    private String dateRegistered;
    private String dateVisited;

    public Device() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getDateVisited() {
        return dateVisited;
    }

    public void setDateVisited(String dateVisited) {
        this.dateVisited = dateVisited;
    }

    @Override
    @NonNull
    public String toString() {
        return "Device{" + "ID=" + ID + ", name='" + name + '\'' + ", dateRegistered='" + dateRegistered + '\'' + ", dateVisited='" + dateVisited + '\'' + '}';
    }

}
