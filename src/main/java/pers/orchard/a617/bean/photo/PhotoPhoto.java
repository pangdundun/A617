package pers.orchard.a617.bean.photo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.Date;

public class PhotoPhoto {
    private Integer ID;
    private Integer IDFolder;
    private Integer IDStory;
    private Integer[] IDsTag;
    private String IDsTagTransfer;
    private Integer IDRegisterDevice;
    private Integer[] IDsStorageDevice;
    private String IDsStorageDeviceTransfer;

    private String nameDisplay;
    private String nameStorage;
    private String nameRegister;
    private String nameFolder;
    private String[] namesTag;
    private String namesTagTransfer;
    private String[] namesStorageDevice;
    private String namesStorageDeviceTransfer;

    private Integer fileSize;
    private Integer width;
    private Integer height;
    private String mimeType;
    private Integer latitude;
    private Integer longitude;
    private Integer orientation;
    private Date dateTaken;
    private String description;
    private String MD5;

    private Boolean presenceLocalFull;
    private Boolean presenceLocalThumb;
    private Boolean presenceCloudThumb;
    private String pathLocalFull;
    private String pathLocalThumb;

    private Date dateRegistered;
    private Date dateUpdated;
    private Integer countUpdated;

    public PhotoPhoto() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getIDFolder() {
        return IDFolder;
    }

    public void setIDFolder(Integer IDFolder) {
        this.IDFolder = IDFolder;
    }

    public Integer getIDStory() {
        return IDStory;
    }

    public void setIDStory(Integer IDStory) {
        this.IDStory = IDStory;
    }

    public Integer[] getIDsTag() {
        return IDsTag;
    }

    public void setIDsTag(Integer[] IDsTag) {
        this.IDsTag = IDsTag;
    }

    public String getIDsTagTransfer() {
        return JSON.toJSONString(IDsTag);
    }

    public void setIDsTagTransfer(String IDsTagTransfer) {
        JSONArray array = JSON.parseArray(IDsTagTransfer);

        Integer[] arr = new Integer[array.size()];
        for (int i = 0; i < array.size(); i++) {
            arr[i] = array.getInteger(i);
        }
        IDsTag = arr;
    }

    public Integer getIDRegisterDevice() {
        return IDRegisterDevice;
    }

    public void setIDRegisterDevice(Integer IDRegisterDevice) {
        this.IDRegisterDevice = IDRegisterDevice;
    }

    public Integer[] getIDsStorageDevice() {
        return IDsStorageDevice;
    }

    public void setIDsStorageDevice(Integer[] IDsStorageDevice) {
        this.IDsStorageDevice = IDsStorageDevice;
    }

    public String getIDsStorageDeviceTransfer() {
        return JSON.toJSONString(IDsStorageDevice);
    }

    public void setIDsStorageDeviceTransfer(String IDsStorageDeviceTransfer) {
        JSONArray array = JSON.parseArray(IDsStorageDeviceTransfer);

        Integer[] arr = new Integer[array.size()];
        for (int i = 0; i < array.size(); i++) {
            arr[i] = array.getInteger(i);
        }
        IDsStorageDevice = arr;
    }

    public String getNameDisplay() {
        return nameDisplay;
    }

    public void setNameDisplay(String nameDisplay) {
        this.nameDisplay = nameDisplay;
    }

    public String getNameStorage() {
        return nameStorage;
    }

    public void setNameStorage(String nameStorage) {
        this.nameStorage = nameStorage;
    }

    public String getNameRegister() {
        return nameRegister;
    }

    public void setNameRegister(String nameRegister) {
        this.nameRegister = nameRegister;
    }

    public String getNameFolder() {
        return nameFolder;
    }

    public void setNameFolder(String nameFolder) {
        this.nameFolder = nameFolder;
    }

    public String[] getNamesTag() {
        return namesTag;
    }

    public void setNamesTag(String[] namesTag) {
        this.namesTag = namesTag;
    }

