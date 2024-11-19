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
    <Link href={"/map/2"}>
      <div className="verticle justify-left"></div>
      <div className="flex justify-between">
        <p className="pl-1 pr-1 text-lg font-bold">{title}</p>
        <div>
          <FaHeart className="h-10 w-10 pl-2 pr-2 pb-2 aspect-square text-red-500"/>
        </div>
      </div>
      <div className="min-h-[200px] w-full rounded-[12px] pl-2 bg-neutral-200">
        {photo}
      </div>
      <p className="w-full pl-1 pr-1 pt-2.5">{description}</p>
      <p className="w-full pl-1 pr-1 pt-1 text-sm text-gray-500">{address}</p>
    </Link>
  );
}
