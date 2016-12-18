package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by asfarus on 12.12.2016.
 */
public class MealServlet extends HttpServlet{
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private static final MealService MEAL_SERVICE = new MealService();
    private static final String ACTION_ADD = "add";
    private static final String ACTION_EDIT = "edit";
    private static final String ACTION_DELETE = "delete";
    private static final String ACTION_UPDATE = "update";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("GET meals");
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("POST meals");
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("action");
        if (action!=null)
        switch (action){
            case ACTION_ADD:
                req.setAttribute("meals",new Meal());
                req.getRequestDispatcher("editMeal.jsp").forward(req,resp);
                return;
            case ACTION_EDIT: {
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("meal", MEAL_SERVICE.get(id));
                req.getRequestDispatcher("editMeal.jsp").forward(req, resp);
                return;
            }
            case ACTION_DELETE: {
                int id = Integer.parseInt(req.getParameter("id"));
                MEAL_SERVICE.delete(id);
                break;
            }
            case ACTION_UPDATE:
                int id = Integer.parseInt(req.getParameter("id"));
                int caloties = Integer.parseInt(req.getParameter("calories"));
                String description = req.getParameter("description");
                LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
                MEAL_SERVICE.update(new Meal(dateTime,description,caloties,id));
                break;
        }
        req.setAttribute("meals",MEAL_SERVICE.getAll());
        req.getRequestDispatcher("meals.jsp").forward(req,resp);
    }

}
