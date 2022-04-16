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

    void initialVersion();

    void initialDevice();

    void initialFolder();

    int insertSomeDevice(List<Device> list);

    int insertSomeFolder(List<PhotoFolder> list);

    int insertSomePhoto(List<PhotoPhoto> list);

    int insertSomeLabel(List<PhotoTagPhoto> list);

    int updateDevice(Device device);

    int updateFolder(PhotoFolder folder);

    int moveFolder(List<Integer> list);

    int updatePhoto(PhotoPhoto photo);

    int updateLabel(PhotoTagPhoto label);

    int deleteSomeDevice(List<Integer> IDs);

    int deleteSomeFolder(List<Integer> IDs);

    int deleteSomePhoto(List<Integer> IDs);

    int deleteSomeLabel(List<Integer> IDs);

    void clearVersion();

    void clearDevice();

    void clearFolder();

    void clearPhoto();

    void clearLabel();

    void closeForeignCheck();

    void openForeignCheck();
}
