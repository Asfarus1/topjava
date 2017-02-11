package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Created by asfarus on 11.02.2017.
 */
@Controller
@RequestMapping(value = "/meals", method = RequestMethod.POST)
public class MealController extends AbstractMealController{

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("mealList", super.getAll());
        return "meals";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public String delete(HttpServletRequest request){
        super.delete(getId(request));
        return "redirect:/meals";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String create(HttpServletRequest request, Model model){
        model.addAttribute("meal", new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000));
        return "meal";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/update")
    public String edit(HttpServletRequest request, Model model){
        model.addAttribute("meal", super.get(getId(request)));
        return "meal";
    }

    @RequestMapping(value = "/filter")
    public String filteredList(Model model, HttpServletRequest request){
        LocalDate startDate = DateTimeUtil.parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = DateTimeUtil.parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = DateTimeUtil.parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = DateTimeUtil.parseLocalTime(request.getParameter("endTime"));
        model.addAttribute("mealList", super.getBetweenDateTimes(startDate, endDate, startTime, endTime));
        return "meals";
    }

    @RequestMapping(value = "/save")
    public String save(Model model, HttpServletRequest request){
        final Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

        if (request.getParameter("id").isEmpty()) {
            log.info("Create {}", meal);
            super.save(meal);
        } else {
            log.info("Update {}", meal);
            meal.setId(getId(request));
            super.update(meal);
        }
        return "redirect:/meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
