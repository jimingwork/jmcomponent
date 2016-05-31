package jm.lib.common.collections.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @author Jiming on 2014年6月14日 
 *
 * 
 */
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class StringValueLabel implements Serializable {
    @XmlAttribute
    private String value;
    @XmlAttribute
    private String label;
    
    
    
    
    
    
    
    public StringValueLabel() {
        super();
    }
    
    public StringValueLabel(String value, String label) {
        super();
        this.value = value;
        this.label = label;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    
    
    
    
    
}
