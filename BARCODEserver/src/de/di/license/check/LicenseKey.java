
package de.di.license.check;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Locale;

/**
 *
 * @author A. Sopicki
 */
public class LicenseKey {
    private java.util.LinkedList<LicenseAttribute> attributes = null;
    
    public LicenseKey() {
        this(null);
    }
    
    public LicenseKey(java.util.LinkedList<LicenseAttribute> list) {
        if ( list == null ) {
            attributes = new java.util.LinkedList<LicenseAttribute>();
        } else {
            attributes = list;
        }
    }
    
    public java.util.Date getExpirationDate() {
        String pattern = "EEE MMM dd HH:mm:ss z yyyy";
        DateFormat format = new SimpleDateFormat(pattern, Locale.ROOT);
        try {
            return format.parse(getAttribute("expiration-date").getValue());
        } catch (ParseException ex) {
            return null;
        }
    }
    
    public void setExpirationDate(java.util.Date date) {
        attributes.add(new LicenseAttribute("expiration-date", 
                String.format(java.util.Locale.ROOT, "%1$tc", date)));
    }
    
    public LicenseAttribute getAttribute(String name) {
        for(LicenseAttribute attr: attributes) {
            if ( attr.getAttribute().equals(name) ) 
                return attr;
        }
        
        return null;
    }
    
    public byte[] getBytes1() {
        return Tools.toByteArray(getAttribute("bytes1").getValue());
    }
        
    public byte[] getBytes2() {
        return Tools.toByteArray(getAttribute("bytes2").getValue());
    }
    
    public void setBytes1(byte[] bytes) {
        LicenseAttribute attr = getAttribute("bytes1");
        
        if ( attr == null ) {
            attr = new LicenseAttribute("bytes1", Tools.toHexString(bytes));
            attributes.add(attr);
        } else {
            attr.setValue(Tools.toHexString(bytes));
        }
    }
    
    public void setBytes2(byte[] bytes) {
        LicenseAttribute attr = getAttribute("bytes2");
        
        if ( attr == null ) {
            attr = new LicenseAttribute("bytes2", Tools.toHexString(bytes));
            attributes.add(attr);
        } else {
            attr.setValue(Tools.toHexString(bytes));
        }
    }
    
    public java.util.LinkedList<LicenseAttribute> getAttributes() {
        return attributes;
    }
    
    public void setAttributes(java.util.LinkedList<LicenseAttribute> list) {
        attributes = list;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        for(LicenseAttribute attr: attributes) {
            builder.append(attr.getAttribute()+"="+attr.getValue());
            builder.append("\r\n");
        }
        
        return builder.toString();
    }
    
    public static LicenseKey readFromFile(java.io.File file) throws IOException {
        java.io.InputStream in = new java.io.FileInputStream(file);
        
        return readFromFile(in);
    }
    
    public static LicenseKey readFromFile(java.io.InputStream stream) throws IOException {
        java.io.InputStreamReader reader = new java.io.InputStreamReader(stream);
        java.io.BufferedReader buffer = new java.io.BufferedReader(reader);
                
        String line = buffer.readLine();
        
        if ( line == null ) {
            throw new IOException("Empty file");
        }
        
        LinkedList<LicenseAttribute> list = new LinkedList<LicenseAttribute>();
        
        while ( line != null ) {
            int i = line.indexOf('=');
            if ( i > 0 && i < line.length() ) {
                String key = line.substring(0, i).trim();
                String value = line.substring(key.length()+1).trim();
            
                if ( value.length() > 0 && key.length() > 0 ) {
                    list.add(new LicenseAttribute(key, value));
                }
            }
            
            line = buffer.readLine();
        }
        
        buffer.close();
        reader.close();
        
        LicenseKey key = new LicenseKey(list);
        
        //basic key checks
        if ( key.getAttribute("bytes1") == null ) {
            throw new IOException("Key read error");
        }
        
        if ( key.getAttribute("bytes2") == null ) {
            throw new IOException("Key read error");
        }
        
        if ( key.getAttribute("expiration-date") == null ) {
            throw new IOException("Key read error");
        }
        
        return key;
    }
    
    public void writeToFile(File f) throws IOException {
        java.io.FileWriter writer = new java.io.FileWriter(f);
        
        writer.write(toString());
        writer.close();
    }
}
