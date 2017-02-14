package com.example.jair.fin.dto.olap;

import java.util.Date;

/**
 * Created by Jair on 2/9/2017.
 */

public class DateUtil {
    private static int original;

    private static int day_to_int(String dayStr){

        int dayInt ;
        switch (dayStr.toLowerCase()){
            case "mon":
                dayInt = 1;
                break;
            case "tue":
                dayInt = 2;
                break;
            case "wed":
                dayInt = 3;
                break;
            case "thu":
                dayInt = 4;
                break;
            case "fri":
                dayInt = 5;
                break;
            case "sat":
                dayInt = 6;
                break;
            case "sun":
                dayInt = 7;
                break;
            default:
                dayInt=0;
        }


        return dayInt;
    }

    private static int month_to_int(String monthStr){

        int dayInt ;
        switch (monthStr.toLowerCase()){
            case "jan":
                dayInt = 1;original=0;
                break;
            case "feb":
                dayInt = 2;original=31;
                break;
            case "mar":
                dayInt = 3;original=59;
                break;
            case "apr":
                dayInt = 4;original=90;
                break;
            case "may":
                dayInt = 5;original=120;
                break;
            case "jun":
                dayInt = 6;original=151;
                break;
            case "jul":
                dayInt = 7;original=181;
                break;
            case "aug":
                dayInt = 8;original=212;
                break;
            case "sep":
                dayInt = 9;original=243;
                break;
            case "oct":
                dayInt = 10;original=273;
                break;
            case "nov":
                dayInt = 11;original=304;
                break;
            case "dec":
                dayInt = 12;original=334;
                break;
            default:
                dayInt=0;
        }


        return dayInt;
    }

    public static int[] convertDate(Date date){
        //for example : fri feb 10 04.32.27 est 2017
        String dateStr = String.valueOf(date);
        String[] array = dateStr.split("\\s");

        int weekday = day_to_int(array[0]);
        int month = month_to_int(array[1]);
        int day = Integer.valueOf(array[2]);
        int year = Integer.valueOf(array[5]);
        //Ordinal day: 244 + 26 = 270
        //Weekday: Friday = 5
        //270 − 5 + 10 = 275
        //275 / 7 = 39.28…
        //Result: Week 39

        //int theDay = original + day;
        //int vat = theDay - weekday;
        //int vat2 = vat + 10;
        //double result = vat2/7;

        int weekYear =(int) Math.round((((original + day) - weekday) + 10)/7);

        return new int[] {weekday,weekYear,month,year};
    }



}
