# Proof-of-value test repository

A small shop back end with three external integrations, used by `ProofOfValueTest` and meant to be pushed as a
GitHub repository of its own for the end-to-end run (see `docs/proof-of-value.md`):

| Folder | Integration | Language |
|---|---|---|
| `billing/` | Stripe (payments, saved cards, webhooks) | Java, stripe-java |
| `devtools/` | GitHub REST API (CLI login, repository list) | TypeScript |
| `notify/` | Slack Web API (alerts to channels) | Python |

Lines that a historical API change breaks end with a marker comment naming the change. Every other match
counts as a false alarm. Some lines are deliberate traps: the same field or path name in code that has
nothing to do with the provider.

The code is written to be read, not run: it has no build files and its dependencies are not installed.
