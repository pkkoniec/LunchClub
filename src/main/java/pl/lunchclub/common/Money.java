package pl.lunchclub.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Money implements Comparable<Money>, Serializable {
    private final BigDecimal amount;

    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    private static final int SCALE = 2;

    public static final Money ZERO = new Money(BigDecimal.ZERO.setScale(
            SCALE,
            ROUNDING_MODE
    ));

    private Money(BigDecimal amount) {
        this.amount = amount.setScale(
                SCALE,
                ROUNDING_MODE
        );
    }

    public static Either<Failure, Money> parse(
            String raw
    ) {
        try {
            return Either.right(new Money(new BigDecimal(raw)));
        } catch (Exception e) {
            return Either.failure("MONEY_CANNOT_PARSE");
        }
    }

    @Override
    public int compareTo(Money o) {
        return amount.compareTo(o.amount);
    }

    public Money subtract(Money subtrahend) {
        return new Money(amount.subtract(subtrahend.amount));
    }

    public Money multiply(BigDecimal decimal) {
        return new Money(amount.multiply(decimal));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(
                amount,
                money.amount
        );
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }

    @Override
    public String toString() {
        return "Money{" + amount + '}';
    }
}
