
package de.di.barcodeserver.profiles.datasources;

import java.io.File;
import java.io.FileInputStream;
import org.apache.log4j.Logger;

/**
 * Data source backed by a properties file.
 * @author A. Sopicki
 */
public class PropertiesDataSource extends AbstractDataSource {

  private File propertiesFile;

  private String key;

  public PropertiesDataSource(File file, String key) {
    propertiesFile = file;
    this.key = key;
  }

  @Override
  protected String fetchValue() throws DataSourceException {
    if ( key.startsWith("@@") && key.length() > 2) {
      try {
        key = this.getIndex(key.substring(2));

        if ( key == null ) {
          throw new DataSourceException("Unable to fetch key for data source "+getClass().getName()
              +" from ELO using index "+key.substring(2));
        }
      } catch(java.rmi.RemoteException rex) {
        Logger.getLogger(getClass()).debug("An exception occured while fetching the key from "
          +"elo.", rex);

        throw new DataSourceException("Unable to fetch value from data source "+getClass().getName());
      }
    }

    FileInputStream inputStream = null;
    java.util.Properties props = new java.util.Properties();
    try {
      inputStream = new FileInputStream(propertiesFile);

      props.load(inputStream);

      return props.getProperty(key);
    } catch(java.io.IOException ioex) {
      Logger.getLogger(getClass()).debug("An exception occured while fetching the value "
          +"from the properties file "+propertiesFile, ioex);
      throw new DataSourceException("Unable to fetch value from data source "+getClass().getName());
    } finally {
      try {
        inputStream.close();
      } catch(Exception ex) {

      }
    }
  }
}
