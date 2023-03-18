package com.hiral.learningspring.util;


import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class DateUtils {
    public Date createDateFromString(String dateString){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        if(StringUtils.hasText(dateString)){
            try {
              date = format.parse(dateString);
            } catch (ParseException e) {
                date = new Date();
                throw new RuntimeException(e);
            }
        } else {
            date = new Date();
        }

        return date;
    }
}
