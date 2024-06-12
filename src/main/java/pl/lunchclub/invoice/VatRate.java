package pl.lunchclub.invoice;

import pl.lunchclub.common.Money;
import pl.lunchclub.common.PercentInteger;

public class VatRate {
    private final PercentInteger value;

    private VatRate(PercentInteger value) {this.value = value;}

    public static VatRate parse(int raw) {
        return new VatRate(PercentInteger.parse(raw));
    }

    public Money of(NetAmount net) {
        return null;
    }

    public PercentInteger unbox() {
        return value;
    }

    public VatRateJson asJson() {
        return new VatRateJson(value.unbox()
                                       .toString());
    }
}
