package com.stockcharts.interns.elijahm;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import org.json.*;


import freemarker.template.*;

import org.apache.logging.log4j.*;

import main.java.com.stockcharts.interns.elijahm.CommandHandler;
import main.java.com.stockcharts.interns.elijahm.FormCreator;
import main.java.com.stockcharts.interns.elijahm.PlayHandler;
import main.java.com.stockcharts.interns.elijahm.SelectStoryHandler;
import main.java.com.stockcharts.interns.elijahm.ViewHandler;

@WebServlet(name="madlibs", urlPatterns={"/*"}, loadOnStartup=0)
public class MadlibsServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();
    private static final Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
    private static final Map<String, CommandHandler> commands = new HashMap<>();


    @Override
    public void init(ServletConfig config) throws ServletException {
        logger.info("===============================================");
        logger.warn(" madlibs             - init()");
        logger.info("===============================================");

        File templatePath = new File(config.getServletContext().getRealPath("/WEB-INF/classes/templates"));
        try {
            cfg.setDirectoryForTemplateLoading(templatePath);
        } catch (IOException e) {
            logger.fatal("IOException setting template directory");
            throw new UnavailableException("Unable to set template directory");
        }

        commands.put("select", new SelectStoryHandler(cfg, logger));
        commands.put("play", new PlayHandler(cfg, logger));
        commands.put("view", new ViewHandler(cfg, logger));

        logger.info("============ init() complete! ============");
        logger.info("Debugging URL: http://localhost:8080/madlibs/*");
    }

    @Override
    public void destroy() {
        logger.info(":::::::::::::::::::::::::::::::::::::::::::::::");
        logger.warn(" madlibs             - destroy()");
        logger.info(":::::::::::::::::::::::::::::::::::::::::::::::");

        // Destroy long-lived objects here

        logger.info("============ destroy() complete! ============");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            long startTimeMillis = System.currentTimeMillis();

            logger.debug("IN - {}", request.getRequestURL());

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");

            String cmdString = (String) request.getParameter("cmd");
            String idstring = (String) request.getParameter("storyid");
            int storyid = idstring == null || idstring.isBlank()
                ? 1
                : Integer.parseInt(idstring);
            
            
            CommandHandler handler = commands.getOrDefault(cmdString, commands.get("select"));
            handler.handle(request, response);

            long latency = System.currentTimeMillis() - startTimeMillis;
            logger.info("OT - {}ms {}", latency, request.getRequestURL());
        } catch (Exception e) {
            logger.error("Unexpected exception encountered when processing: {}", request.getRequestURI(), e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            long startTimeMillis = System.currentTimeMillis();

            logger.debug("IN - {}", request.getRequestURL());

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");

            long latency = System.currentTimeMillis() - startTimeMillis;
            logger.info("OT - {}ms {}", latency, request.getRequestURL());
            
            logger.info("didPost");

            
            
            doGet(request, response);

            // JSONObject js = new JSONObject(body);
            
        } catch (Exception e) {
            logger.error("Unexpected exception encountered when processing: {}", request.getRequestURI(), e);
        }
        
    }

}
