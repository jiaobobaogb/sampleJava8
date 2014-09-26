package com.britesnow.j8.DevTestBao;

import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Created by Bao on 2014/9/26.
 */
public class DateTest {
    @Test
    public void clockTest(){

        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println("millis:"+millis);
        Instant instant = clock.instant();
        Date dateTime = Date.from(instant);
        System.out.println(dateTime);
    }
    @Test
    public void zoneTest(){

        System.out.println(ZoneId.getAvailableZoneIds());
        ZoneId zone1 = ZoneId.of("Europe/London");
        ZoneId zone2 = ZoneId.of("Asia/Hong_Kong");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());
    }
    @Test
    public void localTimeTest(){
        ZoneId zone1 = ZoneId.of("Europe/London");
        ZoneId zone2 = ZoneId.of("Asia/Chongqing");
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        System.out.println(now1.isBefore(now2));  // false
        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
        System.out.println("hoursBetween : "+hoursBetween);       // -3
        System.out.println("minutesBetween : "+minutesBetween);     // -239
    }
}
