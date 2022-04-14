package pers.orchard.a617.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.orchard.a617.dao.MainDao;

@Service
public class TestService {
    private final MainDao dao;

    @Autowired
    public TestService(MainDao dao) {
        this.dao = dao;
    }

    public void createAllTable() {
        dao.createVersionTable();
        dao.createDeviceTable();
        dao.createFolderTable();
        dao.createPhotoTable();
        dao.createLabelTable();
    }

    public void clearAllTable() {
        dao.closeForeignCheck();

        dao.clearVersion();
        dao.clearPhoto();
        dao.clearLabel();
        dao.clearFolder();
        dao.clearDevice();

        dao.openForeignCheck();

        dao.initialVersion();
//        dao.initialDevice();
//        dao.initialFolder();
    }
}
