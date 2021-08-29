
package de.di.license.check;

/**
 *
 * @author A. Sopicki
 */
public class LicenseException extends Exception {
    public LicenseException(String msg) {
        super(msg);
    }
    
    public LicenseException(Throwable cause) {
        super(cause);
    }
    
    public LicenseException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
