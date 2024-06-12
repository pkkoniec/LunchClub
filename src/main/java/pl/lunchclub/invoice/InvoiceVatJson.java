package pl.lunchclub.invoice;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceVatJson {
    @NotNull
    private InvoiceVatTag tag;
    @Valid
    private Payer payer;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Payer {
        @NotNull
        private VatRateJson vatRate;
    }

    public enum InvoiceVatTag {
        EXEMPT, PAYER
    }
}
