package api;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import sorting.models.ArrayToSort;
import sorting.Sort;

public class FirstServlet extends HttpServlet {
    public static final String JSON_VALUE = "application/json";
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        System.out.println("Initializing servlet");
        mapper = new ObjectMapper();
    }

    @Override
    public void destroy() {
        System.out.println("Destroying servlet");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getMethod());
        if (Objects.equals(req.getMethod(), "'GET'")) doGet(req, resp);
        if (Objects.equals(req.getMethod(), "'POST'")) doPost(req, resp);
//        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getMethod());
        System.out.println("req.getParameter(\"par1\") = " + req.getParameter("p"));
        System.out.println("req.getParameterValues(\"par2\") = " + Arrays.toString(req.getParameterValues("par2")));
        System.out.println("req.getServletPath() = " + req.getServletPath());
        System.out.println("req.getPathInfo() = " + req.getPathInfo());
        if (Objects.equals(req.getServletPath(), "/ping")) {
            resp.setStatus(200);
            resp.getWriter().println("pong");
            resp.setContentType("text/plane");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getRequestURI());
        System.out.println(req.getRequestURL());
        System.out.println(req.getHeader("content-type"));
        System.out.println(req.getContentType());
        resp.setContentType(JSON_VALUE);
        if (!req.getContentType().contains(JSON_VALUE)) {
            System.out.println(400);
            resp.setStatus(400);
            mapper.writeValue(resp.getWriter(), Map.of("error", "Expected " + JSON_VALUE));

            return;
        }

        Message value = mapper.readValue(req.getInputStream(), Message.class);

        if (value == null || value.getValue() == null) {
            resp.setStatus(400);
            mapper.writeValue(resp.getWriter(), Map.of("error", "message is null"));
            return;
        }

        String message = Sort.sort(value.getValue());
        mapper.writeValue(resp.getOutputStream(), new Message(message));
        resp.setStatus(200);
    }

//    host/path?par1=val1&par2=var2&par2=var3


}