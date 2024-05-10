package pl.lunchclub.common;

public class GrossAmount {
    private final Money value;

    private GrossAmount(Money value) {this.value = value;}

    static GrossAmount calc(
            NetAmount net,
            InvoiceVat invoiceVat
    ) {
        return new GrossAmount(switch (invoiceVat) {
            case InvoiceVat.Exempt _ -> net.unbox();
            case InvoiceVat.Payer payer -> payer.defaultVatRate()
                    .unbox()
                    .of(net.unbox());
        });
    }

    public Money unbox() {
        return value;
    }
}
