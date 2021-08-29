package de.di.barcodeserver.profiles.datasources;

/**
 *
 * @author A. Sopicki
 */
public class DataSourceException extends Exception {

    /**
     * Creates a new instance of <code>DataSourceException</code> without detail message.
     */
    public DataSourceException() {
    }


    /**
     * Constructs an instance of <code>DataSourceException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DataSourceException(String msg) {
        super(msg);
    }
}
