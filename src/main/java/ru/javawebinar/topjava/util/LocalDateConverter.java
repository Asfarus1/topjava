package ru.javawebinar.topjava.util;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

/**
 * Created by asfarus on 13.02.2017.
 */
public class LocalDateConverter implements Converter<String,LocalDate> {
    @Override
    public LocalDate convert(String source) {
        return DateTimeUtil.parseLocalDate(source);
    }
}
