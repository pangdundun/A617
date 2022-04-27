package pers.orchard.a617.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONValidator;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.orchard.a617.bean.Version;
import pers.orchard.a617.constant.RequestCode;
import pers.orchard.a617.service.CalibrationService;
import pers.orchard.a617.service.FullService;
import pers.orchard.a617.service.IncreaseService;
import pers.orchard.a617.service.TestService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@Controller
public class MainController {
    private static final String TAG = MainController.class.getCanonicalName();

    private final CalibrationService calibrationService;
    private final IncreaseService increaseService;
    private final FullService fullService;
    private final TestService testService;

    @Autowired
    public MainController(CalibrationService calibrationService, IncreaseService increaseService, FullService fullService, TestService testService) {
        this.calibrationService = calibrationService;
        this.increaseService = increaseService;
        this.fullService = fullService;
        this.testService = testService;
    }

    @RequestMapping("")
    private @NotNull String info() {
        return "index";
    }

    /**
     * Synchronous calibration.
     */
    // close before 20220425-124137
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


    /**
     * Send @version 20220418-023421
     * <p>
     * Receive @version 20220418-023447
     */
    @RequestMapping("increase")
    @ResponseBody
    private String increase(@NotNull @RequestBody String json) {
        printStart("increase", json);

        JSONObject resultObj = new JSONObject();
        long startTime = Calendar.getInstance().getTime().getTime();

        JSONObject parse = null;
        if (JSONValidator.from(json).validate()) {
            parse = JSON.parseObject(json);
        }

        if (parse != null) {
            Integer requestCode = parse.getInteger("requestCode");
            Long requestTime = parse.getLong("requestTime");
            Integer deviceID = parse.getInteger("deviceID");

            Integer typeCode = parse.getInteger("typeCode");
            Integer operateCode = parse.getInteger("operateCode");
            Integer ruleCode = parse.getInteger("ruleCode");
            JSONObject dataObj = parse.getJSONObject("data");

            boolean error = requestCode == null || requestTime == null || deviceID == null || typeCode == null || operateCode == null || ruleCode == null || dataObj == null;

            if (!error) {
                if (requestCode == RequestCode.UPLOAD_INCREASE) {
                    increaseService.responseRecords(typeCode, operateCode, ruleCode, dataObj, resultObj);
                } else {
                    JSONDataHelper.setResDataIncorrect(resultObj);
                }
                resultObj.put("request" + "Time", requestTime);
                resultObj.put("deviceID", deviceID);
            } else {
                JSONDataHelper.setResDataIncorrect(resultObj);
            }
        } else {
            JSONDataHelper.setResDataIncorrect(resultObj);
        }

        JSONDataHelper.setTimeConsuming(resultObj, startTime);
        String res = resultObj.toJSONString();
        printEnd("increase", res);
        return res;
    }

    /**
     * Send @version 20220416-232923
     * <p>
     * Receive @version 20220416-234625
     */
    @RequestMapping("fullPull")
    @ResponseBody
    private String fullPull(@NotNull @RequestBody String json) {
        printStart("fullPull", json);

        JSONObject resultObj = new JSONObject();
        long startTime = Calendar.getInstance().getTime().getTime();

        JSONObject parse = null;
        if (JSONValidator.from(json).validate()) {
            parse = JSON.parseObject(json);
        }

        if (parse != null) {
            Integer requestCode = parse.getInteger("requestCode");
            Long requestTime = parse.getLong("requestTime");
            Integer deviceID = parse.getInteger("deviceID");
            Integer typeCode = parse.getInteger("typeCode");

            boolean error = requestCode == null || requestTime == null || deviceID == null || typeCode == null;

            System.out.println("error::" + error);
            if (!error) {
                if (requestCode == RequestCode.DOWNLOAD_ALL_DATA) {
                    Version cloudVersion = calibrationService.getCloudVersion();
                    resultObj.put("version", cloudVersion);

                    JSONArray record = fullService.fullPull(typeCode);
                    resultObj.put("data", record);

                    JSONDataHelper.setResOK(resultObj);
                } else {
                    JSONDataHelper.setResDataIncorrect(resultObj);
                }

                resultObj.put("requestTime", requestTime);
                resultObj.put("deviceID", deviceID);

            } else {
                JSONDataHelper.setResDataIncorrect(resultObj);
            }
        } else {
            JSONDataHelper.setResDataIncorrect(resultObj);
        }

        JSONDataHelper.setTimeConsuming(resultObj, startTime);
        String res = resultObj.toJSONString();
        printEnd("fullPull", res);
        return res;
    }

