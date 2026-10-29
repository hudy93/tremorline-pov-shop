package shop.billing;

import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;

/** Confirms that an order was paid. Uses stripe-java with the API version pinned on the Stripe account. */
public class PaymentService {

    public boolean isPaid(String paymentIntentId) throws Exception {
        PaymentIntent intent = PaymentIntent.retrieve(paymentIntentId);
        if (!"succeeded".equals(intent.getStatus())) {
            return false;
        }
        Charge charge = intent.getCharges().getData().get(0); // expect: stripe-2022-11-15
        return Boolean.TRUE.equals(charge.getPaid());
    }

    public long amountInCents(String paymentIntentId) throws Exception {
        return PaymentIntent.retrieve(paymentIntentId).getAmount();
    }
}
