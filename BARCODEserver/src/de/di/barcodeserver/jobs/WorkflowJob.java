
package de.di.barcodeserver.jobs;

import de.di.barcodeserver.elo.ELOClient;
import de.di.barcodeserver.profiles.ProfileManager;
import de.di.barcodeserver.workers.Worker;
import de.di.barcodeserver.workers.WorkflowWorker;
import java.util.Properties;

/**
 *
 * @author A. Sopicki
 */
public class WorkflowJob extends Job {

  private Worker worker;

  WorkflowJob(int id, Properties settings, ELOClient client, ProfileManager manager) {
    super(id);

    setEloClient(client);
    setProfileManager(manager);

    worker = new WorkflowWorker(this, settings);
  }

  public Worker getWorker() {
    return worker;
  }
}
