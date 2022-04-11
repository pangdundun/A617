package pers.orchard.a617.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.orchard.a617.bean.Device;
import pers.orchard.a617.dao.MainDao;

import java.util.List;

@Service
public class IncreaseService {
    private final MainDao dao;

    @Autowired
    public IncreaseService(MainDao dao) {
        this.dao = dao;
    }

    public List<Device> getAllDevice() {
        return dao.selectAllDevice();
    }

    public void clearAllTable() {
        dao.clearVersion();
        dao.clearDevice();
        dao.clearFolder();
        dao.clearPhoto();
        dao.clearLabel();

        dao.initialVersion();
        dao.initialDevice();
        dao.initialFolder();
    }
}
