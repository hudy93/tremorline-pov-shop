package shop.billing;

import com.stripe.model.Card;
import com.stripe.model.Customer;
import com.stripe.model.PaymentSource;

import java.util.ArrayList;
import java.util.List;

/** The saved cards shown on the checkout page. */
public class CustomerCards {

    public List<String> cardLabels(String customerId) throws Exception {
        Customer customer = Customer.retrieve(customerId);
        List<String> labels = new ArrayList<>();
        if (Boolean.TRUE.equals(customer.getDelinquent())) {
            return labels;
        }
        for (PaymentSource source : customer.getSources().getData()) { // expect: stripe-2020-08-27
            Card card = (Card) source;
            labels.add(card.getBrand() + " •••• " + card.getLast4());
        }
        return labels;
    }

    public String receiptAddress(String customerId) throws Exception {
        return Customer.retrieve(customerId).getEmail();
    }
}
