package de.di.barcodeserver.workers;

import de.di.barcodeserver.jobs.Schedulable;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * The Worker class processes Schedulable objects
 * and runs as a seperate thread. It gets its Schedulable
 * object assigned from the scheduler.
 * 
 * @author A. Sopicki
 */
public abstract class Worker extends Thread {

  private Logger logger = null;
  protected Schedulable schedulable = null;
  /** Flag indicating that the Worker is currently idle */
  private volatile boolean idle = false;
  /** Flag indicating that the Worker is still active */
  private volatile boolean running = true;
  protected Properties settings = null;
  public static int wpollTime = 10000;

  public Worker(Schedulable schedulable, Properties settings) {

    this.settings = settings;
    logger = Logger.getLogger(getClass());
    this.schedulable = schedulable;
    init();
  }

  @Override
  public void run() {
    //main loop for the thread
    while (running) {
      doWork();

      try {
        idle = true;
        Thread.sleep(wpollTime);
//        idle = false;
      } catch (InterruptedException interrupt) {
        idle = false;
      } catch (Exception e) {
        idle = false;
      }
    }

    //cleanup
    schedulable = null;
//    manager = null;
    settings = null;
    logger = null;
  }

  /**
   * Will stop the Worker as soon as possible
   */
  public void shutdown() {
    running = false;
    //only intterupt while in idle mode
//    if (idle) {
      Thread.currentThread().interrupt();
      interrupt();
//    }

  }

  /**
   * Worker has been shutdown?
   * @return true if the worker is still running and false if it has been shutdown
   */
  protected boolean isRunning() {
    return running;
  }

  /**
   * Worker is still active?
   * @return true if the worker thread is still active and false otherwise
   */
  public boolean isActive() {
    return (running && isAlive());
  }

  protected abstract void doWork();

  /**
   * Initialise some variables with values from the configuration file
   */
  protected abstract void init();

}
