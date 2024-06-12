package pl.lunchclub;

import pl.lunchclub.common.Either;
import pl.lunchclub.common.Failure;
import pl.lunchclub.common.Money;
import pl.lunchclub.invoice.*;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class UtilTest {
    protected static InvoiceVatLine anInvoiceVatLine(
            NetAmount net,
            InvoiceVat invoiceVat
    ) {
        Either<Failure, InvoiceVatLine> build = InvoiceVatLine.build(
                net,
                invoiceVat
        );
        assertThat(build).matches(Either::isRight);

        return build.getRightOrThrow();
    }

    protected static NetAmount aNet(
            String raw
    ) {
        Either<Failure, NetAmount> parse = NetAmount.parse(raw);
        assertThat(parse).matches(Either::isRight);
        return parse.getRightOrThrow();
    }

    protected static InvoiceVatLineJson parseInvoiceVatLine(
            String net,
            String gross,
            String vat,
            InvoiceVatJson invoiceVat
    ) {
        return new InvoiceVatLineJson(
                Money.parse(gross)
                        .getRightOrThrow()
                        .asJson(),
                Money.parse(net)
                        .getRightOrThrow()
                        .asJson(),
                Money.parse(vat)
                        .getRightOrThrow()
                        .asJson(),
                invoiceVat
        );
    }
}
