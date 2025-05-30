package pl.lunchclub.invoice;

public sealed interface InvoiceVat permits InvoiceVat.Exempt, InvoiceVat.Payer {
    default InvoiceVatJson asJson() {
        return switch (this) {
            case Exempt _ -> new InvoiceVatJson(
                    InvoiceVatJson.InvoiceVatTag.EXEMPT,
                    null
            );
            case Payer payer -> new InvoiceVatJson(
                    InvoiceVatJson.InvoiceVatTag.PAYER,
                    new InvoiceVatJson.Payer(payer.defaultVatRate()
                                                     .asJson())
            );
        };
    }

    record Exempt() implements InvoiceVat {}

    record Payer(VatRate defaultVatRate) implements InvoiceVat {}
}