    @RequestMapping("fullPush")
    @ResponseBody
    private String fullPush(@NotNull @RequestBody String json) {
        printStart("fullPush", json);

        JSONObject resultObj = new JSONObject();
        long startTime = Calendar.getInstance().getTime().getTime();

        JSONObject parse = null;
        if (JSONValidator.from(json).validate()) {
            parse = JSON.parseObject(json);
        }

        if (parse != null) {
            Integer requestCode = parse.getInteger("requestCode");
            Long requestTime = parse.getLong("requestTime");
            Integer deviceID = parse.getInteger("deviceID");

            Integer typeCode = parse.getInteger("typeCode");
            JSONObject dataObj = parse.getJSONObject("data");

            boolean error = requestCode == null || requestTime == null || deviceID == null || typeCode == null || dataObj == null;

            if (!error) {
                if (requestCode == RequestCode.UPLOAD_ALL_DATA) {
                    JSONArray record = fullService.fullPush(typeCode, resultObj, dataObj);
                    resultObj.put("data", record);

                } else {
                    JSONDataHelper.setResDataIncorrect(resultObj);
                }
                resultObj.put("requestTime", requestTime);
                resultObj.put("deviceID", deviceID);
            } else {
                JSONDataHelper.setResDataIncorrect(resultObj);
            }
        } else {
            JSONDataHelper.setResDataIncorrect(resultObj);
        }

        JSONDataHelper.setTimeConsuming(resultObj, startTime);
        String res = resultObj.toJSONString();
        printEnd("fullPush", res);
        return res;
    }

    /**
     * Send @version 20220417-000532
     * <p>
     * Receive @version 20220417-000324
     */
    @RequestMapping("rebuildDatabase")
    @ResponseBody
    private String rebuildDatabase(@NotNull @RequestBody String json) {
        printStart("recreateAllTable", json);

        JSONObject resultObj = new JSONObject();
        long startTime = Calendar.getInstance().getTime().getTime();

        JSONObject parse = null;
        if (JSONValidator.from(json).validate()) {
            parse = JSON.parseObject(json);
        }

        if (parse != null) {
            Integer requestCode = parse.getInteger("requestCode");
            Long requestTime = parse.getLong("requestTime");
            Integer deviceID = parse.getInteger("deviceID");

            boolean error = requestCode == null || requestTime == null || deviceID == null;

            if (!error) {
                if (requestCode == RequestCode.REBUILD_DATABASE) {
                    testService.rebuildDatabase();

                    JSONDataHelper.setResOK(resultObj);
                } else {
                    JSONDataHelper.setResDataIncorrect(resultObj);
                }

                resultObj.put("requestTime", requestTime);
                resultObj.put("deviceID", deviceID);
            } else {
                JSONDataHelper.setResDataIncorrect(resultObj);
            }
        } else {
            JSONDataHelper.setResDataIncorrect(resultObj);
        }

        JSONDataHelper.setTimeConsuming(resultObj, startTime);
        String res = resultObj.toJSONString();
        printEnd("recreateAllTable", res);
        return res;
    }

    /**
     * Send @version 20220417-000532
     * <p>
     * Receive @version 20220417-000324
     */
    @RequestMapping("reinitializeAllTable")
    @ResponseBody
    private String reinitializeAllTable(@NotNull @RequestBody String json) {
        printStart("reinitializeAllTable", json);

        JSONObject resultObj = new JSONObject();
        long startTime = Calendar.getInstance().getTime().getTime();

        JSONObject parse = null;
        if (JSONValidator.from(json).validate()) {
            parse = JSON.parseObject(json);
        }

        if (parse != null) {
            Integer requestCode = parse.getInteger("requestCode");
            Long requestTime = parse.getLong("requestTime");
            Integer deviceID = parse.getInteger("deviceID");

            boolean error = requestCode == null || requestTime == null || deviceID == null;

            if (!error) {
                if (requestCode == RequestCode.REINITIALIZE_ALL_TABLE) {
                    testService.reinitializeAllTable();

                    JSONDataHelper.setResOK(resultObj);
                } else {
                    JSONDataHelper.setResDataIncorrect(resultObj);
                }

                resultObj.put("requestTime", requestTime);
                resultObj.put("deviceID", deviceID);
            } else {
                JSONDataHelper.setResDataIncorrect(resultObj);
            }
        } else {
            JSONDataHelper.setResDataIncorrect(resultObj);
        }

        JSONDataHelper.setTimeConsuming(resultObj, startTime);
        String res = resultObj.toJSONString();
        printEnd("reinitializeAllTable", res);
        return res;
    }

    private void printStart(String interface_, String info) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(Calendar.getInstance().getTime());
        String text = String.format(Locale.CHINA, "[%s] %s Receive data: %s", time, interface_, info);
        System.out.println();
        System.out.println(text);
    }

    private void printEnd(String interface_, String info) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(Calendar.getInstance().getTime());
        String text = String.format(Locale.CHINA, "[%s] %s Return data: %s", time, interface_, info);
        System.out.println(text);
        System.out.println();
    }
}
