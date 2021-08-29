package de.di.barcodeserver.app;

/**
 * Main class to start the application from the command line
 *
 * @author A. Sopicki
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Application app = null;

      try {
        app = new Application();

        //start the application
        app.start();
      }  catch (StartUpException ex) {
      }

      if ( app != null ) {
        try {
          //wait till the end of the application
          app.join();

          //show error messages if start up fails
          java.util.List<String> status = app.getErrorStatus();

          if (status.size() > 0) {
            for (String msg : status) {
              System.err.println(msg);
            }
          }
        } catch (InterruptedException ex) {
        }
      }
    }
}
