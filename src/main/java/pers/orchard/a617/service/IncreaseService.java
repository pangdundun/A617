package pers.orchard.a617.service;

import com.alibaba.fastjson.JSONArray;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.orchard.a617.dao.MainDao;

@Service
public class IncreaseService {
    private final MainDao dao;

    @Autowired
    public IncreaseService(MainDao dao) {
        this.dao = dao;
    }

    public boolean responseRecords(@NotNull JSONArray records) {

    }
}
