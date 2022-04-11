package pers.orchard.a617.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.orchard.a617.bean.Device;
import pers.orchard.a617.bean.Version;
import pers.orchard.a617.dao.MainDao;

import java.util.Calendar;
import java.util.List;

@Service
public class CalibrationService {
    private final MainDao dao;

    @Autowired
    public CalibrationService(MainDao dao) {
        this.dao = dao;
    }

    /**
     * Synchronous calibration.
     */
    public String calibration(Version localVersion) {
        JSONObject jsonObject = new JSONObject();

//        long currentTime = getCurrentTime();
//        jsonObject.put("createdTime", currentTime);
//        jsonObject.put("generateDevice", "cloud");
//
//        jsonObject.put("version", "xxx123");
//
//        JSONArray recordArr = new JSONArray();
//
//        JSONArray versionArr = new JSONArray();
//
//        Version cloudVersion = getCloudVersion();
//
//        if (cloudVersion.getDeviceDateUpdated() > localVersion.getDeviceVersion()) {
//            List<Device> devices = getAllDevice();
//            JSONObject record = transformDeviceToJSONArray(devices, "overwrite");
//            recordArr.add(record);
//        }
//
//
//        jsonObject.put("record", );
//
//        jsonObject.put("dataType", "device");
//        jsonObject.put("rule", "overwrite");
//
//        List<Device> devices = service.getAllDevice();
//        jsonObject.put("data", devices);

        return jsonObject.toJSONString();
    }

    private long getCurrentTime() {
        return Calendar.getInstance().getTime().getTime();
    }

    public Version getCloudVersion() {
        return dao.selectAllVersion();
    }

    private List<Device> getAllDevice() {
        return dao.selectAllDevice();
    }

    /**
     * @param rule Can be the following values: add, update, delete, overwrite, empty, emptyAndReinitialize.
     */
    private @NotNull JSONObject transformDeviceToJSONArray(@NotNull List<Device> list, @NotNull String rule) {
        JSONObject object = new JSONObject();

        object.put("type", "device");
        object.put("rule", rule);

        JSONArray dataArr = new JSONArray();
        for (Device device : list) {
            String data = JSON.toJSONString(device);
            dataArr.add(data);
        }
        object.put("data", dataArr);

        return object;
    }
}
