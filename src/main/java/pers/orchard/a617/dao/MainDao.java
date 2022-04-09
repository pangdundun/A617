package pers.orchard.a617.dao;

import org.apache.ibatis.annotations.Mapper;
import pers.orchard.a617.bean.Device;
import pers.orchard.a617.bean.photo.PhotoFolder;

import java.util.List;

@Mapper
public interface MainDao {

    List<Device> selectAllDevice();

    int select1();

    List<PhotoFolder> selectAllFolder();
}
