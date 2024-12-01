"use client";

import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { baseUrl } from "@/lib/constants";

export default function SignupPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [nickname, setNickname] = useState("");
  const [error, setError] = useState("");

  const onSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const res = await fetch(`${baseUrl}/api/v1/auth/signin`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password, nickname }),
      });
      if (res.ok) {
        alert("Sign up successful!");
        window.location.href = "./login";
      } else {
        setError("Failed to sign up: " + res.statusText);
      }
    } catch (error) {
      setError("Failed to sign up: " + error);
    }
  };

  return (
    <form onSubmit={onSubmit}>
      <div className="flex flex-col gap-4 px-8">
        <Input
          name="nickname"
          placeholder="Nickname"
          className="h-12 bg-slate-100"
          value={nickname}
          onChange={(e) => setNickname(e.target.value)}
        />
        <Input
          name="username"
          placeholder="Id"
          type="text"
          className="h-12 bg-slate-100"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <Input
          name="password"
          placeholder="Password"
          type="password"
          className="h-12 bg-slate-100"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <Button className="h-12 bg-blue-500 hover:bg-blue-300" type="submit">
          Sign Up
        </Button>
        {error && <p className="text-red-500">{error}</p>}
      </div>
    </form>
  );
}
