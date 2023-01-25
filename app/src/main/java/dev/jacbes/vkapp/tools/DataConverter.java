package dev.jacbes.vkapp.tools;

import java.util.Arrays;
import java.util.List;

public class DataConverter {
    private static final List<String> months = Arrays.asList(
            "Января",
            "Февраля",
            "Марта",
            "Апреля",
            "Мая",
            "Июня",
            "Июля",
            "Августа",
            "Сентября",
            "Октября",
            "Ноября",
            "Декабря"
    );

    public static String dateToString(String date) {
        if (date.length() > 5) {
            return dateWithYear(date);
        } else {
            return dateNoYear(date);
        }
    }

    private static String dateWithYear(String date) {
        String day = date.substring(0, date.indexOf('.'));
        String month = months.get(Integer.parseInt(date.substring(date.indexOf('.') + 1, date.lastIndexOf('.'))) - 1);
        String year = date.substring(date.lastIndexOf('.') + 1);
        return day + " " + month + " " + year;
    }

    private static String dateNoYear(String date) {
        String day = date.substring(0, date.indexOf('.'));
        String month = months.get(Integer.parseInt(date.substring(date.indexOf('.') + 1)) - 1);
        return day + " " + month;
    }
}
