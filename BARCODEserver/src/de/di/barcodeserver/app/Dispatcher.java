package de.di.barcodeserver.app;

import de.di.barcodeserver.jobs.Schedulable;
import de.di.barcodeserver.jobs.SchedulableFactory;
import de.di.barcodeserver.jobs.SchedulableListener;
import de.di.barcodeserver.jobs.Schedulable.Status;
import de.di.barcodeserver.workers.Worker;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * The dispatcher will schedule the export of the data to the SAP-MAP database.
 *
 * @author A. Sopicki
 */
public class Dispatcher extends Thread implements SchedulableListener {

    private Properties settings = null;
    /**
     * Flag indicating that the dispatcher thread is still active
     */
    private volatile boolean running = true;
    /**
     * Flag indicating that the thread is currently idle
     */
    private volatile boolean idle = false;
    private SchedulableFactory schedulableFac = null;
    private Logger logger = null;
    /**
     * Time in milliseconds between every check of the input directory
     */
    private int pollTime = 10000;
    private static final Boolean mutex = new Boolean(false);
    private Schedulable workflow = null;
    private Schedulable resetJob = null;
    private State state = State.Idle;

    /**
     * Different states of the Dispatcher:
     *
     * Idle: Not processing any workflows Processing: Processing workflows
     * Finished: Shutting down application
     */
    public enum State {

        Idle, Processing, Finished
    }

    /**
     * Singleton constructor
     *
     * @param scheduler the scheduler to use for scheduling jobs
     * @sa Dispatcher.getDispatcher(Scheduler scheduler)
     */
    Dispatcher(Properties settings, SchedulableFactory fac) {
        super("DispatcherThread");
        schedulableFac = fac;
        this.settings = settings;
        logger = Logger.getLogger(getClass().getName());
        init();
    }

    @Override
    public void run() {
        logger.trace(getClass().getName() + ": run()");
        while (state != State.Finished) {            
            //wait till export thread is finished
            if (state == State.Idle) {
                //check for new documents
                startProcessing();
            }
            idle = true && running; //mark thread as idle
            try {
                int sleepTime = pollTime;

                if (idle) {
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException ex) {
            }
            idle = false;
        }
        //shutdown exporter worker thread if necessary
        if (workflow != null) {
            workflow.getWorker().shutdown();
        }
        logger.trace(getClass().getName() + ": run()");
        //cleanup
        settings = null;
        logger = null;
    }

    public synchronized void shutdown() {
        running = false;
        state = State.Finished;
        //if thread is idle send an interrupt
        if (idle) {
            this.interrupt();
        }
    }

    public State getProcessingState() {
        return state;
    }

    private synchronized void startProcessing() {

        //only start the exporter if not currently running
        if (workflow == null) {
            logger.info("Workflow handler started");
            state = State.Processing;
            workflow = schedulableFac.createWorkflowJob();
            workflow.addSchedulableListener(this);
            workflow.getWorker().start();
        }
        return;
    }

    @Override
    public void finished(Schedulable s) {
        synchronized (mutex) {
            if (s == workflow) {
                workflow = null;

                if (state != State.Finished) {
                    state = State.Idle;
                }

                Status status = s.getStatus();
                if (status == Schedulable.Status.DONE && logger != null) {
                    logger.info("All workflows handled");
                } else if (status == Schedulable.Status.FAILED || status == Schedulable.Status.ABORTED) {
                    if (logger != null) {
                        logger.warn("An error occured while handling the workflows");
                    }
                }
            }
            if (s == resetJob) {
                resetJob = null;
                if (state != State.Finished) {
                    state = State.Idle;
                }
                Status status = s.getStatus();
                if (status == Schedulable.Status.DONE && logger != null) {
                    logger.trace("Documents reset and ready for testing.");
                } else if (status == Schedulable.Status.FAILED || status == Schedulable.Status.ABORTED) {
                    if (logger != null) {
                        logger.warn("Resetting documents failed.");
                    }
                }
            }
        }
    }

    private void init() {
        try {
            pollTime = Integer.parseInt(settings.getProperty("Application.PollTime", Integer.toString(pollTime)));
            if (pollTime < 4000) {
                pollTime = 10000;
                logger.warn("Application.PollTime has not been configured properly\n"
                        + "Using default value of " + pollTime + " milliseconds");
            }
            Worker.wpollTime = pollTime;
        } catch (Exception e) {
            if (!settings.containsKey("Apllication.PollTime")) {
                logger.warn("Application.PollTime has not been configured\n"
                        + "Using default value of " + pollTime + " milliseconds " + e.getMessage());
            }
        }
    }
}
