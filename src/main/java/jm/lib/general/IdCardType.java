package jm.lib.general;

/**
 * Created by jiming.liu on 2015/11/10.
 */
public enum IdCardType {
    ID_CARD(0, "身份证"),
    PASSPORT(1, "护照"),
    MILITARY_ID(2, "军官证"),
    HVPS(3, "回乡证"),
    MTPS(4, "台胞证"),
    INT_SEAMAN(5, "国际海员证"),
    OTHER(7, "其他证件"),
    HK_M_TW_PERMIT(8, "港澳通行证"),
    TW_ENTRY(9, "赴台证"),
    TW_PERMIT(13, "台湾通行证");

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
            case 3: return HVPS;
            case 4: return MTPS;
            case 5: return INT_SEAMAN;
            case 7: return OTHER;
            case 8: return HK_M_TW_PERMIT;
            case 9: return TW_ENTRY;
            case 13: return TW_PERMIT;
        }
        throw  new IllegalArgumentException("[" + value + "] is not valid.");
    }
}
