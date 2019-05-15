package com.eafx.net;

import com.sun.net.httpserver.*;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleHttpServer {

    private static Logger logger = Logger.getLogger(SimpleHttpServer.class.getName());

    public static void main(String[] args) throws IOException {
        int PORT = 5000;
        try {
            HttpServer simpleHttpServer = HttpServer.create(new InetSocketAddress(PORT), 0);
            //log("Started on Port 5000");
            HttpContext simpleHtpContext = simpleHttpServer.createContext("/");
            //log("Created the HttpContext on \"/\"");
            simpleHtpContext.setHandler(SimpleHttpServer::simpleHttpRequestHandler);
            log("Server Listening on port: "+ PORT);
        }catch (IOException ex){
            logger.log(Level.SEVERE,"Server Failed");
        }catch(Exception ex){
            logger.log(Level.SEVERE, "Encountered Generic Java Exception. " + ex.getMessage());
        }
    }

    private static void simpleHttpRequestHandler(HttpExchange simpleHttpExchange) throws IOException{
        try {
            URI simpleRequestURI = simpleHttpExchange.getRequestURI();
            String receivedCoordinates = simpleRequestURI.toString()
            //log("Received Request at " + receivedCoordinates);
            String response = processSimpleRequest(simpleHttpExchange);
            String simpleHttpResponse = response;
            //log("Sending response \"" + response + "\" to caller");
            simpleHttpExchange.sendResponseHeaders(200, simpleHttpResponse.getBytes().length);
            OutputStream simpleOutputStream = simpleHttpExchange.getResponseBody();
            simpleOutputStream.write(simpleHttpResponse.getBytes());
            simpleOutputStream.close();
        }catch(IOException ex){
            logger.log(Level.SEVERE, "Encountered IOException. " + ex.getMessage());
        }catch(Exception ex){
            logger.log(Level.SEVERE, "Encountered Generic Java Exception. " + ex.getMessage());
        }
    }

    private static String processSimpleRequest(HttpExchange simpleHttpExchange){
        String retval = null;
        try{
            // Get the Request Headers - typically, the browser sends these.
            Headers simpleRequestHeaders = simpleHttpExchange.getRequestHeaders();
            // Get the principal. When you need to make sure that it is just you that is accessing this URI
            HttpPrincipal simpleHttpPrincipal = simpleHttpExchange.getPrincipal();
            // How did you send your request - was it a GET, PUT, POST, PATCH, DELETE?
            // Usually you use Get when sending parameters in the querystring http://<url>:<port>/?name=mike&command=move&x=300&y=0
            String simpleRequestMethod = simpleHttpExchange.getRequestMethod();
            // This is the URI that the client used to get to you
            URI simpleRequestURI = simpleHttpExchange.getRequestURI();
            // This is the querystring in the URI
            String query = simpleRequestURI.getQuery();

            //Commenting the below logging. Use it if you need it.
            //Let us print the headers first to see what can be done with them ... always good to check for nulls first
            if(simpleRequestHeaders!=null) {
                for (String str : simpleRequestHeaders.keySet()) {
                    //log("HEADER: " + str);
                }
            }

            //Let us print the Principal being used to access this endpoint, in case we need to authenticate / authorize later.
            if(simpleHttpPrincipal!=null) {
                //log("PRINCIPAL: " + simpleHttpPrincipal.toString());
            }

            //What is the method being used?
            if(simpleRequestMethod!=null){
                //log("METHOD: " + simpleRequestMethod);
            }

            //What is the URL Accessed?
            if (simpleRequestURI != null) {
                //log("URI: " + simpleRequestURI.toString());
            }

            if(query!=null && (query.length()>0)) {
                //log("QUERY: " + query);
                //Try to get the keys and values that were passed in the request and print them
                Map<String, String> params = getQueryMap(query);
                if (params != null) {
                    for (String key : params.keySet()) {
                        log(key + "=" + params.get(key));
                    }
                }

                String sName = params.getOrDefault("name","Unknown");
                String sCommand = params.getOrDefault("command","do nothing");
                String xCoordinate = params.getOrDefault("x","0");
                String yCoordinate = params.getOrDefault("y","0");

                retval = "Ok, " + sName + ". You want me to " + sCommand + " by (" + xCoordinate +"," + yCoordinate +")";
                xValue = xCoordinate;
                yValue = yCoordinate;
                xFinal = Float.parseFloat(xValue);
                yFinal = Float.parseFloat(yValue);
                MotorController.speedAdapter(xFinal, yFinal); //this is what connects to motor controller, only works on raspberry pi with this code running

            }
            else
            {
                retval = "Ok. You didnt give me any commands...";
            }
        }catch(Exception ex){
            logger.log(Level.WARNING, "Encountered Generic Java Exception. " + ex.getMessage());
        }
        return retval;
    }

    public static Map<String, String> getQueryMap(String query)
    {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (String param : params)
        {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }

    /**
     * Used for Logging info to console
     * You can also use logger.log(Level.NORMAL, <string>); but that will be more verbose so we only use logger for errors
     * @param strs
     */
    private static void log(String ... strs)
    {
        for (String str : strs) {
            System.out.println(str);
        }
    }
}
