"use client";

import { Button } from "@/components/ui/button";
import { useRouter } from "next/navigation";
export default function BackButton() {
  const router = useRouter();

  const handleBack = () => {
    if (typeof window !== "undefined") {
      router.back();
    }
  };

  return <Button onClick={handleBack}>back</Button>;
}
