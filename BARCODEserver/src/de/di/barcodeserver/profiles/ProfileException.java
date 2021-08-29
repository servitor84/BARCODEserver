package de.di.barcodeserver.profiles;

/**
 *
 * @author A. Sopicki
 */
public class ProfileException extends Exception {

    /**
     * Creates a new instance of <code>ProfileException</code> without detail message.
     */
    public ProfileException() {
    }


    /**
     * Constructs an instance of <code>ProfileException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ProfileException(String msg) {
        super(msg);
    }
}
