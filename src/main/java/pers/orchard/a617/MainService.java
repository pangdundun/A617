package pers.orchard.a617;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.orchard.a617.dao.MainDao;

@Service
public class MainService {
    private final MainDao dao;

    @Autowired
    public MainService(MainDao dao) {
        this.dao = dao;
    }
}
