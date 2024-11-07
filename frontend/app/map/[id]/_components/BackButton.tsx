"use client";

import { useRouter } from "next/navigation";
import { MdArrowBackIos } from "react-icons/md";

export default function BackButton() {
  const router = useRouter();
  return (
    <button type="button" onClick={() => router.back()} className="p-2">
      <MdArrowBackIos className="size-5 font-extrabold" />
    </button>
  );
}
