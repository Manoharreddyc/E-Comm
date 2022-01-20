package com.mrc.FundTransfer.helper;

import ch.qos.logback.classic.pattern.DateConverter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
@Component
public class Helper {
    public static long generateAccountNumber(){
        return Long.parseLong(DateConverter.convertDateToString(new Date(),"ddMMyyyyhhmmss")+generateRandom(1,5));
    }
    public static String generateReferenceNumber(){
        return UUID.randomUUID().toString();
    }
    private static int generateRandom(int minimum ,int maximum){
        return (int)((Math.random()*maximum) + minimum);
    }
}
