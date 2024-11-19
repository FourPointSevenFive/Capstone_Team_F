"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import { IoIosArrowBack } from "react-icons/io";

export default function Layout({ children }: { children: React.ReactNode }) {
  const path = usePathname();
  return (
    <div className="flex flex-col gap-10">
      <div className="flex items-center justify-between">
        <Link href="./landing">
          <IoIosArrowBack className="size-8 cursor-pointer" />
        </Link>

        <div>{path == "/login" ? "Sign In" : "Sign Up"}</div>
      </div>
      {children}
    </div>
  );
}
