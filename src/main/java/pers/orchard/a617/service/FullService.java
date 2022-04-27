package pers.orchard.a617.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.orchard.a617.bean.Device;
import pers.orchard.a617.bean.photo.PhotoFolder;
import pers.orchard.a617.bean.photo.PhotoLabel;
import pers.orchard.a617.bean.photo.PhotoPhoto;
import pers.orchard.a617.bean.photo.PhotoWithLabelEntity;
import pers.orchard.a617.constant.TypeCode;
import pers.orchard.a617.controller.JSONDataHelper;
import pers.orchard.a617.dao.MainDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class FullService {
    private static final String TAG = FullService.class.getCanonicalName();

    private final MainDao dao;

    @Autowired
    public FullService(MainDao dao) {
        this.dao = dao;
    }

    public JSONArray fullPull(int type) {
        JSONArray dataArr = new JSONArray();

        switch (type) {
            case TypeCode.DEVICE -> {
                List<Device> devices = dao.selectAllDeviceWithoutRoot();
                dataArr.addAll(devices);
            }
            case TypeCode.FOLDER -> {
                List<PhotoFolder> folders = dao.selectAllFolderWithoutID0();
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
            case TypeCode.LABEL_WITH_PHOTO -> {
                List<PhotoWithLabelEntity> labels = dao.selectAllPhotoWithLabel();
                dataArr.addAll(labels);
            }
        }
        return dataArr;
    }

    public JSONArray fullPush(int type, @NotNull JSONObject resultObj, JSONObject dataObj) {
        JSONArray dataArr = new JSONArray();

        switch (type) {
            case TypeCode.DEVICE -> {
                dao.closeForeignCheck();
                dao.clearDevice();
                dao.clearFolder();
                dao.clearPhoto();
                dao.clearLabel();

                dao.clearPhotoWithLabel();
                dao.deviceInitial();
                dao.folderInitial();
                dao.openForeignCheck();

                Object[] objectArr = JSONHelper.getObjectArr(dataObj);
                if (objectArr.length != 0) {
                    List<Device> list = new ArrayList<>();
                    for (Object o : objectArr) {
                        JSONObject obj = (JSONObject) o;
                        Device device = JSONHelper.castToDevice(obj);
                        list.add(device);
                    }
                    dao.deviceInsertSome(list);
                }
                JSONDataHelper.setResOK(resultObj);
            }
            case TypeCode.FOLDER -> {
                Object[] objectArr = JSONHelper.getObjectArr(dataObj);
                if (objectArr.length != 0) {
                    List<PhotoFolder> list = new ArrayList<>();
                    for (Object o : objectArr) {
                        JSONObject obj = (JSONObject) o;
                        PhotoFolder folder = JSONHelper.castToFolder(obj);
                        list.add(folder);
                    }
                    dao.folderInsertSome(list);
                }
                JSONDataHelper.setResOK(resultObj);
            }
            case TypeCode.PHOTO -> {
                Object[] objectArr = JSONHelper.getObjectArr(dataObj);
                if (objectArr.length != 0) {
                    List<PhotoPhoto> list = new ArrayList<>();
                    for (Object o : objectArr) {
                        JSONObject obj = (JSONObject) o;
                        PhotoPhoto photo = JSONHelper.castToPhoto(obj);
                        list.add(photo);
                    }
                    dao.photoInsertSome(list);
                }
                JSONDataHelper.setResOK(resultObj);
            }
            case TypeCode.LABEL -> {
                Object[] objectArr = JSONHelper.getObjectArr(dataObj);
                if (objectArr.length != 0) {
                    List<PhotoLabel> list = new ArrayList<>();
                    for (Object o : objectArr) {
                        JSONObject obj = (JSONObject) o;
                        PhotoLabel label = JSONHelper.castToLabel(obj);
                        list.add(label);
                    }
                    dao.labelInsertSome(list);
                }
                JSONDataHelper.setResOK(resultObj);
            }
            case TypeCode.LABEL_WITH_PHOTO -> {
                Object[] objectArr = JSONHelper.getObjectArr(dataObj);
                if (objectArr.length != 0) {
                    List<PhotoWithLabelEntity> list = new ArrayList<>();
                    for (Object o : objectArr) {
                        JSONObject obj = (JSONObject) o;
                        PhotoWithLabelEntity entity = JSONHelper.castToPhotoWithLabel(obj);
                        list.add(entity);
                    }
                    dao.insertPhotoWithLabel(list);
                }
                JSONDataHelper.setResOK(resultObj);
            }
        }
        return dataArr;
    }
}
