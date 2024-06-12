package pl.lunchclub.invoice;

import pl.lunchclub.common.Either;
import pl.lunchclub.common.Failure;
import pl.lunchclub.common.Money;
import pl.lunchclub.common.MoneyJson;

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

    public MoneyJson asJson() {
        return value.asJson();
    }
}
