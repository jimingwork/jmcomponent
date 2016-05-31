package jm.lib.general;

/**
 * Created by jiming.liu
 */
public enum IdCardType {
    ID_CARD(1, "身份证"),
    PASSPORT(2, "护照"),
    MILITARY_ID(3, "军官证"),
    HK_M_TW_PERMIT(4, "港澳通行证"),
    OTHER(21, "其他证件")
    ;

    private int type;
    private String displayName;

    private IdCardType(int type, String displayName) {
        this.type = type;
        this.displayName = displayName;
    }

    public int value() {
        return type;
    }

    public String getDisplayName() {return displayName;}

    public static IdCardType valueOf(int value) {
        switch(value) {
            case 0: return ID_CARD;
            case 1: return PASSPORT;
            case 2: return MILITARY_ID;
            case 7: return OTHER;
            case 8: return HK_M_TW_PERMIT;
        }
        throw  new IllegalArgumentException("[" + value + "] is not valid.");
    }
}
