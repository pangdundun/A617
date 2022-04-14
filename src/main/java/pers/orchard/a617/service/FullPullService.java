package pers.orchard.a617.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.orchard.a617.bean.Device;
import pers.orchard.a617.bean.photo.PhotoFolder;
import pers.orchard.a617.bean.photo.PhotoPhoto;
import pers.orchard.a617.bean.photo.PhotoTagPhoto;
import pers.orchard.a617.constant.RuleCode;
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
        JSONArray array = new JSONArray();

        if ((type & 1) == 1) {
            JSONObject record = getAllDeviceRecord();
            array.add(record);
        }
        type >>= 1;
        if ((type & 1) == 1) {
            JSONObject record = getAllFolderRecord();
            array.add(record);
        }
        type >>= 1;
        if ((type & 1) == 1) {
            JSONObject record = getAllPhotoRecord();
            array.add(record);
        }
        type >>= 1;
        if ((type & 1) == 1) {
            JSONObject record = getAllLabelRecord();
            array.add(record);
        }

        return array;
    }

    private @NotNull JSONObject getAllDeviceRecord() {
        JSONObject object = new JSONObject();

        object.put("typeCode", TypeCode.DEVICE);
        object.put("ruleCode", RuleCode.OVERWRITE);

        JSONArray array = new JSONArray();
        List<Device> devices = dao.selectAllDevice();
        array.addAll(devices);
        object.put("data", array);

        return object;
    }

    private @NotNull JSONObject getAllFolderRecord() {
        JSONObject object = new JSONObject();

        object.put("typeCode", TypeCode.FOLDER);
        object.put("ruleCode", RuleCode.OVERWRITE);

        JSONArray array = new JSONArray();
        List<PhotoFolder> folders = dao.selectAllFolder();
        array.addAll(folders);
        object.put("data", array);

        return object;
    }

    private @NotNull JSONObject getAllPhotoRecord() {
        JSONObject object = new JSONObject();

        object.put("typeCode", TypeCode.PHOTO);
        object.put("ruleCode", RuleCode.OVERWRITE);

        JSONArray array = new JSONArray();
        List<PhotoPhoto> photos = dao.selectAllPhoto();
        array.addAll(photos);

        for (Object o : array) {
            JSONObject obj = (JSONObject) o;
            obj.remove("iDsStorageDeviceTransfer");
            obj.remove("iDsTagTransfer");
            obj.remove("namesStorageDeviceTransfer");
            obj.remove("namesTagTransfer");
        }

        object.put("data", array);

        return object;
    }

    private @NotNull JSONObject getAllLabelRecord() {
        JSONObject object = new JSONObject();

        object.put("typeCode", TypeCode.LABEL);
        object.put("ruleCode", RuleCode.OVERWRITE);

        JSONArray array = new JSONArray();
        List<PhotoTagPhoto> labels = dao.selectAllLabel();
        array.addAll(labels);
        object.put("data", array);

        return object;
    }
}
