
package de.di.license.check;

/**
 *
 * @author A. Sopicki
 */
public class Tools {
    public static String toHexString(byte[] bytes) {
        StringBuilder b = new StringBuilder();
        
        int up = 0;
        int low = 0;
        
        for(int i = 0; i < bytes.length; i++) {
            up = (bytes[i]>>4)&0xf;
            low = (bytes[i]&0x0f);
            
            if ( up > 9 ) {
                up -= 10;
                b.append((char)(up+97));
            } else {
                b.append((char)(up+48));
            }
             
            if ( low > 9 ) {
                low -= 10;
                b.append((char)(low+97));
            } else {
                b.append((char)(low+48));
            }
                
        }
        
        return b.toString();
    }
    
    public static byte[] toByteArray(String hex) {
        
        if ( hex.length() % 2 != 0 )
            throw new IllegalArgumentException("Argument is not a hex string");
        
        byte[] buf = new byte[hex.length()];
        byte b = 0;
        int j = 0;
        
        for(int i = 0; i < hex.length(); i = i + 2) {
            
            if ( hex.charAt(i) > 96 ) {
                b = (byte)((hex.charAt(i)-87<<4));
            } else {
                b = (byte)((hex.charAt(i)-48<<4));
            }
            
            if ( hex.charAt(i+1) > 96 ) {
                b = (byte)(b |(hex.charAt(i+1)-87));
            } else {
                b = (byte)(b |(hex.charAt(i+1)-48));
            }
            
            buf[j] = b;
            j = j + 1;
        }
        return java.util.Arrays.copyOf(buf, j);
    }
}
