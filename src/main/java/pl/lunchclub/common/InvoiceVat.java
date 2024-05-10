package pl.lunchclub.common;

public sealed interface InvoiceVat permits InvoiceVat.Exempt, InvoiceVat.Payer {
    record Exempt() implements InvoiceVat {}

    record Payer(VatRate defaultVatRate) implements InvoiceVat {}
}
