package pers.orchard.a617.bean.photo;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.lang.NonNull;

import java.util.Date;

public class PhotoFolder {
    @JSONField(name = "ID")
    private Integer ID;
    @JSONField(name = "IDParent")
    private Integer IDParent;
    @JSONField(name = "IDCreatedDevice")
    private Integer IDCreatedDevice;
    @JSONField(name = "IDCover")
    private Integer IDCover;

    @JSONField(name = "nameStorage")
    private String nameStorage;
    @JSONField(name = "nameDisplay")
    private String nameDisplay;

    @JSONField(name = "description")
    private String description;

    @JSONField(name = "dateCreated")
    private Date dateCreated;
    @JSONField(name = "dateUpdated")
    private Date dateUpdated;
    @JSONField(name = "countUpdated")
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
