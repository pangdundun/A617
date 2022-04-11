package pers.orchard.a617.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.orchard.a617.constant.RequestCode;
import pers.orchard.a617.service.FullPullService;
import pers.orchard.a617.service.IncreaseService;
import pers.orchard.a617.bean.Device;
import pers.orchard.a617.bean.Version;
import pers.orchard.a617.service.CalibrationService;

import java.util.Calendar;
import java.util.List;

@Controller
public class MainController {
    private static final String TAG = MainController.class.getCanonicalName();

    private final CalibrationService calibrationService;
    private final IncreaseService increaseService;
    private final FullPullService fullPullService;

    @Autowired
    public MainController(CalibrationService calibrationService, IncreaseService increaseService, FullPullService fullPullService) {
        this.calibrationService = calibrationService;
        this.increaseService = increaseService;
        this.fullPullService = fullPullService;
    }

    @Contract(pure = true)
//    @RequestMapping("")
    @ResponseBody
    private @NotNull String info() {
        JSONObject jsonObject = new JSONObject();

        long time = Calendar.getInstance().getTime().getTime();
        jsonObject.put("createdTime", time);

        jsonObject.put("generateDevice", "cloud");
        jsonObject.put("dataType", "device");
        jsonObject.put("rule", "overwrite");

        List<Device> devices = increaseService.getAllDevice();
        jsonObject.put("data", devices);

        return jsonObject.toJSONString();
    }

    /**
     * Synchronous calibration.
     */
//    @RequestMapping("calibration")
    private String calibration(@NotNull @RequestBody String json) {
        JSONObject parse = JSON.parseObject(json);
        JSONObject versionObject = parse.getJSONObject("localVersion");

        if (versionObject == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("errorInfo", "Bad data was passed in.");
            return jsonObject.toJSONString();
        }

        // If the operation is correct, this comment line will be deleted.

//        Version version = Transform.transform(versionObject);
        Version version = versionObject.toJavaObject(Version.class);
        return calibrationService.calibration(version);
    }

    @RequestMapping("fullPull")
    @ResponseBody
    private String fullPull(@NotNull @RequestBody String json) {
        JSONObject resultObj = new JSONObject();
        long startTime = Calendar.getInstance().getTime().getTime();

        JSONObject parse = JSON.parseObject(json);
        Integer requestCode = parse.getInteger("requestCode");
        Integer type = parse.getInteger("type");
        Integer deviceID = parse.getInteger("deviceID");

        boolean error = false;

        if (deviceID == null) {
            error = true;
        } else {
            resultObj.put("deviceID", deviceID);
        }

        if (requestCode != null && requestCode == RequestCode.DOWNLOAD_ALL_DATA) {
            if (type == null) {
                error = true;
            }
        } else {
            error = true;
        }

        if (!error) {
            JSONArray record = fullPullService.fullPull(type);
            resultObj.put("record", record);

            Version cloudVersion = calibrationService.getCloudVersion();
            resultObj.put("version", cloudVersion);

            JSONDataHelper.setResOK(resultObj);

        } else {
            JSONDataHelper.setResDataIncorrect(resultObj);
        }

        JSONDataHelper.setFinishTimeAndTimeConsuming(resultObj, startTime);
        return resultObj.toJSONString();
    }

    @RequestMapping("clearAllTable")
    @ResponseBody
    private void clear() {
        increaseService.clearAllTable();
    }


}
