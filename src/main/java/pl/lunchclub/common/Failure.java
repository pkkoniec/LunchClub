package pl.lunchclub.common;

public record Failure(
        String reason,
        Long epoch
) {}
