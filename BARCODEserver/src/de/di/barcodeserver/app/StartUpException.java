package de.di.barcodeserver.app;

/**
 * Exception for errors on application start up.
 *
 * @author Administrator
 */
public class StartUpException extends Exception {
  StartUpException() {
    super();
  }

  StartUpException(String msg) {
    super(msg);
  }

  StartUpException(String msg, Throwable t) {
    super(msg, t);
  }
}
