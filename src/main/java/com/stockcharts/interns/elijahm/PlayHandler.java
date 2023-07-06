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


public class PlayHandler implements CommandHandler{

    private Configuration cfg;
    private Logger logger;

    public PlayHandler(Configuration cfg, Logger logger) {
        this.cfg = cfg; this.logger = logger;
    }

    public void handle(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt((String) request.getParameter("storyid"));

            Story story = StoryParser.parseStory(id);

            logger.info("story 1: " + story.toString());

            Map<String, Integer> words = new HashMap<>();
            words.put("noun", story.getNounCount());
            words.put("verb", story.getVerbCount());
            words.put("adjective", story.getAdjectiveCount());
            words.put("storyid", story.getId());

            Template template = null;

            try {
                template = cfg.getTemplate("form.ftl");
            } catch (Exception e) {
                e.printStackTrace();
            } 
            
            try (PrintWriter out = response.getWriter() ) {
                FormCreator.sendForm(words, template, out);
            } catch (IOException e) {
                logger.error("I/O Exception sending HTML to {} in response to {}", request.getRemoteAddr(), request.getRequestURI(), e);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
