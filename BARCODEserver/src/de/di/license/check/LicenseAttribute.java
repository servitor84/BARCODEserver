
package de.di.license.check;

/**
 *
 * @author A. Sopicki
 */
public class LicenseAttribute {
    
    private String attribute;
    
    private String value;
    
    public LicenseAttribute() {
        this(null,null);
    }
    
    public LicenseAttribute(String attr, String val) {
        attribute = attr;
        value = val;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
