package pers.orchard.a617.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.orchard.a617.bean.Device;
import pers.orchard.a617.bean.photo.PhotoFolder;
import pers.orchard.a617.bean.photo.PhotoPhoto;
import pers.orchard.a617.bean.photo.PhotoTagPhoto;
import pers.orchard.a617.constant.RuleCode;
import pers.orchard.a617.constant.TypeCode;
import pers.orchard.a617.controller.JSONDataHelper;
import pers.orchard.a617.dao.MainDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncreaseService {
    private final MainDao dao;

    @Autowired
    public IncreaseService(MainDao dao) {
        this.dao = dao;
    }

    public JSONObject responseRecords(@NotNull JSONArray records) {
        JSONObject resultObj = new JSONObject();

        JSONArray resultCodeArr = new JSONArray();
        int resultErrorCount = 0;

        for (Object o : records) {
            JSONObject record = (JSONObject) o;
            boolean error = handleIncrease(record);

            JSONObject status = new JSONObject();
            if (!error) {
                JSONDataHelper.setResOK(status);
            } else {
                JSONDataHelper.setResDataIncorrect(status);
            }
            resultCodeArr.add(status);
        }

        resultObj.put("result", resultCodeArr);
        resultObj.put("resultErrorCount", resultErrorCount);
        return resultObj;
    }

    private boolean handleIncrease(@NotNull JSONObject record) {
        Integer typeCode = record.getInteger("typeCode");
        boolean error = typeCode == null;
        if (!error) {
            switch (typeCode) {
                case TypeCode.DEVICE -> {
                    error = new OperateCallbackDevice().handle(record);
                }
                case TypeCode.FOLDER -> {
                    error = new OperateCallbackFolder().handle(record);
                }
                case TypeCode.PHOTO -> {
                    error = new OperateCallbackPhoto().handle(record);
                }
                case TypeCode.LABEL -> {
                    error = new OperateCallbackLabel().handle(record);
                }
                default -> {
                    error = true;
                }
            }
        }
        return error;
    }

    private interface DoOperateCallback {
        boolean handle(@NotNull JSONObject record);

        void doInsert(int ruleCode, @NotNull JSONArray dataArr);

        void doUpdate(int ruleCode, @NotNull JSONArray dataArr);

        void doDelete(int ruleCode, @NotNull JSONArray dataArr);
    }

    private class OperateCallbackDevice implements DoOperateCallback {
        @Override
        public boolean handle(@NotNull JSONObject record) {
            Integer ruleCode = record.getInteger("ruleCode");
            JSONArray dataArr = record.getJSONArray("data");

            boolean error = ruleCode == null;
            error |= dataArr == null || dataArr.size() == 0;

            if (!error) {
                switch (ruleCode) {
                    case RuleCode.COMMON_INSERT -> doInsert(ruleCode, dataArr);
                    case RuleCode.COMMON_DELETE -> doDelete(ruleCode, dataArr);
                    default -> error = true;
                }
            }
            return error;
        }

        @Override
        public void doInsert(int ruleCode, @NotNull JSONArray dataArr) {
            List<Device> list = new ArrayList<>();
            for (Object o : dataArr) {
                JSONObject obj = (JSONObject) o;
                Device device = obj.toJavaObject(Device.class);
                list.add(device);
            }
            dao.insertSomeDevice(list);
        }

        @Override
        public void doUpdate(int ruleCode, @NotNull JSONArray dataArr) {
        }

        @Override
        public void doDelete(int ruleCode, @NotNull JSONArray dataArr) {
            List<Integer> IDs = new ArrayList<>();
            for (Object o : dataArr) {
                Integer ID = (Integer) o;
                IDs.add(ID);
            }
            dao.deleteSomeDevice(IDs);
        }
    }

    private class OperateCallbackFolder implements DoOperateCallback {
        @Override
        public boolean handle(@NotNull JSONObject record) {
            return false;
        }

        @Override
        public void doInsert(int ruleCode, @NotNull JSONArray dataArr) {
            List<PhotoFolder> list = new ArrayList<>();
            for (Object o : dataArr) {
                JSONObject obj = (JSONObject) o;
                PhotoFolder folder = obj.toJavaObject(PhotoFolder.class);
                list.add(folder);
            }
            dao.insertSomeFolder(list);
        }

        @Override
        public void doUpdate(int ruleCode, @NotNull JSONArray dataArr) {
            switch (ruleCode) {
                case RuleCode.UPDATE_FOLDER_MOVE -> {
                    List<Integer> IDs = new ArrayList<>();
                    for (Object o : dataArr) {
                        JSONObject obj = (JSONObject) o;
                        PhotoFolder folder = obj.toJavaObject(PhotoFolder.class);
                        Integer ID = folder.getID();
                        if (ID != null && ID != 0) {
                            IDs.add(ID);
                        }
                    }
                    dao.moveFolder(IDs);
                }
                case RuleCode.UPDATE_FOLDER_RENAME -> {
                    for (Object o : dataArr) {
                        JSONObject obj = (JSONObject) o;
                        PhotoFolder folder = obj.toJavaObject(PhotoFolder.class);
                        dao.updateFolder(folder);
                    }
                }
                case RuleCode.UPDATE_FOLDER_UPDATE_DESCRIPTION -> {
                    for (Object o : dataArr) {
                        JSONObject obj = (JSONObject) o;
                        PhotoFolder folder = obj.toJavaObject(PhotoFolder.class);
                        dao.updateFolder(folder);
                    }
                }
            }


        }

        @Override
        public void doDelete(int ruleCode, @NotNull JSONArray dataArr) {
            List<Integer> IDs = new ArrayList<>();
            for (Object o : dataArr) {
                Integer ID = (Integer) o;
                IDs.add(ID);
            }
            dao.deleteSomeFolder(IDs);
        }
    }

    private class OperateCallbackPhoto implements DoOperateCallback {
        @Override
        public boolean handle(@NotNull JSONObject record) {
            return false;
        }

        @Override
        public void doInsert(int ruleCode, @NotNull JSONArray dataArr) {
            List<PhotoPhoto> list = new ArrayList<>();
            for (Object o : dataArr) {
                JSONObject obj = (JSONObject) o;
                PhotoPhoto photo = obj.toJavaObject(PhotoPhoto.class);
                list.add(photo);
            }
            dao.insertSomePhoto(list);
        }

        @Override
        public void doUpdate(int ruleCode, @NotNull JSONArray dataArr) {
            for (Object o : dataArr) {
                JSONObject obj = (JSONObject) o;
                PhotoPhoto photo = obj.toJavaObject(PhotoPhoto.class);
                dao.updatePhoto(photo);
            }
        }

        @Override
        public void doDelete(int ruleCode, @NotNull JSONArray dataArr) {
            List<Integer> IDs = new ArrayList<>();
            for (Object o : dataArr) {
                Integer ID = (Integer) o;
                IDs.add(ID);
            }
            dao.deleteSomePhoto(IDs);
        }
    }

    private class OperateCallbackLabel implements DoOperateCallback {
        @Override
        public boolean handle(@NotNull JSONObject record) {
            return false;
        }

        @Override
        public void doInsert(int ruleCode, @NotNull JSONArray dataArr) {
            List<PhotoTagPhoto> list = new ArrayList<>();
            for (Object o : dataArr) {
                JSONObject obj = (JSONObject) o;
                PhotoTagPhoto label = obj.toJavaObject(PhotoTagPhoto.class);
                list.add(label);
            }
            dao.insertSomeLabel(list);
        }

        @Override
        public void doUpdate(int ruleCode, @NotNull JSONArray dataArr) {
            for (Object o : dataArr) {
                JSONObject obj = (JSONObject) o;
                PhotoTagPhoto label = obj.toJavaObject(PhotoTagPhoto.class);
                dao.updateLabel(label);
            }
        }

        @Override
        public void doDelete(int ruleCode, @NotNull JSONArray dataArr) {
            List<Integer> IDs = new ArrayList<>();
            for (Object o : dataArr) {
                Integer ID = (Integer) o;
                IDs.add(ID);
            }
            dao.deleteSomeLabel(IDs);
        }
    }
}
