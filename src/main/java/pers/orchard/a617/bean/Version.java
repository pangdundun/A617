package pers.orchard.a617.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Version {
    @JSONField(name = "deviceVersion")
    private Integer deviceVersion;
    @JSONField(name = "deviceDateUpdated")
    private Date deviceDateUpdated;

    @JSONField(name = "folderVersion")
    private Integer folderVersion;
    @JSONField(name = "folderDateUpdated")
    private Date folderDateUpdated;

    @JSONField(name = "photoVersion")
    private Integer photoVersion;
    @JSONField(name = "photoDateUpdated")
    private Date photoDateUpdated;

    @JSONField(name = "labelVersion")
    private Integer labelVersion;
    @JSONField(name = "labelDateUpdated")
    private Date labelDateUpdated;

    public Version() {
    }

    public Integer getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(Integer deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    public Date getDeviceDateUpdated() {
        return deviceDateUpdated;
    }

    public void setDeviceDateUpdated(Date deviceDateUpdated) {
        this.deviceDateUpdated = deviceDateUpdated;
    }

    public Integer getFolderVersion() {
        return folderVersion;
    }

    public void setFolderVersion(Integer folderVersion) {
        this.folderVersion = folderVersion;
    }

    public Date getFolderDateUpdated() {
        return folderDateUpdated;
    }

    public void setFolderDateUpdated(Date folderDateUpdated) {
        this.folderDateUpdated = folderDateUpdated;
    }

    public Integer getPhotoVersion() {
        return photoVersion;
    }

    public void setPhotoVersion(Integer photoVersion) {
        this.photoVersion = photoVersion;
    }

    public Date getPhotoDateUpdated() {
        return photoDateUpdated;
    }

    public void setPhotoDateUpdated(Date photoDateUpdated) {
        this.photoDateUpdated = photoDateUpdated;
    }

    public Integer getLabelVersion() {
        return labelVersion;
    }

    public void setLabelVersion(Integer labelVersion) {
        this.labelVersion = labelVersion;
    }

    public Date getLabelDateUpdated() {
        return labelDateUpdated;
    }

    public void setLabelDateUpdated(Date labelDateUpdated) {
        this.labelDateUpdated = labelDateUpdated;
    }
}
