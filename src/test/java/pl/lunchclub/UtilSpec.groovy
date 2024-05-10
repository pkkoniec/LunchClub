package pl.lunchclub

import pl.lunchclub.common.DateRangeOpen
import pl.lunchclub.common.Timeline
import spock.lang.Specification

abstract class UtilSpec extends Specification {
    Timeline<String> parseTimelines(String raw) {
        if (raw == null || raw.isBlank()) return Timeline.<String> of([]).rightOrThrow

        def split = raw.replaceAll(">", ">>").split('>')

        Timeline.of(split.findAll { s -> !s.isBlank() }.collect({ s -> parseRange(s) })).rightOrThrow
    }

    Timeline.Span<String> parseRange(String raw) {
        def split = raw.replaceAll(">", "").split("<")
        def dateRange = split[1].split(",")
        new Timeline.Span(DateRangeOpen.ofEpoch(Long.valueOf(dateRange[0]), Long.valueOf(dateRange[1])), Optional.of(split[0]))
    }
}
