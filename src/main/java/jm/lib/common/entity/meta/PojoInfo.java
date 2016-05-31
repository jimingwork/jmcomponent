/**
 * Create at 2008-12-31 by jiming.liu
 * $Id$
 */

package jm.lib.common.entity.meta;


import java.util.Map;

public interface PojoInfo<T> {


    public static final int POPULATE_OK = 0x1;
    public static final int POPULATE_ERROR_CONVERT = 0x10;
    public static final int POPULATE_ERROR_NO_FIELD = 0x20;

//    public static final int DYNA_FIELD_TYPE_SHORT = 0x1;
//    public static final int DYNA_FIELD_TYPE_INT = 0x2;
//    public static final int DYNA_FIELD_TYPE_LONG = 0x4;
//
//    public static final int DYNA_FIELD_TYPE_VARCHAR = 0x10;
//    public static final int DYNA_FIELD_TYPE_TEXT = 0x20;
//
//    public static final int DYNA_FIELD_TYPE_FLOAT = 0x100;
//    public static final int DYNA_FIELD_TYPE_DOUBLE = 0x200;
//
//    public static final int DYNA_FIELD_TYPE_DATE = 0x1000;
    /**
     * <p>
     * This id must be <b>unique</b> and <b>not</b> change in project life cycle.
     * It is for dynamic fields.
     * @return
     */
//    Integer getId();

    /**
     * @return the beanToDbMap
     */
    Map<String, String> getBeanToDbMap();

    /**
     * @return the dbToBeanMap
     */
    Map<String, String> getDbToBeanMap();

    /**
     * @return the clazz
     */
    Class<T> getClazz();

    /**
     * @return the beanName
     */
    String getBeanName();

    /**
     * get bean name in property format
     * @return
     */
    String getNameAsProperty();

    /**
     * @return the moduleName
     */
    String getModuleName();

    /**
     * @return the subTableName
     */
    String getSubTableName();

    /**
     * @return the tableName
     */
    String getTableName();

    /**
     * @return the messagePrefix
     */
    String getMessagePrefix();

//    String getRegionName(RequestContext context);


    T newBean();

    /**
     * create a new bean and set the value with m
     * @param m
     * @return
     */
    T newBean(Map<String, ?> m);

//    boolean isHasOwnerId();
//    String getOwnerId();
//
//
//    boolean isHasShortDF();
//    boolean isHasIntDF();
//    boolean isHasLongDF();
//    boolean isHasVarcharDF();
//    boolean isHasTextDF();
//    boolean isHasDateDF();
//    boolean isHasFloatDF();
//    boolean isHasDoubleDF();

    /**
     * Is value object. No physical table related with it
     * @return
     */
    boolean isValueObject();

    /**
     * Get the display value for the bean in case it is required to be displayed
     * @param v
     * @return
     */
    String getDisp(T v);

    /**
     * 是否有 unique key
     * @return
     */
    // boolean isHasUK();
    // String[] getUKRegionName();
    // String[] getUKValue();



//    public static class DynaField {
//        private short id;
//        private String name;
//        private int type;
//        /**
//         * @return the id
//         */
//        public short getId() {
//            return id;
//        }
//        /**
//         * @param id the id to set
//         */
//        public void setId(short id) {
//            this.id = id;
//        }
//        /**
//         * @return the name
//         */
//        public String getName() {
//            return name;
//        }
//        /**
//         * @param name the name to set
//         */
//        public void setName(String name) {
//            this.name = name;
//        }
//        /**
//         * @return the type
//         */
//        public int getType() {
//            return type;
//        }
//        /**
//         * @param type the type to set
//         */
//        public void setType(int type) {
//            this.type = type;
//        }
//    }



}