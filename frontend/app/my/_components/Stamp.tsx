import Image from 'next/image';

export default function Stamp({
  title,
  date,
}: {
  title: string;
  date: string;
}) {
  return (
    <div className="flex flex-col border-2 rounded-[20px] shadow-md w-48 h-48">
      <div className="flex justify-center pt-5">
        <Image src={"/icons/stamp.png"} alt='stampImage' width={80} height={80}>{}</Image>
      </div>
      <div className="flex justify-center pt-3 text-gray-600">{title}</div>
      <div className="flex justify-center pb-3 text-sm text-gray-600">{date}</div>
    </div>
  );
}
