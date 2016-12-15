package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.MealWithExceedRepository;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealWithExceedService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by asfarus on 12.12.2016.
 */
public class MealServlet extends HttpServlet{
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private static final MealWithExceedRepository MEAL_WITH_EXCEED_REPOSITORY = new MealWithExceedService();
    public static final MealRepository MEAL_REPOSITORY = new MealService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("meals",MEAL_WITH_EXCEED_REPOSITORY.getAll());
        req.getRequestDispatcher("meals.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (req.get)
    }

}
