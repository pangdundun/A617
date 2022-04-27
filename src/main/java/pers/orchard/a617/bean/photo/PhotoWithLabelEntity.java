package pers.orchard.a617.bean.photo;

import org.springframework.lang.NonNull;

import java.util.Date;

public class PhotoWithLabelEntity {
    private int labelID;
    private int photoID;
    private Date addedDate;

    public PhotoWithLabelEntity() {
    }

    public PhotoWithLabelEntity(int labelID, int photoID, Date addedDate) {
        this.labelID = labelID;
        this.photoID = photoID;
        this.addedDate = addedDate;
    }

    public int getLabelID() {
        return labelID;
    }

    public void setLabelID(int labelID) {
        this.labelID = labelID;
    }

    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    @Override
    @NonNull
    public String toString() {
        return "PhotoWithLabelEntity{" + "labelID=" + labelID + ", photoID=" + photoID + ", addedDate=" + addedDate + '}';
    }
}
