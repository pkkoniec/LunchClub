package pl.lunchclub.meet;

import pl.lunchclub.common.DateRangeOpen;

import java.time.LocalDateTime;

public sealed interface MeetingTime permits MeetingTime.Point, MeetingTime.DateRange {
    record Point(
            LocalDateTime dateTime
    ) implements MeetingTime {
    }

    record DateRange(
            DateRangeOpen dateRange
    ) implements MeetingTime {
    }
}
