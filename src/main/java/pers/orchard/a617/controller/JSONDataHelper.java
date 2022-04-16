package pers.orchard.a617.controller;

import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.NotNull;
import pers.orchard.a617.constant.ResponseCode;

import java.util.Calendar;

public class JSONDataHelper {
    public static void setResOK(@NotNull JSONObject object) {
        object.put("responseCode", ResponseCode.OK);
        object.put("responseInfo", "Operation completed.");
    }

    public static void setResTimeOutOfSync(@NotNull JSONObject object) {
        object.put("responseCode", ResponseCode.TIME_OUT_OF_SYNC);
        object.put("responseInfo", "There is a big difference with the server time.");
    }

    public static void setResServerDBError(@NotNull JSONObject object) {
        object.put("responseCode", ResponseCode.SERVER_DATABASE_ERROR);
        object.put("responseInfo", "Server database error.");
    }

    public static void setResDataIncorrect(@NotNull JSONObject object) {
        object.put("responseCode", ResponseCode.DATA_INCORRECT);
        object.put("responseInfo", "The incoming data is incorrect.");
    }

    public static void setTimeConsuming(@NotNull JSONObject object, long startTime) {
        long endTime = Calendar.getInstance().getTime().getTime();
        long timeConsuming = endTime - startTime;
        object.put("timeConsuming", timeConsuming);
    }

}
