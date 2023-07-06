package main.java.com.stockcharts.interns.elijahm;

import java.io.*;
import java.util.*;

import freemarker.template.*;
import org.json.*;

import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.logging.log4j.*;


public class ViewHandler implements CommandHandler{
    
    private Configuration cfg;
    private Logger logger;

    public ViewHandler(Configuration cfg, Logger logger) {
        this.cfg = cfg; this.logger = logger;
    }

    public void handle(HttpServletRequest request, HttpServletResponse response) {
        try {
            String idString = request.getParameter("storyid");
            
            int id = idString == null 
                ? 0 
                : Integer.parseInt(idString);
            
            Story story = StoryParser.parseStory(id);

            Map<String, String[]> params = request.getParameterMap();
            Map<String, String> words = new HashMap<>();

            for (Map.Entry<String, String[]> e : params.entrySet()) 
                words.put(e.getKey(), e.getValue()[0]);

            words.put("storyid", ""+id);

            Template template = new Template("temp", new StringReader(story.getText()), cfg);

            try (PrintWriter out = response.getWriter() ) {
                out.println("this is working: ");
                template.process(words, out);
            } catch (IOException e) {
                logger.error("I/O Exception sending HTML to {} in response to {}", request.getRemoteAddr(), request.getRequestURI(), e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
