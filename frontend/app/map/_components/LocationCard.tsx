"use client";

import Link from "next/link";
import {FaHeart} from 'react-icons/fa';

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
      <div className="verticle justify-left"></div>
        <div className="flex justify-between">
          <p className="text-lg font-bold pb-4 pl-1">{title}</p>
          <div>
            <FaHeart className="text-red-500 h-10 w-10 pr-4"/>
          </div>
        </div>
        <div className="w-full rounded-[12px] bg-neutral-200 p-4 min-h-[200px]">{photo}</div>
        <p className="w-full pt-4 p-2">{description}</p>
        <p className="w-full text-sm text-gray-500 p-2">{address}</p>
    </Link>
  );
}