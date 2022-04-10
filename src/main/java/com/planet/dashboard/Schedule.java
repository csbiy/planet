package com.planet.dashboard;

import lombok.Getter;
import org.springframework.core.env.PropertyResolver;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
@Getter
public final class Schedule {

    private final CronTrigger cronTrigger;

    public Schedule(PropertyResolver propertyResolver) {

        String year = propertyResolver.getProperty("schedule.year");
        String month = propertyResolver.getProperty("schedule.month");
        String day = propertyResolver.getProperty("schedule.day");
        String dayOfWeek = propertyResolver.getProperty("schedule.dayOfWeek");
        String hour = propertyResolver.getProperty("schedule.hour");
        String minute = propertyResolver.getProperty("schedule.minute");
        String second = propertyResolver.getProperty("schedule.second");
        String expression = String.format("%s %s %s %s %s %s", second, minute, hour, day, month, dayOfWeek, year);
        if(!CronExpression.isValidExpression(expression)){
            throw new IllegalArgumentException("유효하지 않은 스케쥴 시간입니다.");
        }
        this.cronTrigger = new CronTrigger(expression);
    }
}
