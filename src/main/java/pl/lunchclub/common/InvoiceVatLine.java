package pl.lunchclub.common;

import lombok.Getter;

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
}
