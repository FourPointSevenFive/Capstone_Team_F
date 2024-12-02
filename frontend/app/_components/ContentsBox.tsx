import Link from "next/link";

export default function ContentsBox({
  title,
  children,
}: {
  title: string;
  children: React.ReactNode;
}) {
  return (
    <div className="flex flex-col gap-4">
      <div className="flex items-center justify-between px-3">
        <p className="text-xl font-bold">{title}</p>
        <Link
          href={`seemore?category=${title.toUpperCase() == "K-POP" ? "K_POP" : title.toUpperCase()}`}
          className="text-gray-500"
        >
          see more
        </Link>
      </div>
      <div className="flex flex-col gap-4">{children}</div>
    </div>
  );
}
