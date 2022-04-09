package pers.orchard.a617.bean.photo;

import org.springframework.lang.NonNull;

public class PhotoFolder {
    private Integer ID;
    private Integer IDParent;
    private Integer IDCreatedDevice;
    private Integer IDCover;

    private String nameStorage;
    private String nameDisplay;

    private String description;

    private String dateCreated;
    private String dateUpdated;
    private Integer countUpdated;

    public PhotoFolder() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getIDParent() {
        return IDParent;
    }

    public void setIDParent(Integer IDParent) {
        this.IDParent = IDParent;
    }

    public Integer getIDCreatedDevice() {
        return IDCreatedDevice;
    }

    public void setIDCreatedDevice(Integer IDCreatedDevice) {
        this.IDCreatedDevice = IDCreatedDevice;
    }

    public Integer getIDCover() {
        return IDCover;
    }

    public void setIDCover(Integer IDCover) {
        this.IDCover = IDCover;
    }

    public String getNameStorage() {
        return nameStorage;
    }

    public void setNameStorage(String nameStorage) {
        this.nameStorage = nameStorage;
    }

    public String getNameDisplay() {
        return nameDisplay;
    }

    public void setNameDisplay(String nameDisplay) {
        this.nameDisplay = nameDisplay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "PhotoFolder{" +
                "ID=" + ID +
                ", IDParent=" + IDParent +
                ", IDCreatedDevice=" + IDCreatedDevice +
                ", IDCover=" + IDCover +
                ", nameStorage='" + nameStorage + '\'' +
                ", nameDisplay='" + nameDisplay + '\'' +
                ", description='" + description + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateUpdated='" + dateUpdated + '\'' +
                ", countUpdated=" + countUpdated +
                '}';
    }
}
