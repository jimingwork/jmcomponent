/**
 * Create at 2008-11-27 by jiming.liu
 * $Id$
 */

package jm.lib.common.collections.common;

public class StringKeyValue extends KeyValue<String, String> implements Comparable<StringKeyValue> {

    /**
     *
     */
    private static final long serialVersionUID = -6064659482207542988L;

    public StringKeyValue() {
        super();
    }

    public StringKeyValue(String key, String value) {
        super(key, value);
    }


    @Override
    public int compareTo(StringKeyValue that) {
        if (this.getKey().compareTo(that.getKey()) < 0) {
            return -1;
        } else if (this.getKey().compareTo(that.getKey()) > 0) {
            return 1;
        }

        if (this.getValue().compareTo(that.getValue()) < 0) {
            return -1;
        } else if (this.getValue().compareTo(that.getValue()) > 0) {
            return 1;
        }
        return 0;
    }
}
