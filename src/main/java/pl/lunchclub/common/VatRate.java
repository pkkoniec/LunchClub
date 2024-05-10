package pl.lunchclub.common;

public class VatRate {
    private final PercentInteger value;

    private VatRate(PercentInteger value) {this.value = value;}

    public Money of(NetAmount net) {
        return null;
    }

    public PercentInteger unbox() {
        return value;
    }
}
