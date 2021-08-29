package de.di.license.check;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author A. Sopicki
 */
public class BasicLicenseChecker implements LicenseChecker {
    
    private MessageDigest md= null;
    
    private byte[] bytes = null;
    
    public static final int ROUNDS = 3;
    
    public BasicLicenseChecker() throws NoSuchAlgorithmException {       
        md = MessageDigest.getInstance("SHA");
    }
    
  @Override
    public void check(LicenseKey key, int rounds) throws LicenseException {
                
        bytes = new byte[15];
        byte[] buf = key.getBytes1();
        
        for(int i = 0; i < buf.length; i++) {
            if ( i < 3 || i > 17 ) {
                buf[i] = buf[i];
            } else {
                bytes[i-3] = buf[i];
            }
        }
        
        md.update(bytes);
        
        for(LicenseAttribute attr: key.getAttributes()) {
            if ( attr.getAttribute().equalsIgnoreCase("bytes1") || 
                    attr.getAttribute().equalsIgnoreCase("bytes2")) {
                continue;
            }
            
            md.update(attr.getValue().getBytes());
        }
                
        buf = key.getBytes2();
        
        for(int i = 0; i < rounds; i++) {
            ByteConversions.subBytes(buf);
            ByteConversions.rotate(buf);
        }
        
        if ( !java.util.Arrays.equals(md.digest(), buf) ) {
            md.reset();
            throw new LicenseException("License key is not correct");
        }
        
        md.reset();
        
    }

  @Override
    public int getRounds() {
        return ROUNDS;
    }
}
