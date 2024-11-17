import Image from "next/image";

export default function Stamp({
  title,
  date,
}: {
  title: string;
  date: string;
}) {
  return (
    <div className="w-25 flex h-fit flex-col rounded-[10px] border-2 shadow-md">
      <div className="flex justify-center pt-2">
        <Image src={"/icons/stamp.png"} alt="stampImage" width={50} height={50}>
          {}
        </Image>
      </div>
      <div className="flex justify-center pt-1 text-gray-600">{title}</div>
      <div className="flex justify-center pb-3 text-sm text-gray-600">{date}</div>
    </div>
  );
}
