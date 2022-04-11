package pers.orchard.a617.transform;

import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.NotNull;
import pers.orchard.a617.bean.Version;

import java.util.Date;

public class Transform {
    public static @NotNull Version transform(@NotNull JSONObject jsonObject) {
        Version version = new Version();

        versionHelper(version, jsonObject, "device");
        versionHelper(version, jsonObject, "folder");
        versionHelper(version, jsonObject, "photo");
        versionHelper(version, jsonObject, "label");

        return version;
    }

    private static void versionHelper(@NotNull Version version, @NotNull JSONObject jsonObject, @NotNull String name) {
        JSONObject object = jsonObject.getJSONObject(name);
        if (object != null) {
            Integer version1 = jsonObject.getInteger("version");
            Long dateUpdated = jsonObject.getLong("dateUpdated");

            Date date = new Date();
            date.setTime(dateUpdated);

            version.setDeviceVersion(version1);
            version.setDeviceDateUpdated(date);
        }
    }
}
