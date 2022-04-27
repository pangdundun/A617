package pers.orchard.a617.service;

import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.orchard.a617.bean.Device;
import pers.orchard.a617.bean.photo.PhotoFolder;
import pers.orchard.a617.bean.photo.PhotoLabel;
import pers.orchard.a617.bean.photo.PhotoPhoto;
import pers.orchard.a617.bean.photo.PhotoWithLabelEntity;
import pers.orchard.a617.constant.OperateCode;
import pers.orchard.a617.constant.RuleCode;
import pers.orchard.a617.constant.TypeCode;
import pers.orchard.a617.controller.JSONDataHelper;
import pers.orchard.a617.dao.MainDao;

import java.util.*;

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
            case TypeCode.LABEL_WITH_PHOTO -> {
                handlePhotoWithLabel(operateCode, ruleCode, dataObj, resultObj);
            }
        }
    }

    private void handleDevice(int operateCode, int ruleCode, @NotNull JSONObject dataObj, @NotNull JSONObject resultObj) {
        switch (operateCode) {
            case OperateCode.SELECT -> {
            }
            case OperateCode.INSERT -> {
                if (ruleCode == RuleCode.INSERT_ONE) {
                    Device device = JSONHelper.getObject1(dataObj, Device.class);

                    Date now = new Date();
                    device.setDateRegistered(now);
                    device.setDateVisited(now);

                    dao.deviceInsertOne(device);

                    JSONObject dataObj2 = new JSONObject();
                    dataObj2.put("int1", device.getID());
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
                    PhotoFolder folder = JSONHelper.getObject1(dataObj, PhotoFolder.class);

                    int IDFolder = folder.getIDParent();
                    String nameDisplay = folder.getNameDisplay();

                    boolean error = dao.getFolderCountInSameFolderByName(IDFolder, nameDisplay) != 0;

                    error = false;
                    if (!error) {
                        Date now = new Date();
                        folder.setDateCreated(now);
                        folder.setDateUpdated(now);
                        folder.setCountUpdated(0);

                        System.out.println("folder::" + folder);
                        dao.folderInsertOne(folder);

                        JSONObject dataObj2 = new JSONObject();
                        dataObj2.put("int1", folder.getID());
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
                        int parentID = JSONHelper.getInt1(dataObj);
                        int[] IDs = JSONHelper.getIntArr(dataObj);

                        boolean error = dao.getFolderCountWhereID(parentID) != 1 || IDs.length == 0;
                        String errorInfo = "The destination directory does not exist or the ID data is empty.";

                        if (!error) {
                            for (int ID : IDs) {
                                if (ID == 0 || dao.getPhotoCountWhereID(ID) != 1) {
                                    error = false;
                                    errorInfo = "Photo does not exist.";
                                    break;
                                }
                            }
                        }

                        error = false;
                        if (!error) {
                            dao.folderMove(parentID, IDs);
                            JSONDataHelper.setResOK(resultObj);
                        } else {
                            JSONDataHelper.setResDataIncorrect(resultObj, errorInfo);
                        }
                    }
                    case RuleCode.UPDATE_RENAME -> {
                        int ID = JSONHelper.getInt1(dataObj);
                        String nameDisplay = JSONHelper.getString1(dataObj);

                        boolean error = ID == 0 || dao.getFolderCountWhereID(ID) != 1 || dao.getFolderCountInSameFolderByName(ID, nameDisplay) != 0;
                        String errorInfo = "The root directory cannot be operated, or the directory to be named does not exist, or a directory with the same name already exists under the current directory.";

                        error = false;
                        if (!error) {
                            dao.folderRename(ID, nameDisplay);
                            JSONDataHelper.setResOK(resultObj);
                        } else {
                            JSONDataHelper.setResDataIncorrect(resultObj, errorInfo);
                        }
                    }
                    case RuleCode.UPDATE_DESCRIPTION -> {
                        int ID = JSONHelper.getInt1(dataObj);
                        String description = JSONHelper.getString1(dataObj);

                        boolean error = dao.getFolderCountWhereID(ID) != 1;
                        String errorInfo = "The photo to be modified does not exist.";

                        error = false;
                        if (!error) {
                            if (description == null) {
                                description = "";
                            }
                            dao.folderUpdateDescription(ID, description);
                            JSONDataHelper.setResOK(resultObj);
                        } else {
                            JSONDataHelper.setResDataIncorrect(resultObj, errorInfo);
                        }
                    }
                }
            }
            case OperateCode.DELETE -> {
                if (ruleCode == RuleCode.DELETE_BY_IDS) {
                    int[] IDs = JSONHelper.getIntArr(dataObj);

                    boolean error = IDs.length == 0;
                    String errorInfo = "ID array cannot be empty.";

                    if (!error) {
                        for (int ID : IDs) {
                            if (dao.getFolderCountWhereID(ID) != 1) {
                                error = false;
                                errorInfo = "The directory(" + ID + ") to be deleted does not exist.";
                                break;
                            }
                        }
                    }

                    error = false;
                    if (!error) {
                        for (int id : IDs) {
                            dao.deleteOneFolder(id);
                        }
                        JSONDataHelper.setResOK(resultObj);
                    } else {
                        JSONDataHelper.setResDataIncorrect(resultObj, errorInfo);
                    }
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
                    Object[] objectArr = JSONHelper.getObjectArr(dataObj);

                    boolean error = objectArr.length == 0;
                    String errorInfo = "The directory to be deleted does not exist.";

                    if (!error) {
                        for (Object o : objectArr) {
                            JSONObject obj = (JSONObject) o;
                            PhotoPhoto photo = JSONHelper.castToPhoto(obj);

                            int folderID = photo.getIDFolder();
                            if (folderID == 0 || dao.getFolderCountWhereID(folderID) != 1 || photo.getIDRegisterDevice() < 0) {
                                error = false;
                                errorInfo = "The root directory is being operated on, or the directory does not exist, or the device ID is illegal.";
                                break;
                            }

                            Date now = new Date();
                            photo.setDateUpdated(now);
                            photo.setDateRegistered(now);
                            photo.setCountUpdated(0);

                            list.add(photo);
                        }
                    }

                    error = false;
                    if (!error) {
                        dao.photoInsertSome(list);

                        JSONObject dataObj2 = new JSONObject();
                        int[] IDs = new int[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            IDs[i] = list.get(i).getID();
                        }
                        dataObj2.put("intArr", IDs);
                        resultObj.put("data", dataObj2);

                        JSONDataHelper.setResOK(resultObj);
                    } else {
                        JSONDataHelper.setResDataIncorrect(resultObj, errorInfo);
                    }
                }
            }
            case OperateCode.UPDATE -> {
                switch (ruleCode) {
                    case RuleCode.UPDATE_MOVE -> {
                        int IDFolder = JSONHelper.getInt1(dataObj);
                        int[] IDs = JSONHelper.getIntArr(dataObj);

                        boolean error = dao.getFolderCountWhereID(IDFolder) != 1 || IDs.length == 0;
                        String errorInfo = "The directory does not exist or the ID array is empty.";

                        for (int ID : IDs) {
                            if (dao.getPhotoCountWhereID(ID) != 1) {
                                error = false;
                                errorInfo = "Photo does not exist.";
                                break;
                            }
                        }

                        error = false;
                        if (!error) {
                            dao.folderMove(IDFolder, IDs);
                            JSONDataHelper.setResOK(resultObj);
                        } else {
                            JSONDataHelper.setResDataIncorrect(resultObj, errorInfo);
                        }

                    }
                    case RuleCode.UPDATE_RENAME -> {
                        int ID = JSONHelper.getInt1(dataObj);
                        String nameDisplay = JSONHelper.getString1(dataObj);

                        boolean error = dao.getPhotoCountWhereID(ID) != 1 || nameDisplay == null || "".equals(nameDisplay);
                        String errorInfo = "The photo does not exist or the name is empty.";

                        error = false;
                        if (!error) {
                            dao.photoRename(ID, nameDisplay);
                            JSONDataHelper.setResOK(resultObj);
                        } else {
                            JSONDataHelper.setResDataIncorrect(resultObj, errorInfo);
                        }

                    }
                    case RuleCode.UPDATE_DESCRIPTION -> {
                        int ID = JSONHelper.getInt1(dataObj);
                        String description = JSONHelper.getString1(dataObj);

                        boolean error = dao.getPhotoCountWhereID(ID) != 1 || description == null;
                        String errorInfo = "Photo does not exist or description is empty.";

                        error = false;
                        if (!error) {
                            dao.photoUpdateDescription(ID, description);
                            JSONDataHelper.setResOK(resultObj);
                        } else {
                            JSONDataHelper.setResDataIncorrect(resultObj, errorInfo);
                        }
                    }
                    case RuleCode.UPDATE_LABELS -> {
                        int photoID = JSONHelper.getInt1(dataObj);
                        int[] labelIDs = JSONHelper.getIntArr(dataObj);

                        boolean error = dao.getPhotoCountWhereID(photoID) != 1;
                        String errorInfo = "Photo does not exist.";

                        if (!error) {
                            for (int labelID : labelIDs) {
                                if (dao.getLabelCountWhereID(labelID) != 1) {
                                    error = false;
                                    errorInfo = "Label does not exist.";
                                    break;
                                }
                            }
                        }

                        error = false;
                        if (!error) {
                            List<PhotoWithLabelEntity> list = new ArrayList<>();
                            for (int labelID : labelIDs) {
                                PhotoWithLabelEntity entity = new PhotoWithLabelEntity();
                                entity.setLabelID(labelID);
                                entity.setPhotoID(photoID);
                                entity.setAddedDate(new Date());
                                list.add(entity);
                            }

                            dao.deletePhotoWithLabelWherePhotoID(photoID);
                            System.out.println("DELETED FINISH");
                            if (list.size() != 0) {
                                dao.insertPhotoWithLabel(list);
                            }
                            JSONDataHelper.setResOK(resultObj);
                        } else {
                            JSONDataHelper.setResDataIncorrect(resultObj, errorInfo);
                        }
                    }
                    case RuleCode.UPDATE_MD5 -> {
                        int ID = JSONHelper.getInt1(dataObj);
                        String MD5 = JSONHelper.getString1(dataObj);

                        boolean error = dao.getPhotoCountWhereID(ID) != 1 || MD5 == null;
                        String errorInfo = "Photo does not exist or MD5 value is null.";

                        error = false;
                        if (!error) {
                            dao.photoUpdateMD5(ID, MD5);
                            JSONDataHelper.setResOK(resultObj);
                        } else {
                            JSONDataHelper.setResDataIncorrect(resultObj, errorInfo);
                        }
                    }
                }
            }
            case OperateCode.DELETE -> {
                switch (ruleCode) {
                    case RuleCode.DELETE_BY_IDS -> {
                        int[] intArr = JSONHelper.getIntArr(dataObj);

                        boolean error = intArr.length == 0;
                        String errorInfo = "The ID array is empty.";

                        if (!error) {
                            for (int ID : intArr) {
                                if (dao.getPhotoCountWhereID(ID) != 1) {
                                    error = false;
                                    errorInfo = "Photo does not exist.";
                                    break;
                                }
                            }
                        }

                        error = false;
                        if (!error) {
                            dao.deleteSomePhoto(intArr);
                            JSONDataHelper.setResOK(resultObj);
                        } else {
                            JSONDataHelper.setResDataIncorrect(resultObj, errorInfo);
                        }
                    }
                    case RuleCode.DELETE_BY_ID_FOLDERS -> {
                        int[] IDs = JSONHelper.getIntArr(dataObj);

                        boolean error = IDs.length == 0;
                        String errorInfo = "The ID array is empty.";

                        error = false;
                        if (!error) {
                            dao.deletePhotoWhereFolderIDs(IDs);
                            JSONDataHelper.setResOK(resultObj);
                        } else {
                            JSONDataHelper.setResDataIncorrect(resultObj, errorInfo);
                        }
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
                    String name = JSONHelper.getString1(dataObj);

                    boolean error = dao.getLabelCountByName(name) != 0;
                    String errorInfo = "A label with the same name exists.";

                    error = false;
                    if (!error) {
                        PhotoLabel label = new PhotoLabel();
                        label.setName(name);
                        Date now = new Date();
                        label.setDateCreated(now);
                        label.setDateUpdated(now);
                        label.setCountUpdated(0);

                        dao.labelInsertOne(label);

                        JSONObject dataObj2 = new JSONObject();
                        dataObj2.put("int1", label.getID());
                        resultObj.put("data", dataObj2);

                        JSONDataHelper.setResOK(resultObj);
                    } else {
                        JSONDataHelper.setResDataIncorrect(resultObj, errorInfo);
                    }
                }
            }
            case OperateCode.UPDATE -> {
                if (ruleCode == RuleCode.UPDATE_RENAME) {
                    int ID = JSONHelper.getInt1(dataObj);
                    String name = JSONHelper.getString1(dataObj);

                    boolean error = dao.getLabelCountWhereID(ID) != 1 || dao.getLabelCountByName(name) != 0;
                    String errorInfo = "The label does not exist or a label with the same name exists.";

                    error = false;
                    if (!error) {
                        PhotoLabel label = new PhotoLabel();
                        label.setID(ID);
                        label.setName(name);
                        Date now = new Date();
                        label.setDateUpdated(now);
                        label.setCountUpdated(label.getCountUpdated() + 1);

                        dao.labelRename(label);

                        JSONDataHelper.setResOK(resultObj);
                    } else {
                        JSONDataHelper.setResDataIncorrect(resultObj, errorInfo);
                    }
                }
            }
            case OperateCode.DELETE -> {
                if (ruleCode == RuleCode.DELETE_BY_IDS) {
                    int[] IDs = JSONHelper.getIntArr(dataObj);

                    boolean error = IDs.length == 0;
                    String errorInfo = "The ID array is empty.";

                    if (!error) {
                        for (int ID : IDs) {
                            if (dao.getLabelCountWhereID(ID) != 1) {
                                error = false;
                                errorInfo = "Label does not exist.";
                                break;
                            }
                        }
                    }

                    error = false;
                    if (!error) {
                        dao.deleteSomeLabel(IDs);
                        JSONDataHelper.setResOK(resultObj);
                    } else {
                        JSONDataHelper.setResDataIncorrect(resultObj, errorInfo);
                    }
                }
            }
        }
    }

    // Closed at 20220426-083352.
    private void handlePhotoWithLabel(int operateCode, int ruleCode, @NotNull JSONObject dataObj, @NotNull JSONObject resultObj) {
        switch (operateCode) {
            case OperateCode.SELECT -> {
            }
            case OperateCode.INSERT -> {
                if (ruleCode == RuleCode.INSERT_SOME) {
                    Object[] objectArr = JSONHelper.getObjectArr(dataObj);

                    boolean error = objectArr.length == 0;
                    String errorInfo = "The data array is empty.";

                    List<PhotoWithLabelEntity> list = new ArrayList<>();
                    if (!error) {
                        for (Object o : objectArr) {
                            JSONObject obj = (JSONObject) o;
                            PhotoWithLabelEntity entity = JSONHelper.castToPhotoWithLabel(obj);

                            int labelID = entity.getLabelID();
                            int photoID = entity.getPhotoID();
                            if (dao.getLabelCountWhereID(labelID) != 1 || dao.getPhotoCountWhereID(photoID) != 1) {
                                error = false;
                                errorInfo = "The tag does not exist, or the photo does not exist.";
                                break;
                            }

                            list.add(entity);
                        }
                    }

                    error = false;
                    if (!error) {
                        dao.insertPhotoWithLabel(list);
                        JSONDataHelper.setResOK(resultObj);
                    } else {
                        JSONDataHelper.setResDataIncorrect(resultObj, errorInfo);
                    }

                }
            }
            case OperateCode.UPDATE -> {
            }
            case OperateCode.DELETE -> {
                if (ruleCode == RuleCode.DELETE_BY_LABEL_IDS) {
                    int[] IDs = JSONHelper.getIntArr(dataObj);

                    boolean error = IDs.length == 0;
                    String errorInfo = "The ID array is empty.";

                    if (!error) {
                        for (int ID : IDs) {
                            if (dao.getLabelCountWhereID(ID) != 1) {
                                error = false;
                                errorInfo = "Label does not exist.";
                                break;
                            }
                        }
                    }

                    error = false;
                    if (!error) {
                        for (int ID : IDs) {
                            dao.deletePhotoWithLabelWhereLabelID(ID);
                        }
                        JSONDataHelper.setResOK(resultObj);
                    } else {
                        JSONDataHelper.setResDataIncorrect(resultObj, errorInfo);
                    }

                } else if (ruleCode == RuleCode.DELETE_BY_IDS) {
                    Object[] objectArr = JSONHelper.getObjectArr(dataObj);

                    boolean error = objectArr.length == 0;
                    String errorInfo = "The data array is empty.";

                    List<PhotoWithLabelEntity> list = new ArrayList<>();
                    if (!error) {
                        Set<Integer> labelIDs = new HashSet<>();
                        Set<Integer> photoIDs = new HashSet<>();

                        for (Object o : objectArr) {
                            JSONObject obj = (JSONObject) o;
                            PhotoWithLabelEntity entity = JSONHelper.castToPhotoWithLabel(obj);

                            labelIDs.add(entity.getLabelID());
                            photoIDs.add(entity.getPhotoID());

                            list.add(entity);
                        }

                        for (Integer ID : labelIDs) {
                            if (dao.getLabelCountWhereID(ID) != 1) {
                                error = false;
                                errorInfo = "Label does not exist.";
                                break;
                            }
                        }
                        for (Integer ID : photoIDs) {
                            if (dao.getPhotoCountWhereID(ID) != 1) {
                                error = false;
                                errorInfo = "Photo does not exist.";
                                break;
                            }
                        }
                    }

                    error = false;
                    if (!error) {
                        for (PhotoWithLabelEntity entity : list) {
                            dao.deletePhotoWithLabelWhereLabelIDAndPhotoID(entity);
                        }
                        JSONDataHelper.setResOK(resultObj);
                    } else {
                        JSONDataHelper.setResDataIncorrect(resultObj, errorInfo);
                    }
                }
            }
        }
    }
}
