
package de.di.license.check;

/**
 *
 * @author A. Sopicki
 */
public class LicenseExpiredException extends LicenseException {
    public LicenseExpiredException(String msg) {
        super(msg);
    }
    
    public LicenseExpiredException(Throwable cause) {
        super(cause);
    }
    
    public LicenseExpiredException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
