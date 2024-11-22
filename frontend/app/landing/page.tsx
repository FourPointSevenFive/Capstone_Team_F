import Image from "next/image";
import logoImage from "@/public/icons/logo.png";
import { Button } from "@/components/ui/button";
import Link from "next/link";
export default function LandingPage() {
  return (
    <div className="mt-20 flex flex-col items-center justify-center">
      <Image src={logoImage} alt="logo" width={100} height={100} />
      <p className="p-3 text-5xl">HallyuGo </p>
      <p className="text-lg">Map App for K-culture Lover</p>
      <div className="mt-20 flex w-60 flex-col gap-2">
        <Link href="./login">
          <Button className="w-full bg-slate-400">Sign In</Button>
        </Link>
        <Link href="./signup">
          <Button className="w-full bg-slate-400">Sign Up</Button>
        </Link>
        <Link href="./">
          <Button className="mt-8 w-full" variant="ghost">
            Continue As A Guest
          </Button>
        </Link>
      </div>
    </div>
  );
}
