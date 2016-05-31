/**
 * JimingLiu created the file at 2006-11-30 03:28:14
 * $Id$
 */
package jm.lib.common.collections.common;

import java.io.*;

/**
 * @author JimingLiu
 *
 */
public class IdName<ID extends Serializable> implements Externalizable {
    private ID id;
    private String name;

    /**
     * @return the id
     */
    public ID getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(ID id) {
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



    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        this.id = (ID)in.readObject();
        this.name = in.readUTF();

    }



    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.id);
        out.writeUTF(this.name);
    }


    /**
     * Returns <code>true</code> if this <code>IdName</code> is the same as the o argument.
     *
     * @return <code>true</code> if this <code>IdName</code> is the same as the o argument.
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != getClass()) {
            return false;
        }
        @SuppressWarnings("rawtypes")
        IdName castedObj = (IdName) o;
        return ((this.id == castedObj.id) && (this.name == null
            ? castedObj.name == null
            : this.name.equals(castedObj.name)));
    }
    /**
     * Override hashCode.
     *
     * @return the Objects hashcode.
     */
    public int hashCode() {
        int hashCode = 95393;
        hashCode = 96667 * hashCode + id.hashCode();
        hashCode = 97387 * hashCode + (name == null ? 0 : name.hashCode());
        return hashCode;
    }
    /**
     * @generated by CodeSugar http://sourceforge.net/projects/codesugar */

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[IdName:");
        buffer.append(" id: ");
        buffer.append(id);
        buffer.append(" name: ");
        buffer.append(name);
        buffer.append("]");
        return buffer.toString();
    }

//    public String toJSON() {
//        StringBuilder sb = new StringBuilder();
//        this.toJSON(sb);
//        return sb.toString();
//    }
//
//    public void toJSON(StringBuilder sb) {
//        sb.append('{');
//        sb.append("\"id\": \"");
//        sb.append(this.getId());
//        sb.append("\",\"name\":\"");
//        sb.append(JsonUtil.encode(getName()));
//        sb.append("\"}");
//
//    }




}
