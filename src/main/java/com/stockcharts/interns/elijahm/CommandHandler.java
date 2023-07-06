package main.java.com.stockcharts.interns.elijahm;

import javax.servlet.*;
import javax.servlet.http.*;

public interface CommandHandler {
    void handle(HttpServletRequest request, HttpServletResponse response);
}
