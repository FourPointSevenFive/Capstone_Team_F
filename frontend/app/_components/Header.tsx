import Link from "next/link";

export default function Header() {
  return (
    <div className="flex">
      <div className="flex flex-1 gap-2">
        <Link href="./">HOME</Link>
        <Link href="./map">MAP</Link>
      </div>
      <Link href="./my">mypage</Link>
    </div>
  );
}
