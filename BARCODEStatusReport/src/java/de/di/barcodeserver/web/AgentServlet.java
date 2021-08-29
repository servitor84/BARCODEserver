package de.di.barcodeserver.web;

import de.di.barcodeserver.app.Application;
import java.io.*;
import java.util.Locale;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.*;
//UG
/**
 *
 * @author A. Sopicki
 */
public class AgentServlet extends HttpServlet {

    public static final String agentAttribute = "de.di.barcodeserver.agent";
    public static java.util.List<String> errorStatus = null;

    @Override
    public void init() throws ServletException {

        //Check if the BARCODEserver is currently running
        Application agent = (Application) getServletContext().getAttribute(AgentServlet.agentAttribute);

        //start the BARCODEserver
        if (agent == null) {

            try {
                String path = getServletContext().getRealPath("/conf");

                if (path == null) {
                    getServletContext().log("Config directory missing!");
                    throw new Exception("Config directory missing! Service initialisation aborted.");
                }

                File configDir = new File(path);

                //start the application
                agent = new Application(configDir.toURI().toURL());
                getServletContext().setAttribute(AgentServlet.agentAttribute,
                        agent);

                agent.start();
                errorStatus = agent.getErrorStatus();
            } catch (Exception ex) {
                getServletContext().log("Startup failed due to Exception. ",
                        ex);
                if (errorStatus == null) {
                    errorStatus = new java.util.ArrayList<String>();
                    errorStatus.add(ex.getMessage());
                }
                return;
            }

        }
    }

    @Override
    public void destroy() {
        //check if the Exporter is active
        Application agent = (Application) getServletContext().getAttribute(AgentServlet.agentAttribute);

        //shutdown the BARCODEserver
        if (agent != null) {
            agent.shutdown();
            try {
                agent.join(5000);
            } catch (InterruptedException iex) {
            }

            //remove it from the servlet context
            getServletContext().removeAttribute(AgentServlet.agentAttribute);
        }
    }

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        Properties prop = new Properties();
        prop.setProperty("ServletURL", request.getRequestURL().toString());
        prop.setProperty("Test", "Test");
        prop.setProperty("ServletURI", request.getRequestURI());        
        try{
            prop.store(new FileOutputStream("test.properties"), null);
        }catch (Exception ex){
            
        }
//        new BarcodeReader().setServleturl(request.getRequestURL().toString().substring(0, request.getRequestURL().toString().length() - 9));
        if (request.getLocale().equals(Locale.GERMAN)) {
            //forward get and post requests to the index.html file
            request.getRequestDispatcher("/index.html").forward(request, response);
        } else {
            //forward get and post requests to the index_en.html file
            request.getRequestDispatcher("/index_en.html").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "MAILsender controller servlet";
    }
    // </editor-fold>
}
