"use client";

import React from "react";
import { SessionProvider } from "next-auth/react";

interface Props {
  children: React.ReactNode;
}

export default function AuthProvider({ children }: Props): JSX.Element {
  return <SessionProvider>{children}</SessionProvider>;
}
