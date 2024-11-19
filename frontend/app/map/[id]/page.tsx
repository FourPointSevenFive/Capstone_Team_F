import CustomBadge from "@/app/_components/CustomBadge";
import { CiHeart } from "react-icons/ci";
import Image from "next/image";
import kheart from "@/public/kheart_gray.png";
import { Button } from "@/components/ui/button";

export default async function Page({ params }: { params: { id: string } }) {
  const { id } = await params;
  return (
    <div className="flex flex-col items-center gap-5">
      <div className="self-end">
        <CustomBadge title="BTS" category="kpop" />
      </div>
      <div className="flex w-full justify-center self-start">
        <p className="flex-1 text-xl font-bold">TITLE</p>
        <div className="flex items-center gap-2">
          <p className="text-xs text-neutral-400">subtitle</p>
          <CiHeart className="size-7" />
        </div>
      </div>
      <div className="h-96 w-full border-2">photo</div>
      <p className="mb-6 w-[90%] self-start text-sm text-neutral-700">
        Jungkook’s favorite restaurant. They serve boiled pork and pork nabe. Be
        aware, there’s usually a wait!
      </p>
      <StampSection />
      <ProofShoots />
    </div>
  );
}

function StampSection() {
  return (
    <div className="border-1 flex h-32 w-80 flex-col items-center justify-center rounded-2xl border border-neutral-200">
      <Image src={kheart} alt="kheart" width={50} height={50} />
      <p className="text-center text-xs font-light text-neutral-400">
        You haven't visited this place yet! <br></br>Please enable GPS and
        collect your stamp!
      </p>
    </div>
  );
}

function ProofShoots() {
  return (
    <div className="flex w-full flex-col gap-5 p-10">
      <div className="flex items-center justify-between">
        <h1 className="font-semibold">Proof Shoots</h1>
        <Button className="h-5 w-12 rounded-3xl bg-blue-300 text-xs hover:bg-blue-400">
          + Add
        </Button>
      </div>
      <div className="grid grid-cols-3 gap-1">
        {Array.from({ length: 10 }).map((_, index) => (
          <div
            key={index}
            className="flex aspect-square items-center justify-center bg-neutral-200">
            photo {index + 1}
          </div>
        ))}
      </div>
    </div>
  );
}
