package com.egin.hotelreservation.common.helper.clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ClockHelper {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

}
