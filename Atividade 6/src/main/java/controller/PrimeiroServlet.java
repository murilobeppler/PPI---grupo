package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class PrimeiroServlet extends HttpServlet {
    public void init() throws ServletException {
        super.init();
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        String html = "<html>" + "<head><title>Servlet Servlet</title></head>" + "</head>" + "<body>" + "<h1>Servlet Servlet</h1>" + "</body>" + "</html>";
        PrintWriter out = resp.getWriter();
        out.print(html);
        out.close();
    }

    public void destroy() {
        super.destroy();
    }

}
