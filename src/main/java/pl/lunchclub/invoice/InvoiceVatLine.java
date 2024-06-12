package pl.lunchclub.invoice;

import lombok.Getter;
import pl.lunchclub.common.Either;
import pl.lunchclub.common.Failure;

@Getter
public class InvoiceVatLine {
    private final GrossAmount gross;
    private final NetAmount net;
    private final VatAmount vat;
    private final InvoiceVat invoiceVat;

    private InvoiceVatLine(
            GrossAmount gross,
            NetAmount net,
            VatAmount vat,
            InvoiceVat invoiceVat
    ) {
        this.gross = gross;
        this.net = net;
        this.vat = vat;
        this.invoiceVat = invoiceVat;
    }

    public static Either<Failure, InvoiceVatLine> build(
            NetAmount net,
            InvoiceVat invoiceVat
    ) {
        GrossAmount gross = GrossAmount.calc(
                net,
                invoiceVat
        );

        return Either.right(new InvoiceVatLine(
                gross,
                net,
                VatAmount.calc(
                        gross,
                        net
                ),
                invoiceVat
        ));
    }

    public InvoiceVatLineJson asJson() {
        return new InvoiceVatLineJson(
                gross.asJson(),
                net.asJson(),
                vat.asJson(),
                invoiceVat.asJson()
        );
    }
}
