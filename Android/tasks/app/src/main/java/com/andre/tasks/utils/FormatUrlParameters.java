package com.andre.tasks.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUrlParameters {

    public  static String formatBoolean(Boolean value){
        if(value){
            return "true";
        }
        else {
            return "false";
        }
    }

    public static String formatDate(Date value){
        if(value == null){
            return "";
        }
        else {
            SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
            return adf.format(value);
        }
    }
}
