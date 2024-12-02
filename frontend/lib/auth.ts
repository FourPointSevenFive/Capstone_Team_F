import { getServerSession } from "next-auth";

import { getSession } from "next-auth/react";
import { options } from "./auth/options";

export const auth = async () =>
  typeof window !== "undefined"
    ? await getSession()
    : await getServerSession(options);
