package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;

/**
 * Created by Asfarus on 05.01.2017.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test(expected = NotFoundException.class)
    public void get() throws Exception {
        service.get(100002,100000);
    }

    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
        service.delete(100002,100000);
    }

    @Test
    public void getBetweenDates() throws Exception {
        Assert.assertEquals(service.getBetweenDates(LocalDate.of(2015,5,31),LocalDate.of(2015,5,31), 100000).size(),3);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {

    }

    @Test
    public void getAll() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void save() throws Exception {

    }

}