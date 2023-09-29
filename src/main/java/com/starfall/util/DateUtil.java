package com.starfall.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class DateUtil {
    public static boolean isConsecutiveDates(LocalDateTime start, LocalDateTime end){
        LocalDate[] dates = {
                LocalDate.of(start.getYear(), start.getMonth(), start.getDayOfMonth()),
                LocalDate.of(end.getYear(), end.getMonth(), end.getDayOfMonth())
        };
        Arrays.sort(dates);
        if (!dates[1].minusDays(1).equals(dates[0])) {
            return false;
        }
        return true;
    }
    public static boolean isSameDay(LocalDateTime start, LocalDateTime end){
        return start.getYear() == end.getYear() && start.getMonth() == end.getMonth() && start.getDayOfMonth() == end.getDayOfMonth();
    }
}
