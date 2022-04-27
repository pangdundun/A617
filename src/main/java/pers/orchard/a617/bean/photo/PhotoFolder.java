package pers.orchard.a617.bean.photo;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.lang.NonNull;

import java.util.Date;

public class PhotoFolder {
    @JSONField(name = "ID")
    private Integer ID;
    @JSONField(name = "IDParent")
    private int IDParent;
    @JSONField(name = "IDCreatedDevice")
    private int IDCreatedDevice;
    @JSONField(name = "IDCover")
    private Integer IDCover;

    private String nameStorage;

    private String nameDisplay;

    private String description;

    private Date dateCreated;

    private Date dateUpdated;

    private int countUpdated;

    public PhotoFolder() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public int getIDParent() {
        return IDParent;
    }

    public void setIDParent(int IDParent) {
        this.IDParent = IDParent;
    }

    public int getIDCreatedDevice() {
        return IDCreatedDevice;
    }

    public void setIDCreatedDevice(int IDCreatedDevice) {
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

    public int getCountUpdated() {
        return countUpdated;
    }

    public void setCountUpdated(int countUpdated) {
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
