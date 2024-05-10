package pl.lunchclub.common;

import java.math.BigDecimal;

public class PercentInteger {
    private final Integer value;

    private PercentInteger(Integer value) {this.value = value;}

    public Money of(Money money) {
        return money.multiply(asDecimal());
    }

    public BigDecimal asDecimal() {
        return new BigDecimal(value).scaleByPowerOfTen(-2);
    }
}
