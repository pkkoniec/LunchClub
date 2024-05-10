package pl.lunchclub.common;

import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateRangeOpen {
    @Getter
    private final Optional<LocalDate> from;
    @Getter
    private final Optional<LocalDate> to;

    public static final DateRangeOpen INFINITE = new DateRangeOpen(Range.all());

    private DateRangeOpen(Range<Long> range) {
        this.from = range.hasLowerBound()
                ? Optional.of(LocalDate.ofEpochDay(range.lowerEndpoint() + switch (range.lowerBoundType()) {
            case OPEN -> 1;
            case CLOSED -> 0;
        }))
                : Optional.empty();
        this.to = range.hasUpperBound()
                ? Optional.of(LocalDate.ofEpochDay(range.upperEndpoint() + switch (range.upperBoundType()) {
            case OPEN -> -1;
            case CLOSED -> 0;
        }))
                : Optional.empty();
    }

    public static DateRangeOpen ofEpoch(
            Long start,
            Long end
    ) {
        return new DateRangeOpen(Range.closed(
                start,
                end
        ));
    }

    public static DateRangeOpen span(List<DateRangeOpen> dateRanges) {
        if (dateRanges.size() == 1) return dateRanges.getFirst();

        RangeSet<Long> set = TreeRangeSet.create();
        dateRanges.forEach(dr -> set.add(dr.asRange()));
        return new DateRangeOpen(set.span());
    }

    public Set<DateRangeOpen> split(
            DateRangeOpen other
    ) {
        return Stream.concat(
                        switch (intersection(other)) {
                            case DateSpan.Range r -> Stream.of(r.dateRangeOpen());
                            case DateSpan.Empty _ -> Stream.<DateRangeOpen>empty();
                        },
                        difference(other).stream()
                )
                .collect(Collectors.toSet());
    }

    public DateSpan intersection(DateRangeOpen other) {
        if (asRange().isConnected(other.asRange()))
            return new DateSpan.Range(new DateRangeOpen(asRange().intersection(other.asRange())));
        return new DateSpan.Empty();
    }

    private Range<Long> range;

    private Range<Long> asRange() {
        if (range == null) range = buildRange();

        return range;
    }

    private Range<Long> buildRange() {
        return from.map(f -> to.map(t -> Range.closed(
                                f.toEpochDay(),
                                t.toEpochDay()
                        ))
                        .orElse(Range.atLeast(f.toEpochDay())))
                .orElse(to.map(t -> Range.atMost(t.toEpochDay()))
                                .orElse(Range.all()));
    }

    public Set<DateRangeOpen> difference(DateRangeOpen other) {
        RangeSet<Long> set = TreeRangeSet.create();
        set.add(asRange().canonical(DiscreteDomain.longs()));
        set.remove(other.asRange()
                           .canonical(DiscreteDomain.longs()));
        return set.asRanges()
                .stream()
                .map(DateRangeOpen::new)
                .collect(Collectors.toSet());
    }

    public boolean encloses(DateRangeOpen other) {
        return asRange().encloses(other.asRange());
    }

    public DateRangeOpenJson asJson() {
        return new DateRangeOpenJson(
                getFrom().orElse(null),
                getFrom().orElse(null)
        );
    }

    @Override
    public String toString() {
        return "DateRangeOpen{" + "from=" + from.orElse(null) + ", to=" + to.orElse(null) + '}';
    }

    public boolean isAdjacent(DateRangeOpen other) {
        return !asRange().isConnected(other.asRange()) && asRange().gap(other.asRange())
                .canonical(DiscreteDomain.longs())
                .isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateRangeOpen that = (DateRangeOpen) o;
        return Objects.equals(
                from,
                that.from
        ) && Objects.equals(
                to,
                that.to
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                from,
                to
        );
    }
}
