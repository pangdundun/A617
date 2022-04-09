package pers.orchard.a617.dao;

import org.apache.ibatis.annotations.Mapper;
import pers.orchard.a617.bean.Device;
import pers.orchard.a617.bean.photo.PhotoFolder;

@Mapper
public interface MainDao {
    Device selectAllDevice();

    PhotoFolder selectAllFolder();
}
