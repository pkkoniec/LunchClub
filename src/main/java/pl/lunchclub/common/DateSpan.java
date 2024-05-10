package pl.lunchclub.common;

public sealed interface DateSpan permits DateSpan.Range, DateSpan.Empty {

    record Range(DateRangeOpen dateRangeOpen) implements DateSpan {}

    record Empty() implements DateSpan {}
}
