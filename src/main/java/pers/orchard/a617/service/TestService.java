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

    public void recreateAllTable() {
        dao.closeForeignCheck();

        dao.dropVersionTable();
        dao.dropDeviceTable();
        dao.dropFolderTable();
        dao.dropPhotoTable();
        dao.dropLabelTable();

        dao.createVersionTable();
        dao.createDeviceTable();
        dao.createFolderTable();
        dao.createPhotoTable();
        dao.createLabelTable();

        dao.openForeignCheck();
    }

    public void initialAllTable() {
        dao.closeForeignCheck();

        dao.clearVersion();
        dao.clearDevice();
        dao.clearFolder();
        dao.clearPhoto();
        dao.clearLabel();

        dao.openForeignCheck();

        dao.versionInitial();
        dao.deviceInitial();
        dao.folderInitial();
    }
}
