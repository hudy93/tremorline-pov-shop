# Provider files for the replay

Stand-ins for the Stripe, GitHub and Slack API descriptions and one Stripe response. They start in the state
before each historical change; for the replay they are replaced with the state after it, at the same URLs, so
Tremorline sees the change happen. Monitor them through their raw URLs, e.g.
`https://raw.githubusercontent.com/hudy93/tremorline-pov-shop/main/provider-files/github.yaml`.

| File | Add in Tremorline as | Change replayed |
|---|---|---|
| `stripe-payment-intents.yaml` | API spec | Stripe 2022-11-15: `PaymentIntent.charges` removed |
| `stripe-customer.json` | Endpoint | Stripe 2020-08-27: `Customer.sources` no longer included |
| `github.yaml` | API spec | GitHub: OAuth Authorizations API removed |
| `slack.yaml` | API spec | Slack: `channels.*` methods retired |

Tremorline's code search only reads source files, so these YAML and JSON files are never matched as code.
The originals, before and after, are in `src/test/resources/proof-of-value/changes/` of the Tremorline repository.