    public String getNamesTagTransfer() {
        return JSON.toJSONString(namesTag);
    }

    public void setNamesTagTransfer(String namesTagTransfer) {
        JSONArray array = JSON.parseArray(namesTagTransfer);

        String[] arr = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            arr[i] = array.getString(i);
        }
        namesTag = arr;
    }

    public String[] getNamesStorageDevice() {
        return namesStorageDevice;
    }

    public void setNamesStorageDevice(String[] namesStorageDevice) {
        this.namesStorageDevice = namesStorageDevice;
    }

    public String getNamesStorageDeviceTransfer() {
        return JSON.toJSONString(namesStorageDevice);
    }

    public void setNamesStorageDeviceTransfer(String namesStorageDeviceTransfer) {
        JSONArray array = JSON.parseArray(namesStorageDeviceTransfer);

        String[] arr = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            arr[i] = array.getString(i);
        }
        namesStorageDevice = arr;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getOrientation() {
        return orientation;
    }

    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }

    public Date getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(Date dateTaken) {
        this.dateTaken = dateTaken;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMD5() {
        return MD5;
    }

    public void setMD5(String MD5) {
        this.MD5 = MD5;
    }

    public Boolean getPresenceLocalFull() {
        return presenceLocalFull;
    }

    public void setPresenceLocalFull(Boolean presenceLocalFull) {
        this.presenceLocalFull = presenceLocalFull;
    }

    public Boolean getPresenceLocalThumb() {
        return presenceLocalThumb;
    }

    public void setPresenceLocalThumb(Boolean presenceLocalThumb) {
        this.presenceLocalThumb = presenceLocalThumb;
    }

    public Boolean getPresenceCloudThumb() {
        return presenceCloudThumb;
    }

    public void setPresenceCloudThumb(Boolean presenceCloudThumb) {
        this.presenceCloudThumb = presenceCloudThumb;
    }

    public String getPathLocalFull() {
        return pathLocalFull;
    }

    public void setPathLocalFull(String pathLocalFull) {
        this.pathLocalFull = pathLocalFull;
    }

    public String getPathLocalThumb() {
        return pathLocalThumb;
    }

    public void setPathLocalThumb(String pathLocalThumb) {
        this.pathLocalThumb = pathLocalThumb;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
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
        return "PhotoPhoto{" +
                "ID=" + ID +
                ", IDFolder=" + IDFolder +
                ", IDStory=" + IDStory +
                ", IDsTag=" + Arrays.toString(IDsTag) +
                ", IDRegisterDevice=" + IDRegisterDevice +
                ", IDsStorageDevice=" + Arrays.toString(IDsStorageDevice) +
                ", nameDisplay='" + nameDisplay + '\'' +
                ", nameStorage='" + nameStorage + '\'' +
                ", nameRegister='" + nameRegister + '\'' +
                ", nameFolder='" + nameFolder + '\'' +
                ", namesTag=" + Arrays.toString(namesTag) +
                ", namesStorageDevice=" + Arrays.toString(namesStorageDevice) +
                ", fileSize=" + fileSize +
                ", width=" + width +
                ", height=" + height +
                ", mimeType='" + mimeType + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", orientation=" + orientation +
                ", dateTaken='" + dateTaken + '\'' +
                ", description='" + description + '\'' +
                ", MD5='" + MD5 + '\'' +
                ", presenceLocalFull=" + presenceLocalFull +
                ", presenceLocalThumb=" + presenceLocalThumb +
                ", presenceCloudThumb=" + presenceCloudThumb +
                ", pathLocalFull='" + pathLocalFull + '\'' +
                ", pathLocalThumb='" + pathLocalThumb + '\'' +
                ", dateRegistered='" + dateRegistered + '\'' +
                ", dateUpdated='" + dateUpdated + '\'' +
                ", countUpdated=" + countUpdated +
                '}';
    }
}
