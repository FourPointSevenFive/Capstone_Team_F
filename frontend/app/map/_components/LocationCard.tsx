"use client";

import Link from "next/link";
import { FaHeart } from "react-icons/fa";
export default function LocationCard({
  title,
  photo,
  description,
}: {
  title: string;
  photo?: string;
  description: string;
}) {
  return (
    <Link href={"/map/2"} className="w-full">
      <div className="verticle justify-left"></div>
      <div className="mb-4 flex items-center justify-between">
        <div className="w-64 text-sm font-bold">{title}</div>

        <FaHeart className="size-8 text-red-500" />
      </div>
      <div className="min-h-[200px] w-full rounded-[12px] bg-neutral-200 pl-2">
        {photo}
      </div>
      <p className="w-full pl-1 pr-1 pt-2.5 text-xs">{description}</p>
    </Link>
  );
}
