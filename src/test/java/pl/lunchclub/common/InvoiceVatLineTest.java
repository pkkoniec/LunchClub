package pl.lunchclub.common;

import org.junit.jupiter.api.Test;
import pl.lunchclub.UtilTest;

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
}
