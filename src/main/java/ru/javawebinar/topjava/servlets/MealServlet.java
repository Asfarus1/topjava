package ru.javawebinar.topjava.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.DateTimeUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    private MealService ms = new MealService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if ("save".equals(action)) {
            Long id = getIdFromParams(request);
            Meal meal = new Meal(id, LocalDateTime.parse(request.getParameter("dateTime")), request.getParameter("description"), Integer.valueOf(request.getParameter("calories")));
            log.debug("save {}", meal);
            ms.save(meal);
            response.sendRedirect("meals");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "edit":
                    editMeal(request, response);
                    return;
                case "remove":
                    ms.remove(getIdFromParams(request));
                default:
                    response.sendRedirect("meals");
            }
        }else {
            List<MealWithExceed> meals = ms.getAll();
            request.setAttribute("meals", meals);
            request.getRequestDispatcher("jsp/mealList.jsp").forward(request, response);
        }
    }

    private void editMeal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = getIdFromParams(req);
        Meal meal = id == 0 ? new Meal(LocalDateTime.now(),"", 0) : ms.get(id);
        log.debug(id==0 ? "add new meal" : "edit meal {}", id);
        req.setAttribute("meal", meal);
        req.getRequestDispatcher("jsp/meal.jsp").forward(req,resp);
    }

    private long getIdFromParams(HttpServletRequest req){
        String sId = req.getParameter("id");
        return sId == null ? 0 : Long.parseLong(sId);
    }
}
