package pers.orchard.a617.service;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.orchard.a617.bean.Device;
import pers.orchard.a617.bean.photo.PhotoFolder;
import pers.orchard.a617.bean.photo.PhotoPhoto;
import pers.orchard.a617.bean.photo.PhotoLabel;
import pers.orchard.a617.constant.TypeCode;
import pers.orchard.a617.dao.MainDao;

import java.util.List;

@Service
public class FullPullService {
    private static final String TAG = FullPullService.class.getCanonicalName();

    private final MainDao dao;

    @Autowired
    public FullPullService(MainDao dao) {
        this.dao = dao;
    }

    public JSONArray fullPull(int type) {
        JSONArray dataArr = new JSONArray();

        switch (type) {
            case TypeCode.DEVICE -> {
                List<Device> devices = dao.selectAllDevice();
                dataArr.addAll(devices);
            }
            case TypeCode.FOLDER -> {
                List<PhotoFolder> folders = dao.selectAllFolder();
                dataArr.addAll(folders);
            }
            case TypeCode.PHOTO -> {
                List<PhotoPhoto> photos = dao.selectAllPhoto();
                dataArr.addAll(photos);
            }
            case TypeCode.LABEL -> {
                List<PhotoLabel> labels = dao.selectAllLabel();
                dataArr.addAll(labels);
            }
        }
        return dataArr;
    }
}
