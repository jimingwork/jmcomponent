package jm.lib.general;

import jm.lib.common.entity.VarCodeable;

/**
 * Created by jiming.liu
 */
public enum Gender implements VarCodeable {
    MALE("Male", "男", "M")
    , FEMALE("Female", "女", "F")
    , UNKNOWN("Unknown", "未知", "U");

    private final String englishName;
    private final String chineseName;
    private final String varCode;

    Gender(String englishName, String value, String varCode) {
        this.englishName = englishName;
        this.chineseName = value;
        this.varCode = varCode;
    }

    public String getEnglishName() {
        return this.englishName;
    }

    public String getChineseName() {
        return this.chineseName;
    }

    public String getVarCode() {
        return this.varCode;
    }

    public static final boolean isValidCode(String code) {
        return "M".equals(code) || "F".equals(code) || "U".equals(code);
    }
}
