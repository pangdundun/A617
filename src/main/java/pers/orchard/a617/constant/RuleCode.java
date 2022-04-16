package pers.orchard.a617.constant;

/**
 * @version 20220416-191612
 */
public class RuleCode {
    public static final int COMMON_INSERT = 1;

    @Deprecated
    public static final int UPDATE = 2;
    public static final int COMMON_DELETE = 3;
    public static final int COMMON_OVERWRITE = 4;
    public static final int COMMON_EMPTY = 5;
    public static final int COMMON_EMPTY_AND_REINITIALIZE = 6;

    @Deprecated
    public static final int INSERT_DEVICE_REGISTER = 7;
    public static final int UPDATE_FOLDER_MOVE = 8;
    public static final int UPDATE_FOLDER_RENAME = 9;
    public static final int UPDATE_FOLDER_UPDATE_DESCRIPTION = 10;
    public static final int DELETE_FOLDER_DELETE = 11;
    public static final int UPDATE_PHOTO_MOVE = 12;
    public static final int UPDATE_PHOTO_UPDATE_LABELS = 13;
    public static final int UPDATE_PHOTO_RENAME = 14;
    public static final int UPDATE_PHOTO_UPDATE_DESCRIPTION = 15;
    public static final int UPDATE_PHOTO_UPDATE_MD5 = 16;
    public static final int UPDATE_PHOTO_DELETE_BY_ID = 17;
    public static final int UPDATE_PHOTO_DELETE_BY_ID_FOLDER = 18;
    public static final int UPDATE_LABEL_INSERT = 19;
    public static final int UPDATE_LABEL_UPDATE = 20;
    public static final int UPDATE_LABEL_DELETE = 21;
}
