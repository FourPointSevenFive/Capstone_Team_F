"use client";

import Link from "next/link";
import { FaHeart } from "react-icons/fa";

export default function LocationCard({
  title,
  photo,
  description,
  address,
}: {
  title: string;
  photo?: string;
  description: string;
  address: string;
}) {
  return (
    <Link href={"/"} passHref>

      <div className="flex justify-between">
        <p className="pl-1 text-lg font-bold">{title}</p>

        <div>
          <FaHeart className="h-10 w-10 pr-4 text-red-500" />
        </div>
      </div>
      <div className="min-h-[200px] w-full rounded-[12px] bg-neutral-200 p-4">
        {photo}
      </div>
      <p className="w-full p-2 pt-4">{description}</p>
      <p className="w-full p-2 text-sm text-gray-500">{address}</p>
    </Link>
  );
}
