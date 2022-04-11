package pers.orchard.a617.bean.photo;

import org.springframework.lang.NonNull;

import java.util.Date;

public class PhotoTagPhoto {
    private Integer ID;
    private String name;

    private Date dateCreated;
    private Date dateUpdated;
    private Integer countUpdated;

    public PhotoTagPhoto() {

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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Integer getCountUpdated() {
        return countUpdated;
    }

    public void setCountUpdated(Integer countUpdated) {
        this.countUpdated = countUpdated;
    }

    @Override
    public String toString() {
        return "PhotoTagPhoto{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                ", countUpdated=" + countUpdated +
                '}';
    }
}
