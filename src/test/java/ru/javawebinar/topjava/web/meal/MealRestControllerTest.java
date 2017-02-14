package ru.javawebinar.topjava.web.meal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by asfarus on 13.02.2017.
 */
public class MealRestControllerTest  extends AbstractControllerTest{
    private int userId;
    private final String REST_URL = MealRestController.REST_URL + "/";

    @Autowired
    protected MealService mealService;

    @Before
    public void setUp(){
        super.setUp();
        userId = AuthorizedUser.id();
        AuthorizedUser.setId(USER_ID);
    }

    @After
    public void tearDown(){
        AuthorizedUser.setId(userId);
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + String.valueOf(MEAL1.getId())))
                .andExpect(status().isOk())
                .andDo(print())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(MealTestData.MATCHER.contentMatcher(MEAL1))
        ;

    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(
                        WITH_EXCEED_MODEL_MATCHER.contentListMatcher(MealsUtil.getWithExceeded(mealService.getAll(userId), AuthorizedUser.getCaloriesPerDay()))
                );
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = new Meal(MEAL1_ID, MEAL1.getDateTime(), MEAL1.getDescription(), MEAL1.getCalories());
        updated.setDescription("UpdatedName");
        mockMvc.perform(put(REST_URL + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, mealService.get(MEAL1_ID, USER_ID));

    }

    @Test
    public void testCreateWithLocation() throws Exception {

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(MEAL1)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        Meal createdMeal = MATCHER.fromJsonAction(action);
        MEAL1.setId(createdMeal.getId());
        MATCHER.assertEquals(MEAL1,createdMeal);
    }

    @Test
    public void testGetBetween() throws Exception {

        TestUtil.print(mockMvc.perform(get(REST_URL + "between?startDate=2015-05-30&startTime=10:00:00&endDate=2015-05-30&endTime=14:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(WITH_EXCEED_MODEL_MATCHER.contentListMatcher(
                        MealsUtil.getFilteredWithExceeded(
                                mealService.getBetweenDates(DateTimeUtil.parseLocalDate("2015-05-30"), DateTimeUtil.parseLocalDate("2015-05-30"), USER_ID),
                                DateTimeUtil.parseLocalTime("10:00:00"),
                                DateTimeUtil.parseLocalTime("14:00:00"),
                                USER.getCaloriesPerDay()
                        ))
                )
        );
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + String.valueOf(MEAL1_ID)))
                .andExpect(status().isOk())
                .andDo(print());

        MATCHER.assertCollectionEquals(Arrays.asList(MEAL6,MEAL5,MEAL4,MEAL3,MEAL2), mealService.getAll(UserTestData.USER_ID));
    }

}