"use client";

import { Input } from "@/components/ui/input";
import { IoSearch } from "react-icons/io5";
import { useRouter } from "next/navigation"; // next/navigation에서 useRouter 가져오기
import { useState } from "react";

export default function SearchBar() {
  const [keyword, setKeyword] = useState("");
  const router = useRouter();

  const onSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    router.push(`/search?keyword=${keyword}`);
  };

  return (
    <div className="relative">
      <IoSearch className="absolute left-3 top-2 text-gray-400" />
      <form onSubmit={onSubmit}>
        <Input
          placeholder="Search..."
          className="h-8 w-52 rounded-xl pl-8"
          onChange={(e) => setKeyword(e.target.value)}
        />
      </form>
    </div>
  );
}
