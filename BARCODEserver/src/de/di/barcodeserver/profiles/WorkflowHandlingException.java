package de.di.barcodeserver.profiles;

/**
 *
 * @author A. Sopicki
 */
public class WorkflowHandlingException extends Exception {

    /**
     * Creates a new instance of <code>WorkflowHandlingException</code> without detail message.
     */
    public WorkflowHandlingException() {
    }


    /**
     * Constructs an instance of <code>WorkflowHandlingException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public WorkflowHandlingException(String msg) {
        super(msg);
    }
}
