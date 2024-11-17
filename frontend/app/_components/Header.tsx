"use client";
import Link from "next/link";
import { LuHome } from "react-icons/lu";
import { GrLocation } from "react-icons/gr";
import { FaUser } from "react-icons/fa";
import UnderBar from "./UnderBar";
import { usePathname } from "next/navigation";

export default function Header() {
  const currentPath = usePathname();

  return (
    <div className="flex w-full items-center pb-3 text-base font-medium">
      <div className="flex flex-1 gap-5">
        <Link href="./" className="flex w-20 flex-col items-center">
          <div className="flex items-center justify-center gap-1 pr-1">
            <LuHome />
            HOME
          </div>
          {currentPath === "/" && <UnderBar />}
        </Link>

        <div className="flex flex-1 gap-2">
          <Link href="./map" className="flex w-20 flex-col items-center">
            <div className="flex items-center justify-center gap-1 pr-1">
              <GrLocation />
              MAP
            </div>
            {currentPath === "/map" && <UnderBar />}
          </Link>
        </div>
        <Link href="./my" className="flex flex-col items-center gap-1">
          <FaUser size={20} />
          {currentPath === "/my" && <UnderBar isMy={true} />}
        </Link>
      </div>
    </div>
  );
}
