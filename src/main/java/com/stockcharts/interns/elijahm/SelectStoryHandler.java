package main.java.com.stockcharts.interns.elijahm;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.MalformedInputException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;

import freemarker.template.*;
import org.apache.logging.log4j.*;

public class SelectStoryHandler implements CommandHandler {
    private Configuration cfg;
    private Logger logger;

    public SelectStoryHandler(Configuration cfg, Logger logger) {
        this.cfg = cfg; this.logger = logger;
    }

    public void handle(HttpServletRequest request, HttpServletResponse response) {
        try {

            Template template = cfg.getTemplate("selectstory.ftl");

            try (PrintWriter writer = response.getWriter()) {
                template.process(new HashMap<>(), writer);
            } catch (Exception e) {
                // TODO: handle exception
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
