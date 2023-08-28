package com.egin.hotelreservation.common.helper.clock;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

@Component
public class ClockConfig {

    private final Clock clock;

    public ClockConfig(Clock clock) {
        this.clock = clock;
    }

    public LocalDateTime getLocalDateTimeNow() {
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(
                instant,
                Clock.systemDefaultZone().getZone()
        );
    }
}
