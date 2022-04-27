package pers.orchard.a617.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;
import pers.orchard.a617.bean.Device;
import pers.orchard.a617.bean.photo.PhotoFolder;
import pers.orchard.a617.bean.photo.PhotoLabel;
import pers.orchard.a617.bean.photo.PhotoPhoto;
import pers.orchard.a617.bean.photo.PhotoWithLabelEntity;

import java.util.Arrays;

public class JSONHelper {
    public static int getInt1(@NotNull JSONObject jsonObject) {
        return jsonObject.getIntValue("int1");
    }

    public static String getString1(@NotNull JSONObject jsonObject) {
        return jsonObject.getString("string1");
    }

    public static <T> T getObject1(@NotNull JSONObject jsonObject, Class<T> clazz) {
        return jsonObject.getObject("object1", clazz);
    }

    public static int @NotNull [] getIntArr(@NotNull JSONObject jsonObject) {
        JSONArray intJSONArr = jsonObject.getJSONArray("intArr");
        int[] intArr = new int[intJSONArr.size()];
        for (int i = 0; i < intJSONArr.size(); i++) {
            intArr[i] = intJSONArr.getIntValue(i);
        }
        return intArr;
    }

    public static String @NotNull [] getStringArr(@NotNull JSONObject jsonObject) {
        JSONArray stringJSONArr = jsonObject.getJSONArray("stringArr");
        String[] stringArr = new String[stringJSONArr.size()];
        for (int i = 0; i < stringJSONArr.size(); i++) {
            stringArr[i] = stringJSONArr.getString(i);
        }
        return stringArr;
    }

    public static Object @NotNull [] getObjectArr(@NotNull JSONObject jsonObject) {
        JSONArray stringJSONArr = jsonObject.getJSONArray("objectArr");
        Object[] objArr = new Object[stringJSONArr.size()];
        for (int i = 0; i < stringJSONArr.size(); i++) {
            objArr[i] = stringJSONArr.get(i);
        }
        return objArr;
    }

    public static @NotNull Device castToDevice(@NotNull JSONObject jsonObject) {
        // @version 20220418-065838

        Device device = new Device();
        device.setID(jsonObject.getInteger("ID"));
        device.setName(jsonObject.getString("name"));
        device.setDateRegistered(jsonObject.getDate("dateRegistered"));
        device.setDateVisited(jsonObject.getDate("dateVisited"));
        return device;
    }

    public static @NotNull PhotoFolder castToFolder(@NotNull JSONObject jsonObject) {
        // @version 20220418-065838

        PhotoFolder folder = new PhotoFolder();
        folder.setID(jsonObject.getInteger("ID"));
        folder.setIDParent(jsonObject.getIntValue("IDParent"));
        folder.setIDCreatedDevice(jsonObject.getIntValue("IDCreatedDevice"));
        folder.setIDCover(jsonObject.getInteger("IDCover"));
        folder.setNameStorage(jsonObject.getString("nameStorage"));
        folder.setNameDisplay(jsonObject.getString("nameDisplay"));
        folder.setDescription(jsonObject.getString("description"));
        folder.setDateCreated(jsonObject.getDate("dateCreated"));
        folder.setDateUpdated(jsonObject.getDate("dateUpdated"));
        folder.setCountUpdated(jsonObject.getIntValue("countUpdated"));
        return folder;
    }

