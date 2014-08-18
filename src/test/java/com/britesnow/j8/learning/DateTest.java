package com.britesnow.j8.learning;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.junit.Test;


/**
 * <p></p>
 */
public class DateTest {

    @Test
    public void testClock(){
        Clock clock = Clock.systemDefaultZone();
        
        System.out.println("millsecs: "+ clock.millis() + " = " + System.currentTimeMillis());
        System.out.println("TimeZone: " + clock.getZone());
        
        // Get Default Zone
        clock = Clock.systemUTC();
        System.out.println("TimeZone: " + clock.getZone());
        
        // Get Spe Zone
        clock = Clock.system(ZoneId.of("Asia/Harbin"));
        System.out.println("TimeZone: " + clock.getZone());
        
        //To date
        Date date = Date.from(clock.instant());
        System.out.println("Date: " + date);
        System.out.println("\n");
    }
    
    @Test
    public void testLocalTime(){
        LocalTime now1 = LocalTime.now(ZoneId.systemDefault());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime now2 = LocalTime.now(ZoneId.systemDefault());
        System.out.println("now1:" + now1);
        System.out.println("now2:" + now2);
        // compare both times
        System.out.println("compare: now1 before now2 "+ now1.isBefore(now2));
        System.out.println("secs between: " + ChronoUnit.SECONDS.between(now1, now2));
        System.out.println("mill secs between: " + ChronoUnit.MILLIS.between(now1, now2));
        System.out.println("\n");
    }
    
    @Test
    public void testLocalDate(){
        LocalDate now1 = LocalDate.now();
        LocalDate now2 = LocalDate.now();
        now2 = now2.plusDays(2);
        System.out.println("now1:" + now1);
        System.out.println("now2:" + now2);
        
        //get month
        System.out.println("month:" + now2.getMonth());
        System.out.println("month value:" + now2.getMonthValue());
        //compare date
        System.out.println("compare: now1 after now2 "+ now1.isAfter(now2));
        System.out.println("days between: " + ChronoUnit.DAYS.between(now1, now2));
        //test if it is leap
        System.out.println("isLeap year: " + now2.isLeapYear());
        now2 = now2.plusYears(2);
        System.out.println("isLeap year: " + now2.isLeapYear());
        // get start of day
        System.out.println("Date time: " + now1.atStartOfDay());
        
        Instant instant = now1.atTime(LocalTime.of(0, 0, 0)).atZone(ZoneId.systemDefault()).toInstant();
        // change to java.util.Date
        System.out.println("instant: " + instant);
        System.out.println("Date: " + Date.from(instant));
        
    }
	
}
