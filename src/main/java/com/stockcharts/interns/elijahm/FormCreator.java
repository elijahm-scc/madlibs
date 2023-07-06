package main.java.com.stockcharts.interns.elijahm;

import java.io.PrintWriter;
import java.io.*;
import java.util.*;
import freemarker.template.*;

public class FormCreator {

    public static void sendForm(Map<String, Integer> fieldCounts, Template template, PrintWriter out) {

        Map<String, Object> dataModel = new HashMap<>();

        Map<String, String> formFields = new HashMap<>();
        for (Map.Entry<String, Integer> entry : fieldCounts.entrySet()) {
            String fieldName = entry.getKey();
            if (fieldName.startsWith("story")) continue;
            int fieldCount = entry.getValue();
            StringBuilder fieldHtml = new StringBuilder();
            for (int i = 1; i <= fieldCount; i++) {
                fieldHtml
                    .append(fieldName)
                    .append(" ")
                    .append(i)
                    .append(": <input type='text' name='")
                    .append(fieldName).append(i)
                    .append("' value=''><br>");
            }
            formFields.put(fieldName, fieldHtml.toString());
        }

        dataModel.put("formFields", formFields);
        dataModel.put("storyid", ""+fieldCounts.get("storyid"));

        
        try {  
            template.process(dataModel, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
