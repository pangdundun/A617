package pers.orchard.a617.dao;

import org.apache.ibatis.annotations.Mapper;
import pers.orchard.a617.bean.Device;
import pers.orchard.a617.bean.Version;
import pers.orchard.a617.bean.photo.PhotoFolder;
import pers.orchard.a617.bean.photo.PhotoPhoto;
import pers.orchard.a617.bean.photo.PhotoLabel;
import pers.orchard.a617.bean.photo.PhotoWithLabelEntity;

import java.util.List;

@Mapper
public interface MainDao {

    int dropVersionTable();

    int dropDeviceTable();

    int dropFolderTable();

    int dropPhotoTable();

    int dropLabelTable();

    int dropPhotoWithLabelTable();

    int createDeviceTable();

    int createFolderTable();

    int createPhotoTable();

    int createLabelTable();

    int createPhotoWithLabelTable();

    int createVersionTable();

    List<Device> selectAllDevice();

    List<Device> selectAllDeviceWithoutRoot();

    List<Device> selectAllDeviceWithoutID0();

    Device selectDeviceByID(int iD);

    List<PhotoFolder> selectAllFolder();

    List<PhotoFolder> selectAllFolderWithoutID0();

    PhotoFolder selectFolderByID(int ID);

    int getFolderCountInSameFolderByName(int IDFolder, String nameDisplay);

    int getFolderCountWhereID(int ID);

    List<PhotoPhoto> selectAllPhoto();

    int getPhotoCountWhereID(int ID);

    List<PhotoLabel> selectAllLabel();

    int getLabelCountByName(String name);

    int getLabelCountWhereID(int ID);

    int getLabelCountWhereIDAndName(int ID, String name);

    Version selectAllVersion();

    void versionInitial();

    void deviceInitial();

    void folderInitial();

    int deviceInsertOneMini(Device device);

    int deviceInsertOne(Device device);

    int deviceInsertSome(List<Device> list);

    int folderInsertOneMini(PhotoFolder folder);

    int folderInsertOne(PhotoFolder folder);

    int folderInsertSome(List<PhotoFolder> list);

    int photoInsertOne(PhotoPhoto photo);

    int photoInsertSome(List<PhotoPhoto> list);

    int labelInsertOne(PhotoLabel label);

    int labelInsertSome(List<PhotoLabel> list);

    int deviceUpdate(Device device);

    int folderUpdate(PhotoFolder folder);

    int folderMove(int parentID, int[] IDs);

    void folderRename(int ID, String nameDisplay);

    void folderUpdateDescription(int ID, String nameDisplay);

    int photoUpdate(PhotoPhoto photo);

    void photoMove(int IDFolder, int[] IDs);

    void photoRename(int ID, String nameDisplay);

    void photoUpdateDescription(int ID, String description);

    void photoUpdateLabels(int ID, String IDs, String names);

    void photoUpdateMD5(int ID, String MD5);

    int updateLabel(PhotoLabel label);

    void labelRename(PhotoLabel label);

    int deleteSomeDevice(int[] IDs);

    int deleteOneFolder(int ID);

    int deleteSomePhoto(int[] IDs);

    int deletePhotoWhereFolderIDs(int[] IDs);

    int deleteSomeLabel(int[] IDs);

    List<PhotoWithLabelEntity> selectAllPhotoWithLabel();

    int getPhotoWithLabelCountWhereLabelID(int ID);

    void insertPhotoWithLabel(List<PhotoWithLabelEntity> list);

    int deletePhotoWithLabelWhereLabelID(int labelID);

    int deletePhotoWithLabelWherePhotoID(int photoID);

    int deletePhotoWithLabelWhereLabelIDAndPhotoID(PhotoWithLabelEntity entity);

    void clearVersion();

    void clearDevice();

    void clearFolder();

    void clearPhoto();

    void clearLabel();

    void clearPhotoWithLabel();

    void setNoAutoValueOnZero();

    void closeForeignCheck();

    void openForeignCheck();
}
