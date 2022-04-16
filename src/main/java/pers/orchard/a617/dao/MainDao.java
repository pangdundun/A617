package pers.orchard.a617.dao;

import org.apache.ibatis.annotations.Mapper;
import pers.orchard.a617.bean.Device;
import pers.orchard.a617.bean.Version;
import pers.orchard.a617.bean.photo.PhotoFolder;
import pers.orchard.a617.bean.photo.PhotoPhoto;
import pers.orchard.a617.bean.photo.PhotoTagPhoto;

import java.util.List;

@Mapper
public interface MainDao {

    int dropVersionTable();

    int dropDeviceTable();

    int dropFolderTable();

    int dropPhotoTable();

    int dropLabelTable();

    int createDeviceTable();

    int createFolderTable();

    int createPhotoTable();

    int createLabelTable();

    int createVersionTable();

    List<Device> selectAllDevice();

    List<PhotoFolder> selectAllFolder();

    List<PhotoPhoto> selectAllPhoto();

    List<PhotoTagPhoto> selectAllLabel();

    Version selectAllVersion();

    void versionInitial();

    void deviceInitial();

    void folderInitial();

    int deviceInsertOne(Device device);

    int deviceInsertSome(List<Device> list);

    int folderInsertOne(PhotoFolder folder);

    int folderInsertSome(List<PhotoFolder> list);

    int photoInsertOne(PhotoPhoto photo);

    int photoInsertSome(List<PhotoPhoto> list);

    int labelInsertOne(PhotoTagPhoto label);

    int labelInsertSome(List<PhotoTagPhoto> list);

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

    int updateLabel(PhotoTagPhoto label);

    void labelRename(int ID, String name);

    int deleteSomeDevice(int[] IDs);

    int deleteSomeFolder(int[] IDs);

    int deleteSomePhoto(int[] IDs);

    int deleteSomeLabel(int[] IDs);

    void clearVersion();

    void clearDevice();

    void clearFolder();

    void clearPhoto();

    void clearLabel();

    void closeForeignCheck();

    void openForeignCheck();
}
