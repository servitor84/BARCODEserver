
package de.di.barcodeserver.profiles.datasources;

import de.di.barcodeserver.elo.ELOClient;
import de.elo.utils.net.RemoteException;
import org.apache.log4j.Logger;

/**
 *
 * @author A. Sopicki
 */
public class ELODataSource extends AbstractDataSource {

  private String index;

  private Logger logger;

  public ELODataSource(String index) {
    this.index = index;

    logger = Logger.getLogger(getClass());
    
    setNoCaching(true);
  }

  @Override
  protected String fetchValue() throws DataSourceException {
    try {
      ELOClient client = getEloClient();

      if ( index.startsWith("@@") && index.length() > 2) {
        try {
          index = this.getIndex(index.substring(2));

          if ( index == null ) {
            throw new DataSourceException("Unable to fetch key for data source "+getClass().getName()
                +" from ELO using index "+index.substring(2));
          }
        } catch(java.rmi.RemoteException rex) {
          Logger.getLogger(getClass()).debug("An exception occured while fetching the key from "
            +"elo.", rex);

          throw new DataSourceException("Unable to fetch value from data source "+getClass().getName());
        }
      }

      return client.getIndexValue(getObjId(), index);
    } catch(RemoteException rex) {
      logger.debug("An error occured while fetching the value from ELO", rex);
      throw new DataSourceException("Unable to fetch value from data source "+getClass().getName());
    }
  }
}
