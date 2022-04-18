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

    public static void setResServerDBError(@NotNull JSONObject object) {
        object.put("responseCode", ResponseCode.SERVER_DATABASE_ERROR);
        object.put("responseInfo", "Server database error.");
    }

    public static void setResDataIncorrect(@NotNull JSONObject object) {
        object.put("responseCode", ResponseCode.DATA_INCORRECT);
        object.put("responseInfo", "The incoming data is incorrect.");
    }

    public static void setResErrorContentWithTheSameName(@NotNull JSONObject object) {
        object.put("responseCode", ResponseCode.ERROR_CONTENT_WITH_THE_SAME_NAME);
        object.put("responseInfo", "Duplicate file name.");
    }

    public static void setTimeConsuming(@NotNull JSONObject object, long startTime) {
        long endTime = Calendar.getInstance().getTime().getTime();
        long timeConsuming = endTime - startTime;
        object.put("timeConsuming", timeConsuming);
    }

}
