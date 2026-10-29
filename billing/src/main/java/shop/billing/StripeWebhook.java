package shop.billing;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/** Receives Stripe events. Their payloads follow the API version of the webhook endpoint. */
public class StripeWebhook {
    private final ObjectMapper mapper = new ObjectMapper();
    private final Receipts receipts;

    public StripeWebhook(Receipts receipts) {
        this.receipts = receipts;
    }

    public void onEvent(String payload) throws Exception {
        JsonNode event = mapper.readTree(payload);
        if (!"payment_intent.succeeded".equals(event.path("type").asText())) {
            return;
        }
        JsonNode intent = event.path("data").path("object");
        long amount = intent.path("amount").asLong();
        String currency = intent.path("currency").asText();
        JsonNode firstCharge = intent.path("charges").path("data").get(0); // expect: stripe-2022-11-15
        receipts.send(firstCharge.path("receipt_url").asText(), amount, currency);
    }

    public interface Receipts {
        void send(String receiptUrl, long amount, String currency);
    }
}
