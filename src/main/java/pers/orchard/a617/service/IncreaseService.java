package pers.orchard.a617.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pers.orchard.a617.bean.Device;
import pers.orchard.a617.bean.photo.PhotoFolder;
import pers.orchard.a617.bean.photo.PhotoPhoto;
import pers.orchard.a617.bean.photo.PhotoTagPhoto;
import pers.orchard.a617.constant.OperateCode;
import pers.orchard.a617.constant.RuleCode;
import pers.orchard.a617.constant.TypeCode;
import pers.orchard.a617.controller.JSONDataHelper;
import pers.orchard.a617.dao.MainDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class IncreaseService {
    private final MainDao dao;

    @Autowired
    public IncreaseService(MainDao dao) {
        this.dao = dao;
    }

    public JSONObject responseRecords(int typeCode, int operateCode, int ruleCode, @NotNull Data data) {
        switch (typeCode) {
            case TypeCode.DEVICE -> {
                return handleDevice(operateCode, ruleCode, data);
            }
            case TypeCode.FOLDER -> {
                return handleFolder(operateCode, ruleCode, data);
            }
            case TypeCode.PHOTO -> {
                return handlePhoto(operateCode, ruleCode, data);
            }
            case TypeCode.LABEL -> {
                return handleLabel(operateCode, ruleCode, data);
            }
        }
        return null;
    }

    private @NotNull JSONObject handleDevice(int operateCode, int ruleCode, @NotNull Data data) {
        JSONObject resultObj = new JSONObject();

        switch (operateCode) {
            case OperateCode.SELECT -> {
            }
            case OperateCode.INSERT -> {
                if (ruleCode == RuleCode.INSERT_ONE) {
                    JSONObject obj = (JSONObject) data.data1;
                    Device device = obj.toJavaObject(Device.class);

                    dao.deviceInsertOne(device);

                    Integer ID = device.getID();
                    resultObj.put("data_int1", ID);
                }
            }
            case OperateCode.UPDATE -> {
            }
            case OperateCode.DELETE -> {
            }
        }
        return resultObj;
    }

    private @NotNull JSONObject handleFolder(int operateCode, int ruleCode, @NotNull Data data) {
        JSONObject resultObj = new JSONObject();

        switch (operateCode) {
            case OperateCode.SELECT -> {
            }
            case OperateCode.INSERT -> {
                if (ruleCode == RuleCode.INSERT_ONE) {
                    JSONObject obj = (JSONObject) data.data1;
                    PhotoFolder folder = obj.toJavaObject(PhotoFolder.class);

                    dao.folderInsertOne(folder);

                    Integer ID = folder.getID();
                    resultObj.put("data_int1", ID);
                }
            }
            case OperateCode.UPDATE -> {
                switch (ruleCode) {
                    case RuleCode.UPDATE_MOVE -> {
                        int parentID = data.int1;
                        int[] IDs = data.intArr;

                        dao.folderMove(parentID, IDs);
                    }
                    case RuleCode.UPDATE_RENAME -> {
                        int ID = data.int1;
                        String nameDisplay = data.string1;

                        dao.folderRename(ID, nameDisplay);
                    }
                    case RuleCode.UPDATE_DESCRIPTION -> {
                        int ID = data.int1;
                        String description = data.string1;

                        dao.folderUpdateDescription(ID, description);
                    }
                }
            }
            case OperateCode.DELETE -> {
                if (ruleCode == RuleCode.DELETE_BY_IDS) {
                    int[] IDs = data.intArr;

                    dao.deleteSomeFolder(IDs);
                }
            }
        }
        return resultObj;
    }

    private @NotNull JSONObject handlePhoto(int operateCode, int ruleCode, @NotNull Data data) {
        JSONObject resultObj = new JSONObject();

        switch (operateCode) {
            case OperateCode.SELECT -> {
            }
            case OperateCode.INSERT -> {
                if (ruleCode == RuleCode.INSERT_SOME) {
                    List<PhotoPhoto> list = new ArrayList<>();
                    for (Object o : data.dataArr) {
                        PhotoPhoto photo = (PhotoPhoto) o;
                        list.add(photo);
                    }
                    dao.photoInsertSome(list);
                }
            }
            case OperateCode.UPDATE -> {
                switch (ruleCode) {
                    case RuleCode.UPDATE_MOVE -> {
                        int IDFolder = data.int1;
                        int[] IDs = data.intArr;

                        dao.folderMove(IDFolder, IDs);
                    }
                    case RuleCode.UPDATE_RENAME -> {
                        int ID = data.int1;
                        String nameDisplay = data.string1;

                        dao.photoRename(ID, nameDisplay);
                    }
                    case RuleCode.UPDATE_DESCRIPTION -> {
                        int ID = data.int1;
                        String description = data.string1;

                        dao.photoUpdateDescription(ID, description);
                    }
                    case RuleCode.UPDATE_LABELS -> {
                        int ID = data.int1;
                        int[] IDs = data.intArr;
                        String[] names = data.stringArr;

                        String str1 = JSONArray.toJSONString(IDs);
                        String str2 = JSONArray.toJSONString(names);

                        dao.photoUpdateLabels(ID, str1, str2);
                    }
                    case RuleCode.UPDATE_MD5 -> {
                        int ID = data.int1;
                        String MD5 = data.string1;

                        dao.photoUpdateMD5(ID, MD5);
                    }
                }
            }
            case OperateCode.DELETE -> {
                switch (ruleCode) {
                    case RuleCode.DELETE_BY_ID -> {
                        int ID = data.int1;

                        dao.deleteSomePhoto(new int[]{ID});
                    }
                    case RuleCode.DELETE_BY_ID_FOLDER -> {
                        int[] IDs = data.intArr;

                        dao.deleteSomePhoto(IDs);
                    }
                }
            }
        }
        return resultObj;
    }

    private @NotNull JSONObject handleLabel(int operateCode, int ruleCode, @NotNull Data data) {
        JSONObject resultObj = new JSONObject();

        switch (operateCode) {
            case OperateCode.SELECT -> {
            }
            case OperateCode.INSERT -> {
                if (ruleCode == RuleCode.INSERT_ONE) {
                    List<PhotoTagPhoto> list = new ArrayList<>();
                    for (Object o : data.dataArr) {
                        PhotoTagPhoto label = (PhotoTagPhoto) o;
                        list.add(label);
                    }
                    dao.labelInsertSome(list);
                }
            }
            case OperateCode.UPDATE -> {
                if (ruleCode == RuleCode.UPDATE_RENAME) {
                    int ID = data.int1;
                    String name = data.string1;

                    dao.labelRename(ID, name);
                }
            }
            case OperateCode.DELETE -> {
                if (ruleCode == RuleCode.DELETE_BY_ID) {
                    int ID = data.int1;

                    dao.deleteSomeLabel(new int[]{ID});
                }
            }
        }
        return resultObj;
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
