
package de.di.barcodeserver.profiles.datasources;

import de.di.dokinform.app.IniFileReader;
import java.io.File;
import org.apache.log4j.Logger;

/**
 *
 * @author A. Sopicki
 */
public class IniFileDataSource extends AbstractDataSource {
  private File iniFile;

  private String key;

  private String section;

  public IniFileDataSource(File file, String section, String key) {
    iniFile = file;
    this.key = key;
    this.section = section;
  }

  @Override
  protected String fetchValue() throws DataSourceException {
    String iniKey = key;

    if ( key.startsWith("@@") && key.length() > 2) {
      try {
         iniKey = this.getIndex(key.substring(2));

        if ( iniKey == null ) {
          throw new DataSourceException("Unable to fetch key for data source "+getClass().getName()
              +" from ELO using index field "+key.substring(2));
        }
      } catch(java.rmi.RemoteException rex) {
        Logger.getLogger(getClass()).debug("An exception occured while fetching the key from "
          +"elo.", rex);

        throw new DataSourceException("Unable to fetch value from data source "+getClass().getName());
      }
    }

    IniFileReader reader = new IniFileReader(iniFile);
    try {

      reader.parse();

      return reader.getValue(section, iniKey);
    } catch(java.io.IOException ioex) {
      Logger.getLogger(getClass()).debug("An exception occured while fetching the value "
          +"from the ini file "+iniFile, ioex);
      throw new DataSourceException("Unable to fetch value from data source "+getClass().getName());
    }
  }
}
