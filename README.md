1. Examples of Value Objects and tests:
* deep VO example - GrossAmount - wrapping Money VO and only exhibiting arithmetic operations that makes sense in invoice domain (e.g. gross + vat is not allowed, net + vat yields gross)
* public sealed interface InvoiceVat permits InvoiceVat.Exempt, InvoiceVat.Payer - sealed union usage used to precisely model domain using data oriented paradigm
  * json serialization of sealed union with _tag_ enum
* Timeline - with groovy test utilizing DSL ('A<0,10>B<11,20>' | 'C<5,15>' | 'A<0,4>C<5,15>B<16,20>')
