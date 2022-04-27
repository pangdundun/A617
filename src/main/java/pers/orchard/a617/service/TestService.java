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

    public void rebuildDatabase() {
        dao.closeForeignCheck();

        dao.dropVersionTable();
        dao.dropDeviceTable();
        dao.dropFolderTable();
        dao.dropPhotoTable();
        dao.dropLabelTable();
        dao.dropPhotoWithLabelTable();

        dao.setNoAutoValueOnZero();
        dao.createVersionTable();
        dao.createDeviceTable();
        dao.createFolderTable();
        dao.createPhotoTable();
        dao.createLabelTable();
        dao.createPhotoWithLabelTable();

        dao.setNoAutoValueOnZero();
        dao.deviceInitial();
        dao.folderInitial();

        dao.openForeignCheck();
    }

    public void reinitializeAllTable() {
        dao.closeForeignCheck();

        dao.clearVersion();
        dao.clearDevice();
        dao.clearFolder();
        dao.clearPhoto();
        dao.clearLabel();

        dao.versionInitial();

        dao.setNoAutoValueOnZero();
        dao.deviceInitial();
        dao.folderInitial();

        dao.openForeignCheck();
    }
}
