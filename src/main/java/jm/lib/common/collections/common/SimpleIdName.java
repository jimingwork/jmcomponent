/**
 * Create at 2008-11-10 by jiming.liu
 * $Id$
 */

package jm.lib.common.collections.common;

import java.io.*;

import jm.lib.common.entity.IdNameable;

@Deprecated // using IdName
public class SimpleIdName<T extends Serializable> implements IdNameable<T>, Externalizable {

    private T id;
    private String name;


    public SimpleIdName() {
        super();
    }



    public SimpleIdName(T id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    /**
     * @return the id
     */
    public T getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(T id) {
        this.id = id;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }



    @SuppressWarnings("unchecked")
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        this.id = (T)in.readObject();
        this.name = in.readUTF();

    }



    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.id);
        out.writeUTF(this.name);
    }



    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }



    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SimpleIdName other = (SimpleIdName) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }


}
