package pl.lunchclub.meet;

import pl.lunchclub.common.DateRangeOpen;

import java.time.LocalDateTime;

public sealed interface MeetTime permits MeetTime.Point, MeetTime.DateRange {
    record Point(
            LocalDateTime dateTime
    ) implements MeetTime {
    }

    record DateRange(
            DateRangeOpen dateRange
    ) implements MeetTime {
    }
}
