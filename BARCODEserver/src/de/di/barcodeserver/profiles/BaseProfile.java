
package de.di.barcodeserver.profiles;

import de.di.barcodeserver.elo.ELOClient;
import de.di.barcodeserver.profiles.datasources.DataSource;
import de.di.barcodeserver.profiles.datasources.ELODataSource;
import de.di.barcodeserver.profiles.datasources.IniFileDataSource;
import de.di.barcodeserver.profiles.datasources.PropertiesDataSource;
import java.io.File;

/**
 *
 * @author A. Sopicki
 */
public abstract class BaseProfile implements Profile {
  
  protected String profileName;

  private ELOClient client;

  private String dataSourceType;
  private String dataSourceParams;
  private String objId;

  public String getProfileName() {
    return profileName;
  }

  public void setProfileName(String name) {
    profileName = name;
  }

  /**
   * @return the client
   */
  public ELOClient getClient() {
    return client;
  }

  /**
   * @param client the client to set
   */
  public void setClient(ELOClient client) {
    this.client = client;
  }

  protected DataSource getDataSource()
    throws ProfileException {

    DataSource source = null;

    if ( getDataSourceType().equalsIgnoreCase("elo") ) {
      source = new ELODataSource(dataSourceParams);

      //disable caching on dynamic lookup
      if ( dataSourceParams.startsWith("@@") ) {
        source.setNoCaching(true);
      }
    } else if ( getDataSourceType().equalsIgnoreCase("ini-file") ) {
      String[] parts = dataSourceParams.split(";");

      if ( parts.length != 3 ) {
        throw new ProfileException("Illegal paramater count for INI-File data source");
      }

      source = new IniFileDataSource(new File(parts[0]), parts[1], parts[2]);

      //disable caching on dynamic lookup
      if ( parts[2].startsWith("@@") ) {
        source.setNoCaching(true);
      }
    } else if ( getDataSourceType().equalsIgnoreCase("properties-file") ) {
      String[] parts = dataSourceParams.split(";");

      if ( parts.length != 2 ) {
        throw new ProfileException("Illegal paramater count for Properties-File data source");
      }

      source = new PropertiesDataSource(new File(parts[0]), parts[1]);

      //disable caching on dynamic lookup
      if ( parts[1].startsWith("@@") ) {
        source.setNoCaching(true);
      }
    }

    if ( source != null ) {
      source.setObjId(objId);
      source.setEloClient(client);

      return source;
    }

    throw new ProfileException("Illegal datasource for profile "+profileName);
  }

  /**
   * @return the dataSourceType
   */
  protected String getDataSourceType() {
    return dataSourceType;
  }

  /**
   * @param dataSourceType the dataSourceType to set
   */
  protected void setDataSourceType(String dataSourceType) {
    this.dataSourceType = dataSourceType;
  }

  /**
   * @return the dataSourceParams
   */
  protected String getDataSourceParams() {
    return dataSourceParams;
  }

  /**
   * @param dataSourceParams the dataSourceParams to set
   */
  protected void setDataSourceParams(String dataSourceParams) {
    this.dataSourceParams = dataSourceParams;
  }

  /**
   * @return the objId
   */
  protected String getObjId() {
    return objId;
  }

  /**
   * @param objId the objId to set
   */
  protected void setObjId(String objId) {
    this.objId = objId;
  }

  
}
