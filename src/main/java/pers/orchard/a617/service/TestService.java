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
