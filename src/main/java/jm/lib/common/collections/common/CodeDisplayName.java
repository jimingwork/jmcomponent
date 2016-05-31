package jm.lib.common.collections.common;

import jm.lib.common.entity.Codeable;
import jm.lib.common.entity.DisplayNameable;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@Data
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class CodeDisplayName<C extends Serializable> implements DisplayNameable, Codeable<C>, Serializable {
    @XmlAttribute
    private C code;

    @XmlAttribute
    private String displayName;
    
    
    

    public CodeDisplayName() {
        super();
    }
    
    public CodeDisplayName(C code, String displayName) {
        super();
        this.code = code;
        this.displayName = displayName;
    }


}
