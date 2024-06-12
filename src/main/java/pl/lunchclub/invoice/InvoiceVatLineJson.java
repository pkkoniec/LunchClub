package pl.lunchclub.invoice;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.lunchclub.common.MoneyJson;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceVatLineJson {
    @NotNull
    private MoneyJson gross;
    @NotNull
    private MoneyJson net;
    @NotNull
    private MoneyJson vat;
    @NotNull
    @Valid
    private InvoiceVatJson invoiceVat;
}
