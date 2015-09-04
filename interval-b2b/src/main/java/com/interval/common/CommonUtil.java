package com.interval.common;

/**
 * Created by User on 9/4/2015.
 */
public class CommonUtil {

    public static int convertToInt(final String key){
        int id = 0;
        try{
            id = Integer.parseInt(key);
        }catch (NumberFormatException exc){

        }
        return id;
    }
}
