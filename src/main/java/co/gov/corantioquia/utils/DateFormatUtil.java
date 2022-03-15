package co.gov.corantioquia.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    public static String formatDate(Date date){
        return formatDate(date, "dd/MM/yyyy");
    }


    public static String formatDate(Date date, String pattern){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
}
