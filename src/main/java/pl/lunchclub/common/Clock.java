package pl.lunchclub.common;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public interface Clock {
    static Long epoch() {
        return LocalDateTime.now()
                .toEpochSecond(ZoneOffset.UTC);
    }
}
