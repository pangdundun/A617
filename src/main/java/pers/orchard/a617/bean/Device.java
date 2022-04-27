package pers.orchard.a617.bean;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.lang.NonNull;

import java.util.Date;

public class Device {
    @JSONField(name = "ID")
    private Integer ID;

    private String name;

    private Date dateRegistered;

    private Date dateVisited;

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

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Date getDateVisited() {
        return dateVisited;
    }

    public void setDateVisited(Date dateVisited) {
        this.dateVisited = dateVisited;
    }

    @Override
    @NonNull
    public String toString() {
        return "Device{" + "ID=" + ID + ", name='" + name + '\'' + ", dateRegistered='" + dateRegistered + '\'' + ", dateVisited='" + dateVisited + '\'' + '}';
    }

}
