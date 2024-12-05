import Image from "next/image";

export default function Stamp({
  title,
  date,
  category,
}: {
  title: string;
  date: string;
  category: "K_POP" | "DRAMA" | "MOVIE" | "NOVEL";
}) {
  return (
    <div className="w-25 flex h-fit flex-col rounded-[10px] border-2 shadow-md">
      <div className="flex justify-center pt-2">
        <Image
          src={`/icons/proof_${category}.png`}
          alt="stampImage"
          width={40}
          height={40}
        />
      </div>
      <div className="overflow-hidden text-ellipsis whitespace-nowrap pt-1 text-sm text-gray-600">
        {title}
      </div>

      <div className="flex justify-center pb-3 text-[10px] text-gray-600">
        {new Date(date).toString().slice(0, 15)}
      </div>
    </div>
  );
}
