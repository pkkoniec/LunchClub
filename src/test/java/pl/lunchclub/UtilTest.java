package pl.lunchclub;

import pl.lunchclub.common.*;

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
}