    public static @NotNull PhotoPhoto castToPhoto(@NotNull JSONObject jsonObject) {
        // @version 20220418-065838

        PhotoPhoto photo = new PhotoPhoto();
        photo.setID(jsonObject.getInteger("ID"));
        photo.setIDFolder(jsonObject.getInteger("IDFolder"));
        photo.setIDStory(jsonObject.getInteger("IDStory"));
        photo.setIDsTag(getJSONInt(jsonObject.getJSONArray("IDsTag")));
        photo.setIDRegisterDevice(jsonObject.getInteger("IDRegisterDevice"));
        photo.setIDsStorageDevice(getJSONInt(jsonObject.getJSONArray("IDsStorageDevice")));
        photo.setNameDisplay(jsonObject.getString("nameDisplay"));
        photo.setNameStorage(jsonObject.getString("nameStorage"));
        photo.setNameRegister(jsonObject.getString("nameRegister"));
        photo.setNameFolder(jsonObject.getString("nameFolder"));
        photo.setNamesTag(getJSONString(jsonObject.getJSONArray("namesTag")));
        photo.setNamesStorageDevice(getJSONString(jsonObject.getJSONArray("namesStorageDevice")));
        photo.setFileSize(jsonObject.getInteger("fileSize"));
        photo.setWidth(jsonObject.getInteger("width"));
        photo.setHeight(jsonObject.getInteger("height"));
        photo.setMimeType(jsonObject.getString("mimeType"));
        photo.setLatitude(jsonObject.getString("latitude"));
        photo.setLongitude(jsonObject.getString("longitude"));
        photo.setOrientation(jsonObject.getInteger("orientation"));
        photo.setDateTaken(jsonObject.getDate("dateTaken"));
        photo.setDescription(jsonObject.getString("description"));
        photo.setMD5(jsonObject.getString("MD5"));
        photo.setPresenceLocalFull(jsonObject.getBoolean("presenceLocalFull"));
        photo.setPresenceLocalThumb(jsonObject.getBoolean("presenceLocalThumb"));
        photo.setPresenceCloudThumb(jsonObject.getBoolean("presenceCloudThumb"));
        photo.setPathLocalFull(jsonObject.getString("pathLocalFull"));
        photo.setPathLocalThumb(jsonObject.getString("pathLocalThumb"));
        photo.setDateRegistered(jsonObject.getDate("dateRegistered"));
        photo.setDateUpdated(jsonObject.getDate("dateUpdated"));
        photo.setCountUpdated(jsonObject.getInteger("countUpdated"));
        return photo;
    }

    public static @NotNull PhotoLabel castToLabel(@NotNull JSONObject jsonObject) {
        // @version 20220418-065838

        PhotoLabel label = new PhotoLabel();
        label.setID(jsonObject.getInteger("ID"));
        label.setName(jsonObject.getString("name"));
        label.setDateCreated(jsonObject.getDate("dateCreated"));
        label.setDateUpdated(jsonObject.getDate("dateUpdated"));
        label.setCountUpdated(jsonObject.getIntValue("countUpdated"));
        return label;
    }

    public static @NotNull PhotoWithLabelEntity castToPhotoWithLabel(@NotNull JSONObject jsonObject) {
        // @version 20220418-065838

        PhotoWithLabelEntity entity = new PhotoWithLabelEntity();
        entity.setLabelID(jsonObject.getInteger("labelID"));
        entity.setPhotoID(jsonObject.getInteger("photoID"));
        entity.setAddedDate(jsonObject.getDate("dateAdded"));
        return entity;
    }

    @Contract(pure = true)
    public static Integer @NotNull [] getJSONInteger(JSONArray jsonArray) {
        if (jsonArray == null) {
            return new Integer[0];
        }
        Integer[] intArr = new Integer[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            intArr[i] = jsonArray.getInteger(i);
        }
        return intArr;
    }

    @Contract(pure = true)
    public static int @NotNull [] getJSONInt(JSONArray jsonArray) {
        if (jsonArray == null) {
            return new int[0];
        }
        int[] intArr = new int[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            intArr[i] = jsonArray.getInteger(i);
        }
        return intArr;
    }

    @Contract(pure = true)
    public static String @NotNull [] getJSONString(JSONArray jsonArray) {
        if (jsonArray == null) {
            return new String[0];
        }
        String[] stringArr = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            stringArr[i] = jsonArray.getString(i);
        }
        return stringArr;
    }

    public static class Data {
        public int int1;
        public String string1;
        public Object data1;

        public int[] intArr;
        public String[] stringArr;
        public Object[] dataArr;

        @Override
        @NonNull
        public String toString() {
            return "Data{" +
                    "int1=" + int1 +
                    ", string1='" + string1 + '\'' +
                    ", data1=" + data1 +
                    ", intArr=" + Arrays.toString(intArr) +
                    ", stringArr=" + Arrays.toString(stringArr) +
                    ", dataArr=" + Arrays.toString(dataArr) +
                    '}';
        }
    }
}
