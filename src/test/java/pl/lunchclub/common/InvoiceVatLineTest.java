package pl.lunchclub.common;

import org.junit.jupiter.api.Test;
import pl.lunchclub.UtilTest;
import pl.lunchclub.invoice.*;

import static org.assertj.core.api.Assertions.assertThat;

class InvoiceVatLineTest extends UtilTest {

    @Test
    void build() {
        InvoiceVatLine invoiceVatLine = anInvoiceVatLine(
                aNet("10"),
                new InvoiceVat.Exempt()
        );

        assertThat(invoiceVatLine.getVat()).isEqualTo(VatAmount.ZERO);
    }

    @Test
    void jsonSerialization() {
        {
            InvoiceVatLine invoiceVatLine = anInvoiceVatLine(
                    aNet("10"),
                    new InvoiceVat.Exempt()
            );

            assertThat(invoiceVatLine.asJson()).isEqualTo(parseInvoiceVatLine(
                    "10",
                    "10",
                    "0",
                    new InvoiceVatJson(
                            InvoiceVatJson.InvoiceVatTag.EXEMPT,
                            null
                    )
            ));
        }
        {
            InvoiceVatLine invoiceVatLine = anInvoiceVatLine(
                    aNet("10"),
                    new InvoiceVat.Payer(VatRate.parse(20))
            );

            assertThat(invoiceVatLine.asJson()).isEqualTo(parseInvoiceVatLine(
                    "10",
                    "12",
                    "2",
                    new InvoiceVatJson(
                            InvoiceVatJson.InvoiceVatTag.PAYER,
                            new InvoiceVatJson.Payer(new VatRateJson("20"))
                    )
            ));
        }
    }
}
