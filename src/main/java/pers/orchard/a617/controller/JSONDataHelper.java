package pers.orchard.a617.controller;

import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.NotNull;
import pers.orchard.a617.constant.ResultCode;

import java.util.Calendar;

public class JSONDataHelper {
    public static void setResOK(@NotNull JSONObject object) {
        object.put("resultCode", ResultCode.OK);
        object.put("resultInfo", "Operation completed.");
    }

    public static void setResTimeOutOfSync(@NotNull JSONObject object) {
        object.put("resultCode", ResultCode.TIME_OUT_OF_SYNC);
        object.put("resultInfo", "There is a big difference with the server time.");
    }

    public static void setResServerDBError(@NotNull JSONObject object) {
        object.put("resultCode", ResultCode.SERVER_DATABASE_ERROR);
        object.put("resultInfo", "Server database error.");
    }

    public static void setResDataIncorrect(@NotNull JSONObject object) {
        object.put("resultCode", ResultCode.DATA_INCORRECT);
        object.put("resultInfo", "The incoming data is incorrect.");
    }

    public static void setFinishTimeAndTimeConsuming(@NotNull JSONObject object, long startTime) {
        long endTime = Calendar.getInstance().getTime().getTime();
        object.put("finishTime", endTime);

        long timeConsuming = endTime - startTime;
        object.put("timeConsuming", timeConsuming);
    }

}
