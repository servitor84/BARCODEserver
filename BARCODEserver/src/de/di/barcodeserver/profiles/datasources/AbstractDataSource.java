
package de.di.barcodeserver.profiles.datasources;

import de.di.barcodeserver.elo.ELOClient;
import java.rmi.RemoteException;

/**
 *
 * @author A. Sopicki
 */
public abstract class AbstractDataSource implements DataSource {

  private String value = null;

  private boolean noCaching = false;

  private ELOClient eloClient = null;

  private String objId = null;

  /**
   * @return the value
   */
  public String getValue() throws DataSourceException {
    if ( value == null || noCaching ) {
      value = fetchValue();
    }


    return value;
  }

  /**
   * @param value the value to set
   */
  public void setValue(String value) {
    this.value = value;
  }


  protected abstract String fetchValue() throws DataSourceException;

  /**
   * @return the noCaching
   */
  public boolean isNoCaching() {
    return noCaching;
  }

  /**
   * @param noCaching the noCaching to set
   */
  public void setNoCaching(boolean noCaching) {
    this.noCaching = noCaching;
  }

  /**
   * @return the eloClient
   */
  public ELOClient getEloClient() {
    return eloClient;
  }

  /**
   * @param eloClient the eloClient to set
   */
  public void setEloClient(ELOClient eloClient) {
    this.eloClient = eloClient;
  }

  /**
   * @return the objId
   */
  public String getObjId() {
    return objId;
  }

  /**
   * @param objId the objId to set
   */
  public void setObjId(String objId) {
    this.objId = objId;
  }

  protected String getIndex(String index) throws RemoteException {
    return eloClient.getIndexValue(objId, index);
  }
}
