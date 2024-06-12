package pl.lunchclub.common;

import java.math.BigDecimal;

public class PercentInteger {
    private final Integer value;

    private PercentInteger(Integer value) {this.value = value;}

    public static PercentInteger parse(int raw) {
        return new PercentInteger(raw);
    }

    public Money of(Money money) {
        return money.multiply(asDecimal());
    }

    public BigDecimal asDecimal() {
        return new BigDecimal(value).scaleByPowerOfTen(-2);
    }

    public Integer unbox() {
        return value;
    }
}
