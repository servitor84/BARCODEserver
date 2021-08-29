
package de.di.barcodeserver.profiles;

import de.di.barcodeserver.elo.ELOClient;
import de.elo.ix.client.WFCollectNode;

/**
 *
 * @author A. Sopicki
 */
public interface Profile {

  public String getProfileName();

  public void setProfileName(String name);

  public void init(java.util.Properties props) throws ProfileException;

  public void setClient(ELOClient client);

  public void handleWorkflow(WFCollectNode node) throws WorkflowHandlingException;

}
