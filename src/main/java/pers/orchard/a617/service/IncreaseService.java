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
                    error = handleDevice(record, new DeviceOperateCallback());
                }
                case TypeCode.FOLDER -> {
                    error = handleDevice(record, new FolderOperateCallback());
                }
                case TypeCode.PHOTO -> {
                    error = handleDevice(record, new PhotoOperateCallback());
                }
                case TypeCode.LABEL -> {
                    error = handleDevice(record, new LabelOperateCallback());
                }
                default -> {
                }
            }
        }
        return error;
    }

    private boolean handleDevice(@NotNull JSONObject record, DoOperateCallback callback) {
        Integer ruleCode = record.getInteger("ruleCode");
        JSONArray dataArr = record.getJSONArray("data");

        boolean error = ruleCode == null;
        error |= dataArr == null || dataArr.size() == 0;

        if (!error) {
            switch (ruleCode) {
                case RuleCode.INSERT -> {
                    callback.doInsert(dataArr);
                }
                case RuleCode.UPDATE -> {
                    callback.doUpdate(dataArr);
                }
                case RuleCode.DELETE -> {
                    callback.doDelete(dataArr);
                }
            }
        }
        return error;
    }

    private interface DoOperateCallback {
        void doInsert(JSONArray dataArr);

        void doUpdate(JSONArray dataArr);

        void doDelete(JSONArray dataArr);
    }

    private class DeviceOperateCallback implements DoOperateCallback {
        @Override
        public void doInsert(@NotNull JSONArray dataArr) {
            List<Device> list = new ArrayList<>();
            for (Object o : dataArr) {
                JSONObject obj = (JSONObject) o;
                Device device = obj.toJavaObject(Device.class);
                list.add(device);
            }
            dao.insertSomeDevice(list);
        }

        @Override
        public void doUpdate(@NotNull JSONArray dataArr) {
            for (Object o : dataArr) {
                JSONObject obj = (JSONObject) o;
                Device device = obj.toJavaObject(Device.class);
                dao.updateDevice(device);
            }
        }

        @Override
        public void doDelete(@NotNull JSONArray dataArr) {
            List<Integer> IDs = new ArrayList<>();
            for (Object o : dataArr) {
                Integer ID = (Integer) o;
                IDs.add(ID);
            }
            dao.deleteSomeDevice(IDs);
        }
    }

    private class FolderOperateCallback implements DoOperateCallback {
        @Override
        public void doInsert(@NotNull JSONArray dataArr) {
            List<PhotoFolder> list = new ArrayList<>();
            for (Object o : dataArr) {
                JSONObject obj = (JSONObject) o;
                PhotoFolder folder = obj.toJavaObject(PhotoFolder.class);
                list.add(folder);
            }
            dao.insertSomeFolder(list);
        }

        @Override
        public void doUpdate(@NotNull JSONArray dataArr) {
            for (Object o : dataArr) {
                JSONObject obj = (JSONObject) o;
                PhotoFolder folder = obj.toJavaObject(PhotoFolder.class);
                dao.updateFolder(folder);
            }
        }

        @Override
        public void doDelete(@NotNull JSONArray dataArr) {
            List<Integer> IDs = new ArrayList<>();
            for (Object o : dataArr) {
                Integer ID = (Integer) o;
                IDs.add(ID);
            }
            dao.deleteSomeFolder(IDs);
        }
    }

    private class PhotoOperateCallback implements DoOperateCallback {
        @Override
        public void doInsert(@NotNull JSONArray dataArr) {
            List<PhotoPhoto> list = new ArrayList<>();
            for (Object o : dataArr) {
                JSONObject obj = (JSONObject) o;
                PhotoPhoto photo = obj.toJavaObject(PhotoPhoto.class);
                list.add(photo);
            }
            dao.insertSomePhoto(list);
        }

        @Override
        public void doUpdate(@NotNull JSONArray dataArr) {
            for (Object o : dataArr) {
                JSONObject obj = (JSONObject) o;
                PhotoPhoto photo = obj.toJavaObject(PhotoPhoto.class);
                dao.updatePhoto(photo);
            }
        }

        @Override
        public void doDelete(@NotNull JSONArray dataArr) {
            List<Integer> IDs = new ArrayList<>();
            for (Object o : dataArr) {
                Integer ID = (Integer) o;
                IDs.add(ID);
            }
            dao.deleteSomePhoto(IDs);
        }
    }

    private class LabelOperateCallback implements DoOperateCallback {
        @Override
        public void doInsert(@NotNull JSONArray dataArr) {
            List<PhotoTagPhoto> list = new ArrayList<>();
            for (Object o : dataArr) {
                JSONObject obj = (JSONObject) o;
                PhotoTagPhoto label = obj.toJavaObject(PhotoTagPhoto.class);
                list.add(label);
            }
            dao.insertSomeLabel(list);
        }

        @Override
        public void doUpdate(@NotNull JSONArray dataArr) {
            for (Object o : dataArr) {
                JSONObject obj = (JSONObject) o;
                PhotoTagPhoto label = obj.toJavaObject(PhotoTagPhoto.class);
                dao.updateLabel(label);
            }
        }

        @Override
        public void doDelete(@NotNull JSONArray dataArr) {
            List<Integer> IDs = new ArrayList<>();
            for (Object o : dataArr) {
                Integer ID = (Integer) o;
                IDs.add(ID);
            }
            dao.deleteSomeLabel(IDs);
        }
    }
}
