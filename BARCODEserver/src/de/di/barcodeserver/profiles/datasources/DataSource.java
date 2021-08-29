package de.di.barcodeserver.profiles.datasources;

import de.di.barcodeserver.elo.ELOClient;

/**
 *
 * @author A. Sopicki
 */
public interface DataSource {
  public String getValue() throws DataSourceException;

  public void setEloClient(ELOClient client);

  public void setObjId(String objId);

  public boolean isNoCaching();

  public void setNoCaching(boolean noCaching);
}
