package pl.lunchclub.common;

public class NetAmount {
    private final Money value;

    private NetAmount(Money value) {this.value = value;}

    public static Either<Failure, NetAmount> parse(String raw) {
        return Money.parse(raw)
                .mapRight(NetAmount::new);
    }

    public Money unbox() {
        return value;
    }
}
