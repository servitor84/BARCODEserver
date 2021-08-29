package de.di.barcodeserver.app.handlers;

import de.di.barcodeserver.jobs.Schedulable;

/**
 *
 * @author A. Sopicki
 */
public interface SchedulableHandler {
  public void handleSchedulable(Schedulable s) throws HandlerException;

  public void setNextHandler(SchedulableHandler next);

  public SchedulableHandler getNextHandler();
}
