package de.di.barcodeserver.app.handlers;

import de.di.barcodeserver.jobs.Schedulable;
import de.di.barcodeserver.profiles.Profile;
import de.di.barcodeserver.profiles.WorkflowHandlingException;
import de.elo.ix.client.WFCollectNode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 *
 * @author A. Sopicki
 */
public class WorkflowHandler extends BaseSchedulableHandler {

    private WFCollectNode workflowNode;
    private static Pattern pattern = Pattern.compile(".*\\{(.+)\\}.*");

    public WorkflowHandler(WFCollectNode node) {
        logger = Logger.getLogger(getClass().getName());
        workflowNode = node;
    }

    @Override
    public void handleSchedulable(Schedulable s) throws HandlerException {        
        Matcher m = pattern.matcher(workflowNode.getNodeName());
        if (m.matches()) {
            String p = m.group(1);
            Profile profile = s.getProfileManager().getProfile(p);
            if (profile == null) {
                logger.error("Profile " + p + " not found. Unable to handle workflow.");
                throw new HandlerException("Unable to handle workflow.");
            }
            try {
                    profile.handleWorkflow(workflowNode);                    
            } catch (WorkflowHandlingException ex) {
                logger.debug("An error occured while handling the workflow", ex);
                logger.debug("Workflow: " + workflowNode.getFlowName() + "; DocumentID: " + workflowNode.getObjId());
                throw new HandlerException("Error while handling the workflow", ex);
            }
        } else {
            throw new HandlerException("Unable to handle workflow. No profile specified in node "
                    + workflowNode.getNodeName() + ".");
        }
        super.handleSchedulable(s);
    }
}
