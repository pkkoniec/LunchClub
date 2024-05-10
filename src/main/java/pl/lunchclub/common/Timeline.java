package pl.lunchclub.common;

import java.util.*;
import java.util.stream.Collectors;

public class Timeline<P> {

    private final List<Span<P>> spans;

    public record Span<P>(
            DateRangeOpen dateRange,
            Optional<P> payload

    ) {
        public boolean isEqualAndAdjacent(Span<P> other) {
            return Objects.equals(
                    payload,
                    other.payload()
            ) && dateRange.isAdjacent(other.dateRange());
        }
    }

    private Timeline(
            List<Span<P>> spans
    ) {
        this.spans = spans.stream()
                .sorted(Comparator.comparing(
                        r -> r.dateRange()
                                .getFrom()
                                .orElse(null),
                        Comparator.nullsFirst(Comparator.naturalOrder())
                ))
                .toList();
    }

    public static <P> Either<Failure, Timeline<P>> of(
            List<Span<P>> spans
    ) {
        return Either.right(new Timeline<>(spans));
    }

    /**
     * changes payload for matched date ranges
     *
     * @return modified timeline
     */
    public Timeline<P> override(
            Span<P> override
    ) {
        return new Timeline<>(spans.stream()
                                      .flatMap(r -> r.dateRange()
                                              .split(override.dateRange())
                                              .stream()
                                              .map(s -> new Span<>(
                                                      s,
                                                      override.dateRange()
                                                              .encloses(s)
                                                              ? override.payload()
                                                              : r.payload()
                                              )))
                                      .toList()).mergeAdjacent();
    }

    private Timeline<P> mergeAdjacent() {
        if (spans.isEmpty() || spans.size() == 1) return this;

        List<Span<P>> merged = new ArrayList<>(spans.size());
        Deque<Span<P>> deque = new ArrayDeque<>(spans);

        do {
            Span<P> candidate = deque.poll();
            List<DateRangeOpen> ranges = new ArrayList<>();
            ranges.add(candidate.dateRange());
            while (deque.peek() != null && deque.peek()
                    .isEqualAndAdjacent(candidate)) {
                ranges.add(deque.poll()
                                   .dateRange());
            }
            merged.add(new Span<>(
                    DateRangeOpen.span(ranges),
                    candidate.payload()
            ));

        } while (!deque.isEmpty());

        return new Timeline<>(merged);
    }

    @Override
    public String toString() {
        return "Timeline{" + "ranges=\n" + spans.stream()
                .map(Record::toString)
                .collect(Collectors.joining("\n")) + "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timeline<?> timeline = (Timeline<?>) o;
        return Objects.equals(
                spans,
                timeline.spans
        );
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(spans);
    }
}
