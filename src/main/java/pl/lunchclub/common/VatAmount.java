package pl.lunchclub.common;

import java.util.Objects;

public class VatAmount {
    public static final VatAmount ZERO = new VatAmount(Money.ZERO);

    private final Money value;

    private VatAmount(Money value) {this.value = value;}

    static VatAmount calc(
            GrossAmount gross,
            NetAmount net
    ) {
        return new VatAmount(gross.unbox()
                                     .subtract(net.unbox()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VatAmount vatAmount = (VatAmount) o;
        return Objects.equals(
                value,
                vatAmount.value
        );
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "VatAmount{" + value + '}';
    }
}
