package de.di.barcodeserver.jobs;

import de.di.dokinform.recovery.Id;
import de.di.barcodeserver.elo.ELOClient;
import de.di.barcodeserver.profiles.ProfileManager;
import java.util.Properties;

/**
 *
 * @author A. Sopicki
 */
public class SchedulableFactory {

  private Id nextJobId = new Id();
  
  private Properties settings;

  private ELOClient client;

  private ProfileManager manager;

  public SchedulableFactory(Properties settings, ELOClient c, ProfileManager manager) {
    this.settings = settings;
    client = c;
    this.manager = manager;
  }

  public Schedulable createWorkflowJob() {
    return new WorkflowJob(nextJobId.nextId(), settings, client, manager);
  }

  public void setNextJobId(int i) {
    nextJobId.setId(i);
  }

  public int getLastJobId() {
    return nextJobId.getLastId();
  }
}
