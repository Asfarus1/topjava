package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.util.exceptions.NotFoundException;

/**
 * Created by asfarus on 15.06.2017.
 */
public class ValidationUtil {
    public static void checkNotFoundWithId(boolean found, long id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, long id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) throw new NotFoundException("Not found entity with " + msg);
    }
}
