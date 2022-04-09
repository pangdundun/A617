package pers.orchard.a617.bean.photo;

import org.springframework.lang.NonNull;

public class PhotoTagPhoto {
    private Integer ID;
    private String name;

    private String dateCreated;
    private String dateUpdated;
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

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Integer getCountUpdated() {
        return countUpdated;
    }

    public void setCountUpdated(Integer countUpdated) {
        this.countUpdated = countUpdated;
    }

    @Override
    @NonNull
    public String toString() {
        return "PhotoTagPhoto{" + "ID=" + ID + ", name='" + name + '\'' + ", dateCreated='" + dateCreated + '\'' + ", dateUpdated='" + dateUpdated + '\'' + ", countUpdated='" + countUpdated + '\'' + '}';
    }
}
