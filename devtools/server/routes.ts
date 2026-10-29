// The shop's own admin API: card payment authorizations held by the shop, not GitHub's.
import { Router } from "express";
import { listGrants, voidGrant } from "./grants";

export const router = Router();
router.get("/admin/authorizations", listGrants);
router.delete("/admin/authorizations/:id", voidGrant);
