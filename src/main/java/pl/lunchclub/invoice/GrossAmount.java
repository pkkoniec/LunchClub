package pl.lunchclub.invoice;

import pl.lunchclub.common.Money;
import pl.lunchclub.common.MoneyJson;

public class GrossAmount {
    private final Money value;

    private GrossAmount(Money value) {this.value = value;}

    static GrossAmount calc(
            NetAmount net,
            InvoiceVat invoiceVat
    ) {
        return new GrossAmount(switch (invoiceVat) {
            case InvoiceVat.Exempt _ -> net.unbox();
            case InvoiceVat.Payer payer -> Money.sum(
                    net.unbox(),
                    payer.defaultVatRate()
                            .unbox()
                            .of(net.unbox())
            );
        });
    }

    public static GrossAmount parse(MoneyJson gross) {
        return null;
    }

    public Money unbox() {
        return value;
    }

    public MoneyJson asJson() {
        return value.asJson();
    }
}
