package shop.shipping;

import java.math.BigDecimal;
import java.util.List;

/** A carrier's quote for one parcel. Has nothing to do with Stripe. */
public class ShippingQuote {
    private String carrier;
    private String currency;
    private List<Fee> charges;

    public BigDecimal total() {
        return charges.stream().map(Fee::amount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Fee> getCharges() {
        return charges;
    }

    public record Fee(String label, BigDecimal amount) {
    }
}
