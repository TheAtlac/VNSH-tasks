package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import sorting.models.ArrayToSort;

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
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getMethod());
        resp.setStatus(200);
        resp.getWriter().println("pong");
        resp.setContentType("text/plane");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(JSON_VALUE);
        System.out.println("start post");
        if (!req.getContentType().contains(JSON_VALUE)) {
            System.out.println(400);
            resp.setStatus(400);
            mapper.writeValue(resp.getWriter(), Map.of("error", "Expected " + JSON_VALUE));

            return;
        }
        ArrayToSort value;
        try {
            value = mapper.readValue(req.getInputStream(), ArrayToSort.class);
        } catch (Exception e) {
            System.out.println(e);
            resp.setStatus(400);
            return;
        }
        if (value == null || value.getValues() == null) {
            resp.setStatus(400);
            mapper.writeValue(resp.getWriter(), Map.of("errorMessage", "Array is null"));
            return;
        }

        System.out.println("i am sorting");
        if (req.getParameter("algorithm") != null) {
            System.out.print("rechosed " + req.getParameter("algorithm"));
            value.setAlgorithm(req.getParameter("algorithm"));
        }

        if (!Objects.equals(value.getAlgorithm(), "bubble") && !Objects.equals(value.getAlgorithm(),
                "selection") && !Objects.equals(value.getAlgorithm(), "bogo")) {
            mapper.writeValue(resp.getWriter(), Map.of("errorMessage", "Array is null"));
            resp.setStatus(404);
        }
        System.out.println(mapper.writeValueAsString(value));
        System.out.println(value.getAlgorithm());
        System.out.println(value.getTime());
        System.out.println(Arrays.toString(value.getValues()));
        resp.getWriter().write(mapper.writeValueAsString(value));
        resp.setStatus(200);
    }

//    host/path?par1=val1&par2=var2&par2=var3


}