"use client";

import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { signIn } from "next-auth/react";

export default function SignInPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const onSubmit = async (event: React.FormEvent): Promise<void> => {
    event.preventDefault();

    try {
      const res = await signIn("credentials", {
        redirect: false,
        username,
        password,
      });

      console.log("res: ", res);
      if (res && !res.error) {
        console.log("Signed in: ", res);
        window.location.href = "/";
      } else {
        setError("Failed to sign in. Please check your credentials.");
      }
    } catch (error) {
      //console.error("Failed to sign in: ", error);
      setError("An unexpected error occurred. Please try again.: " + error);
    }
  };

  return (
    <div className="flex flex-col gap-8 px-8">
      <form onSubmit={onSubmit} className="flex flex-col gap-4">
        <Input
          placeholder="Email"
          type="text"
          className="h-12 bg-slate-100"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <Input
          placeholder="Password"
          type="password"
          className="h-12 bg-slate-100"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <Button
          type="submit"
          className="h-12 bg-blue-500 text-white hover:bg-blue-300"
        >
          Sign In
        </Button>
        <Button
          variant="ghost"
          onClick={() => (window.location.href = "/signup")}
        >
          Create An Account
        </Button>
        {error && <p className="text-red-500">{error}</p>}
      </form>
    </div>
  );
}
