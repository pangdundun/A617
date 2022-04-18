package pers.orchard.a617.bean.photo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.Date;

public class PhotoPhoto {
    @JSONField(name = "ID")
    private Integer ID;
    @JSONField(name = "IDFolder")
    private Integer IDFolder;
    @JSONField(name = "IDStory")
    private Integer IDStory;
    @JSONField(name = "IDsTag")
    private Integer[] IDsTag;
    @JSONField(name = "IDsTagTransfer")
    private String IDsTagTransfer;
    @JSONField(name = "IDRegisterDevice")
    private Integer IDRegisterDevice;
    @JSONField(name = "IDsStorageDevice")
    private Integer[] IDsStorageDevice;
    @JSONField(name = "IDsStorageDeviceTransfer")
    private String IDsStorageDeviceTransfer;

    @JSONField(name = "nameDisplay")
    private String nameDisplay;
    @JSONField(name = "nameStorage")
    private String nameStorage;
    @JSONField(name = "nameRegister")
    private String nameRegister;
    @JSONField(name = "nameFolder")
    private String nameFolder;
    @JSONField(name = "namesTag")
    private String[] namesTag;
    @JSONField(name = "namesTagTransfer")
    private String namesTagTransfer;
    @JSONField(name = "namesStorageDevice")
    private String[] namesStorageDevice;
    @JSONField(name = "namesStorageDeviceTransfer")
    private String namesStorageDeviceTransfer;

    @JSONField(name = "fileSize")
    private Integer fileSize;
    @JSONField(name = "width")
    private Integer width;
    @JSONField(name = "height")
    private Integer height;
    @JSONField(name = "mimeType")
    private String mimeType;
    @JSONField(name = "latitude")
    private String latitude;
    @JSONField(name = "longitude")
    private String longitude;
    @JSONField(name = "orientation")
    private Integer orientation;
    @JSONField(name = "dateTaken")
    private Date dateTaken;
    @JSONField(name = "description")
    private String description;
    @JSONField(name = "MD5")
    private String MD5;

    @JSONField(name = "presenceLocalFull")
    private Boolean presenceLocalFull;
    @JSONField(name = "presenceLocalThumb")
    private Boolean presenceLocalThumb;
    @JSONField(name = "presenceCloudThumb")
    private Boolean presenceCloudThumb;
    @JSONField(name = "pathLocalFull")
    private String pathLocalFull;
    @JSONField(name = "pathLocalThumb")
    private String pathLocalThumb;

    @JSONField(name = "dateRegistered")
    private Date dateRegistered;
    @JSONField(name = "dateUpdated")
    private Date dateUpdated;
    @JSONField(name = "countUpdated")
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
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
