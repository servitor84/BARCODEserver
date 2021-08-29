package de.di.license.check;

/**
 *
 * @author A. Sopicki
 */
public interface LicenseChecker {
    public void check(LicenseKey key, int rounds) throws LicenseException;
    
    public int getRounds();
}
