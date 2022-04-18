package pers.orchard.a617.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pers.orchard.a617.bean.Device;
import pers.orchard.a617.bean.photo.PhotoFolder;
import pers.orchard.a617.bean.photo.PhotoPhoto;
import pers.orchard.a617.bean.photo.PhotoLabel;
import pers.orchard.a617.constant.OperateCode;
import pers.orchard.a617.constant.RuleCode;
import pers.orchard.a617.constant.TypeCode;
import pers.orchard.a617.controller.JSONDataHelper;
import pers.orchard.a617.dao.MainDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class IncreaseService {
    private final MainDao dao;

    @Autowired
    public IncreaseService(MainDao dao) {
        this.dao = dao;
    }

    public void responseRecords(int typeCode, int operateCode, int ruleCode, @NotNull JSONObject dataObj, @NotNull JSONObject resultObj) {
        switch (typeCode) {
            case TypeCode.DEVICE -> {
                handleDevice(operateCode, ruleCode, dataObj, resultObj);
            }
            case TypeCode.FOLDER -> {
                handleFolder(operateCode, ruleCode, dataObj, resultObj);
            }
            case TypeCode.PHOTO -> {
                handlePhoto(operateCode, ruleCode, dataObj, resultObj);
            }
            case TypeCode.LABEL -> {
                handleLabel(operateCode, ruleCode, dataObj, resultObj);
            }
        }
    }

    private void handleDevice(int operateCode, int ruleCode, @NotNull JSONObject dataObj, @NotNull JSONObject resultObj) {
        switch (operateCode) {
            case OperateCode.SELECT -> {
            }
            case OperateCode.INSERT -> {
                if (ruleCode == RuleCode.INSERT_ONE) {
                    Device device = getObject1(dataObj, Device.class);

                    Date now = new Date();
                    device.setDateRegistered(now);
                    device.setDateVisited(now);

                    dao.deviceInsertOne(device);

                    JSONObject dataObj2 = new JSONObject();
                    dataObj2.put("object1", device);
                    resultObj.put("data", dataObj2);

                    JSONDataHelper.setResOK(resultObj);
                }
            }
            case OperateCode.UPDATE -> {
            }
            case OperateCode.DELETE -> {
            }
        }
    }

    private void handleFolder(int operateCode, int ruleCode, @NotNull JSONObject dataObj, @NotNull JSONObject resultObj) {
        switch (operateCode) {
            case OperateCode.SELECT -> {
            }
            case OperateCode.INSERT -> {
                if (ruleCode == RuleCode.INSERT_ONE) {
                    PhotoFolder folder = getObject1(dataObj, PhotoFolder.class);

                    int IDFolder = folder.getIDParent();
                    String nameDisplay = folder.getNameDisplay();

                    int count = dao.getFolderCountInSameFolderByName(IDFolder, nameDisplay);

                    if (count == 0) {
                        Date now = new Date();
                        folder.setDateCreated(now);
                        folder.setDateUpdated(now);
                        folder.setCountUpdated(0);

                        dao.folderInsertOne(folder);

                        JSONObject dataObj2 = new JSONObject();
                        dataObj2.put("object1", folder);
                        resultObj.put("data", dataObj2);

                        JSONDataHelper.setResOK(resultObj);
                    } else {
                        JSONDataHelper.setResErrorContentWithTheSameName(resultObj);
                    }
                }
            }
            case OperateCode.UPDATE -> {
                switch (ruleCode) {
                    case RuleCode.UPDATE_MOVE -> {
                        int parentID = getInt1(dataObj);
                        int[] IDs = getIntArr(dataObj);

                        dao.folderMove(parentID, IDs);
                        JSONDataHelper.setResOK(resultObj);
                    }
                    case RuleCode.UPDATE_RENAME -> {
                        int ID = getInt1(dataObj);
                        String nameDisplay = getString1(dataObj);

                        dao.folderRename(ID, nameDisplay);
                        JSONDataHelper.setResOK(resultObj);
                    }
                    case RuleCode.UPDATE_DESCRIPTION -> {
                        int ID = getInt1(dataObj);
                        String description = getString1(dataObj);

                        dao.folderUpdateDescription(ID, description);
                        JSONDataHelper.setResOK(resultObj);
                    }
                }
            }
            case OperateCode.DELETE -> {
                if (ruleCode == RuleCode.DELETE_BY_IDS) {
                    int[] IDs = getIntArr(dataObj);

                    for (int id : IDs) {
                        dao.deleteOneFolder(id);
                    }
                    JSONDataHelper.setResOK(resultObj);
                }
            }
        }
    }

    private void handlePhoto(int operateCode, int ruleCode, @NotNull JSONObject dataObj, @NotNull JSONObject resultObj) {
        switch (operateCode) {
            case OperateCode.SELECT -> {
            }
            case OperateCode.INSERT -> {
                if (ruleCode == RuleCode.INSERT_SOME) {
                    List<PhotoPhoto> list = new ArrayList<>();
                    Object[] objectArr = getObjectArr(dataObj);

                    for (Object o : objectArr) {
                        JSONObject obj = (JSONObject) o;
                        PhotoPhoto photo = castToPhoto(obj);

                        Date now = new Date();
                        photo.setDateUpdated(now);
                        photo.setDateRegistered(now);
                        photo.setCountUpdated(0);

                        list.add(photo);
                    }

                    dao.photoInsertSome(list);

                    JSONObject dataObj2 = new JSONObject();
                    dataObj2.put("objectArr", list);
                    resultObj.put("data", dataObj2);

                    JSONDataHelper.setResOK(resultObj);
                }
            }
            case OperateCode.UPDATE -> {
                switch (ruleCode) {
                    case RuleCode.UPDATE_MOVE -> {
                        int IDFolder = getInt1(dataObj);
                        int[] IDs = getIntArr(dataObj);

                        dao.folderMove(IDFolder, IDs);
                        JSONDataHelper.setResOK(resultObj);
                    }
                    case RuleCode.UPDATE_RENAME -> {
                        int ID = getInt1(dataObj);
                        String nameDisplay = getString1(dataObj);

                        dao.photoRename(ID, nameDisplay);
                        JSONDataHelper.setResOK(resultObj);
                    }
                    case RuleCode.UPDATE_DESCRIPTION -> {
                        int ID = getInt1(dataObj);
                        String description = getString1(dataObj);

                        dao.photoUpdateDescription(ID, description);
                        JSONDataHelper.setResOK(resultObj);
                    }
                    case RuleCode.UPDATE_LABELS -> {
                        int ID = getInt1(dataObj);
                        int[] IDs = getIntArr(dataObj);
                        String[] names = getStringArr(dataObj);

                        String str1 = JSONArray.toJSONString(IDs);
                        String str2 = JSONArray.toJSONString(names);

                        dao.photoUpdateLabels(ID, str1, str2);
                        JSONDataHelper.setResOK(resultObj);
                    }
                    case RuleCode.UPDATE_MD5 -> {
                        int ID = getInt1(dataObj);
                        String MD5 = getString1(dataObj);

                        dao.photoUpdateMD5(ID, MD5);
                        JSONDataHelper.setResOK(resultObj);
                    }
                }
            }
            case OperateCode.DELETE -> {
                switch (ruleCode) {
                    case RuleCode.DELETE_BY_IDS -> {
                        int[] intArr = getIntArr(dataObj);

                        dao.deleteSomePhoto(intArr);
                        JSONDataHelper.setResOK(resultObj);
                    }
                    case RuleCode.DELETE_BY_ID_FOLDERS -> {
                        int[] IDs = getIntArr(dataObj);

                        dao.deleteSomePhoto(IDs);
                        JSONDataHelper.setResOK(resultObj);
                    }
                }
            }
        }
    }

    private void handleLabel(int operateCode, int ruleCode, @NotNull JSONObject dataObj, @NotNull JSONObject resultObj) {
        switch (operateCode) {
            case OperateCode.SELECT -> {
            }
            case OperateCode.INSERT -> {
                if (ruleCode == RuleCode.INSERT_ONE) {
                    String name = getString1(dataObj);
                    int count = dao.getLabelCountByName(name);

                    if (count == 0) {
                        PhotoLabel label = new PhotoLabel();
                        label.setName(name);
                        Date now = new Date();
                        label.setDateCreated(now);
                        label.setDateUpdated(now);
                        label.setCountUpdated(0);

                        dao.labelInsertOne(label);

                        JSONObject dataObj2 = new JSONObject();
                        dataObj2.put("object1", label);
                        resultObj.put("data", dataObj2);

                        JSONDataHelper.setResOK(resultObj);
                    } else {
                        JSONDataHelper.setResErrorContentWithTheSameName(resultObj);
                    }
                }
            }
            case OperateCode.UPDATE -> {
                if (ruleCode == RuleCode.UPDATE_RENAME) {
                    PhotoLabel label = getObject1(dataObj, PhotoLabel.class);

                    String name = label.getName();
                    int count = dao.getLabelCountByName(name);

                    if (count == 0) {
                        Date now = new Date();
                        label.setDateUpdated(now);
                        label.setCountUpdated(label.getCountUpdated() + 1);

                        dao.labelRename(label);

                        JSONObject dataObj2 = new JSONObject();
                        dataObj2.put("object1", label);
                        resultObj.put("data", dataObj2);

                        JSONDataHelper.setResOK(resultObj);
                    } else {
                        JSONDataHelper.setResErrorContentWithTheSameName(resultObj);
                    }
                }
            }
            case OperateCode.DELETE -> {
                if (ruleCode == RuleCode.DELETE_BY_ID) {
                    int ID = getInt1(dataObj);

                    dao.deleteSomeLabel(new int[]{ID});
                    JSONDataHelper.setResOK(resultObj);
                }
            }
        }
    }

    private int getInt1(@NotNull JSONObject jsonObject) {
        return jsonObject.getIntValue("int1");
    }

    private String getString1(@NotNull JSONObject jsonObject) {
        return jsonObject.getString("string1");
    }

    private <T> T getObject1(@NotNull JSONObject jsonObject, Class<T> clazz) {
        return jsonObject.getObject("object1", clazz);
    }

    private int @NotNull [] getIntArr(@NotNull JSONObject jsonObject) {
        JSONArray intJSONArr = jsonObject.getJSONArray("intArr");
        int[] intArr = new int[intJSONArr.size()];
        for (int i = 0; i < intJSONArr.size(); i++) {
            intArr[i] = intJSONArr.getIntValue(i);
        }
        return intArr;
    }

    private String @NotNull [] getStringArr(@NotNull JSONObject jsonObject) {
        JSONArray stringJSONArr = jsonObject.getJSONArray("stringArr");
        String[] stringArr = new String[stringJSONArr.size()];
        for (int i = 0; i < stringJSONArr.size(); i++) {
            stringArr[i] = stringJSONArr.getString(i);
        }
        return stringArr;
    }

    private Object @NotNull [] getObjectArr(@NotNull JSONObject jsonObject) {
        JSONArray stringJSONArr = jsonObject.getJSONArray("objectArr");
        Object[] objArr = new Object[stringJSONArr.size()];
        for (int i = 0; i < stringJSONArr.size(); i++) {
            objArr[i] = stringJSONArr.get(i);
        }
        return objArr;
    }

    private @NotNull PhotoPhoto castToPhoto(@NotNull JSONObject jsonObject) {
        // @version 20220418-065838

        PhotoPhoto photo = new PhotoPhoto();
        photo.setID(jsonObject.getInteger("ID"));
        photo.setIDFolder(jsonObject.getInteger("IDFolder"));
        photo.setIDStory(jsonObject.getInteger("IDStory"));
        photo.setIDsTag(getJSONInteger(jsonObject.getJSONArray("IDsTag")));
        photo.setIDRegisterDevice(jsonObject.getInteger("IDRegisterDevice"));
        photo.setIDsStorageDevice(getJSONInteger(jsonObject.getJSONArray("IDsStorageDevice")));
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

    @Contract(pure = true)
    private Integer @NotNull [] getJSONInteger(JSONArray jsonArray) {
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
    private String @NotNull [] getJSONString(JSONArray jsonArray) {
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
