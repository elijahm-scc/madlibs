package com.stockcharts.interns.elijahm;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import freemarker.template.*;

import org.apache.logging.log4j.*;

import main.java.com.stockcharts.interns.elijahm.FormCreator;

@WebServlet(name="madlibs", urlPatterns={"/*"}, loadOnStartup=0)
public class MadlibsServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();
    private static final Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);

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

            // Set response defaults (so that "normal" handlers don't need to)
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");

            Map<String, Integer> words = new HashMap<>();

            words.put("noun", 5);
            words.put("adjective", 2);
            Template template = cfg.getTemplate("form.ftl");
            // Send the output as the response
            try (PrintWriter out = response.getWriter() ) {
                FormCreator.sendForm(words, template, out);
            } catch (IOException e) {
                logger.error("I/O Exception sending HTML to {} in response to {}", request.getRemoteAddr(), request.getRequestURI(), e);
            }

            long latency = System.currentTimeMillis() - startTimeMillis;
            logger.info("OT - {}ms {}", latency, request.getRequestURL());
        } catch (Exception e) {
            logger.error("Unexpected exception encountered when processing: {}", request.getRequestURI(), e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
        
        try {
            long startTimeMillis = System.currentTimeMillis();

            logger.debug("IN - {}", request.getRequestURL());

            // Set response defaults (so that "normal" handlers don't need to)
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");

            long latency = System.currentTimeMillis() - startTimeMillis;
            logger.info("OT - {}ms {}", latency, request.getRequestURL());
        } catch (Exception e) {
            logger.error("Unexpected exception encountered when processing: {}", request.getRequestURI(), e);
        }
        
    }

}
