package jm.lib.util;

import org.apache.commons.lang3.time.FastDateFormat;

/**
 * Created by jiming.liu on 2015/11/11.
 * Methods related with format/parse
 */
public class DateFormatGroup {
    public static final FastDateFormat DATETIME = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    public static final FastDateFormat SHORT_DATETIME = FastDateFormat.getInstance("yyyyMMddHHmmss");
    public static final FastDateFormat DATE = FastDateFormat.getInstance("yyyy-MM-dd");
    public static final FastDateFormat SHORT_DATE = FastDateFormat.getInstance("yyyyMMdd");

}
