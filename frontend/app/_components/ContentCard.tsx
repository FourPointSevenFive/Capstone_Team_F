import Link from "next/link";

export default function ContentCard({
  title,
  description,
  image,
  link,
  hashtags,
}: {
  title: string;
  description: string;
  image?: string;
  link?: string;
  hashtags?: string;
}) {
  return (
    <Link href={"./"}>
      <div className="flex justify-between rounded-2xl border-2 border-neutral-100 p-4">
        <div className="flex w-56 flex-col gap-4">
          <p className="text-sm font-bold text-gray-700">{title}</p>
          <p className="text-xs text-slate-500">{description}</p>
          <p className="text-xs text-blue-500">{hashtags}</p>
        </div>
        <div className="size-24 rounded-[12px] bg-neutral-200">{image}</div>
      </div>
    </Link>
  );
}
