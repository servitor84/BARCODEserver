<%--
    Document   : index
    Created on : 18.04.2008, 07:28:50
    Author     : A. Sopicki
    Changes    : 2008-06-08 UG, Formatierung
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="de.di.barcodeserver.app.Application,de.di.barcodeserver.web.AgentServlet,de.di.barcodeserver.app.ProductInfo, de.di.barcodeserver.barcode.Info" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<fmt:bundle basename="de.di.barcodeserver.web.resource.servlet">
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <meta http-equiv="refresh" content="5; URL=<%= request.getRequestURL().toString()%>" />
            <title><fmt:message key="page.title.text"></fmt:message></title>
                <style type="text/css">
                    #footer {
                        margin-top: 3em;
                        font-size: 0.8em;
                    }

                    #logo {
                        text-align: left;
                    }

                    body {
                        background-color: #E9E9E9;
                        font-family: Arial,sans-serif;
                    }
                    div.filelist{
                        margin-top: 3em;
                        font-size: 0.8em;
                    }
                    div.status {
                        font-weight: bold;
                        color: green;
                        margin-bottom: 1em;
                    }
                    div.warning {
                        font-weight: bold;
                        color: red;
                    }
                    span.configurator {
                        font-size: 0.5em;
                    }
                    span.filename {
                        font-size: 0.8em;
                    }
                    span.headline {
                        font-size: 1.2em;
                    }
                    span.refresh {
                        font-size: 0.8em;
                    }
                    table.configurator {
                        width: 800px;
                        margin-bottom: 1em;
                    }

                    table.status {
                        border-collapse: collapse;
                    }
                    table.status td {
                        background-color: #cbcdcf;
                        border: 1px solid gray;
                        padding-top: 0.3em;
                        padding-bottom: 0.3em;
                        padding-left: 0.7em;
                        padding-right: 0.7em;
                    }
                    table.status{
                        width:600px;
                        margin-left:auto;
                        margin-right:auto;
                    }
                    table.status-top{
                        width:600px;
                        margin-left:auto;
                        margin-right:auto;
                    }
                    table.status-left{
                        width:600px;
                        margin-left:auto;
                        margin-right:auto;
                    }
                    table.status td.name {
                        font-weight: bold;
                    }
                    table.status td.value {
                        text-align: right;
                    }
                    table.fullwidth {
                        width: 100%;
                    }
                    td.name{text-align: left; margin-left: 5px;}
                </style>
            </head>
            <body>

            <%
                Application applicationObj = new Application();
                String versionInfo = applicationObj.status.get("version");
                //  versionInfo = new ProductInfo().getProgramversion() + " Build (" + versionInfo + ")";
                Application mapper = (Application) application.getAttribute(AgentServlet.agentAttribute);
                if (mapper != null && mapper.isAlive()) {
                    java.util.Map<String, String> status = mapper.getStatus();

                    String productname = status.get("product_name");

                    boolean dispatcher = Boolean.parseBoolean(status.get("dispatcher_status"));

                    if (dispatcher) {
            %>
            <table class="status-top">
                <tr>
                    <td width = "10" height ="50">
                        <img src="images/ok.gif" height = "25">
                    </td>
                    <td width="5">
                    </td>
                    <td>
                        <div class="name">
                            <fmt:message key="system.active.text">
                                <fmt:param value="<%= new ProductInfo().getProductname()%>" />
                            </fmt:message>
                        </div>
                    </td>        

                </tr>
            </table>

            <div class="status"><hr></div>
            <table class="status">
                <tr>
                    <th colspan="2" align="left"><fmt:message key="system.properties.title.text"></fmt:message>
                        <span class="refresh">(<fmt:message key="system.refresh.text" ></fmt:message>)</span></th>
                    </tr>
                    <tr>
                        <td class="name"><fmt:message key="system.properties.product.text"></fmt:message></td><td class="value"><%= new ProductInfo().getProductname()%></td>
                    </tr>
                    <tr>
                        <td class="name"><fmt:message key="system.properties.version.text"></fmt:message></td><td class="value"><%= versionInfo%></td>
                    </tr>
                    <tr>
                        <td class="name"><fmt:message key="system.properties.dispatcher_state.text"></fmt:message></td><td class="value"><%
                            String state = status.get("dispatcher_state");
                            if (state.equals("Idle")) {
                        %><fmt:message key="system.state.idle.text"></fmt:message><%            } else if (state.equals("Processing")) {
                        %><fmt:message key="system.state.processing.text"></fmt:message><%                } else {
                        %><fmt:message key="system.state.finished.text"></fmt:message><%                        }

                        %></td>
                </tr>
                <tr>
                    <td class="name"><fmt:message key="system.properties.logging.level"></fmt:message></td><td class="value"><%= Info.getLoggingInfo()%></td>
                    </tr>
                    <tr>
                        <td class="name"><fmt:message key="system.properties.service.startup"></fmt:message></td><td class="value"><%= Info.getServiceStartup()%></td>
                    </tr>
                    <tr>
                        <td class="name"><fmt:message key="system.properties.last.run"></fmt:message></td><td class="value"><%= Info.getLastRun()%></td>
                    </tr>
                    <tr>
                        <td class="name"><fmt:message key="system.properties.next.run"></fmt:message></td><td class="value"><%= Info.getNextRun()%></td>
                    </tr>
                    <tr>
                        <td class="name"><fmt:message key="system.properties.counter.ok"></fmt:message></td><td class="value"><%= Info.getCounterOK()%></td>
                    </tr>
                    <tr>
                        <td class="name"><fmt:message key="system.properties.counter.error"></fmt:message></td><td class="value"><%= Info.getCounterError()%></td>
                    </tr>
                    <tr>
                        <th colspan="2" align="left">&#160;</th>
                    </tr>
                    <tr>
                        <th colspan="2" align="left"><fmt:message key="system.properties.list.of.profile"></fmt:message></th>
                    </tr>
                    <tr>
                        <td class="name"><fmt:message key="profile.name"/></td><td class="value"><b><fmt:message key="profile.action"/></b></td>
                </tr>
                <% for (int i = 0; i < Info.getNumberOf(); i++) {%>
                <tr>
                    <td class="name"><%= Info.getProfileInfo(i)[0]%></td><td class="value"><%= Info.getProfileInfo(i)[1]%></td>
                </tr>
                <% }%>
            </table>
            <%
            } else {%>
            <table>
                <tr>
                    <td width="10" height="50">
                        <img src="images/unknown_error.gif" height="25">
                    </td>
                    <td width="5">
                    </td>
                    <td>
                        <div class="warning"><fmt:message key="system.error.text"></fmt:message></div>
                        </td>
                    </tr>
                </table>


                <table class="status">
                    <tr>
                        <td class="name"><fmt:message key="system.properties.product.text" ></fmt:message></td><td class="value"><%= new ProductInfo().getProductname()%></td>
                    </tr>
                    <tr>
                        <td class="name"><fmt:message key="system.properties.version.text" ></fmt:message></td><td class="value"><%= versionInfo%></td>
                    </tr>
                </table>
            <%  }
            } else {%>
            <table>
                <tr>
                    <td width="10" height="50">
                        <img src="images/error.gif" height = "25">
                    </td>
                    <td width="5">
                    </td>
                    <td valign="middle">
                        <%
                            java.util.List<String> errorStatus = AgentServlet.errorStatus;
                            if (errorStatus != null && errorStatus.size() > 0) {
                        %>
                        <div class="warning" valign="middle"><fmt:message key="system.exception.text" ></fmt:message></div>
                            <div valign="middle">
                            <%
                                java.lang.StringBuilder builder = new StringBuilder();
                                for (String msg : errorStatus) {
                                    builder.append(msg.replaceAll("\n", "<br />"));
                                    builder.append("<br />");
                                }

                                out.println(builder.toString());
                            %>
                        </div>
                        <%
                        } else {
                        %>
                        <div class="warning" valign="middle"><fmt:message key="system.inactive.text"></fmt:message></div>
                        <%                            }
                        %>
                    </td>
                </tr>
            </table>

            <%
                    application.removeAttribute(AgentServlet.agentAttribute);
                }%>


            <div id="footer">
                <!-- <table class="fullwidth">
             
                     <tr>
                         <td>
             
                         </td>
                     </tr>
                 </table>
                -->
            </div>
        </body>
    </html>
</fmt:bundle>