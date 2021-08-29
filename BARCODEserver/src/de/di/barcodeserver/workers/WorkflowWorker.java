package de.di.barcodeserver.workers;

import de.di.barcodeserver.app.handlers.HandlerException;
import de.di.barcodeserver.app.handlers.SchedulableHandler;
import de.di.barcodeserver.app.handlers.WorkflowHandler;
import de.di.barcodeserver.barcode.BarcodeProcessor;
import de.di.barcodeserver.barcode.Info;
import de.di.barcodeserver.elo.ELOClient;
import de.di.barcodeserver.jobs.Schedulable;
import de.di.barcodeserver.profiles.ConvertTiff2Pdf;
import de.di.barcodeserver.profiles.WorkflowHandlingException;
import de.elo.ix.client.WFCollectNode;
import de.elo.utils.net.RemoteException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author A. Sopicki
 */
public class WorkflowWorker extends Worker {

    private int maxWorkflows = 100;
    private Logger logger;
    private String user;

    public WorkflowWorker(Schedulable schedulable, Properties settings) {
        super(schedulable, settings);
        logger = Logger.getLogger(getClass().getName());
    
        init();
        }

    @Override
    protected void doWork() {        
        ELOClient client = schedulable.getEloClient();
        try {            
            java.util.List<WFCollectNode> workflows = client.getWorkflows(maxWorkflows, user);
            logger.debug("Got " + workflows.size() + " Workflows");

            int workflowNr = 0;
            for (WFCollectNode workflow : workflows) {
                workflowNr++;
                logger.info("Handling workflow for Id: " + workflow.getObjId() + " / " + workflow.getFlowName());
                SchedulableHandler handler = new WorkflowHandler(workflow);
                BarcodeProcessor.currentProfileName = workflow.getNodeName();
                ConvertTiff2Pdf.node = workflow;
                try {                    
                    handler.handleSchedulable(schedulable);
                    Info.setCounterOK(Info.getCounterOK() + 1);
                } catch (HandlerException ex) {
                    logger.debug("An exception occured while handling the workflow " + ex.getMessage());
                    Info.setCounterError(Info.getCounterError() + 1);
                    //continue with next workflow if cause is a workflow handling problem                    
                    if (ex.getCause() != null && ex.getCause() instanceof WorkflowHandlingException) {
                    } else {
                        if(workflowNr <= workflows.size())
                        {
                            continue;
                        }
                        else
                        {
                            schedulable.abort();
                            shutdown();
                            return;
                        }
                    }
                }
            }

        } catch (Exception ex) {
            Info.setCounterError(Info.getCounterError() + 1);
            logger.error("An exception occured while handling the workflows" + ex.getMessage());            
            schedulable.abort();
            shutdown();
            return;
        }

        schedulable.finished();
        shutdown();
    }

    @Override
    protected void init() {
        try {
            maxWorkflows = Integer.parseInt(settings.getProperty("Agent.workflowsPerRun"));
            user = settings.getProperty("IndexServer.User");
        } catch (NumberFormatException nfe) {
            logger.warn("Illegal number of workflows per run. Using default value 100.");
        } catch (Exception ex) {
            logger.warn("Cannot read from config.properties ");
        }
    }
}
