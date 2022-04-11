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

    List<Device> selectAllDevice();

    List<PhotoFolder> selectAllFolder();

    List<PhotoPhoto> selectAllPhoto();

    List<PhotoTagPhoto> selectAllLabel();

    Version selectAllVersion();

    void initialVersion();

    void initialDevice();

    void initialFolder();

    void clearVersion();

    void clearDevice();

    void clearFolder();

    void clearPhoto();

    void clearLabel();
}
