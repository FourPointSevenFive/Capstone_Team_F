import { Button } from "@/components/ui/button";

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
        <Button variant="ghost" className="text-gray-500">
          see more
        </Button>
      </div>
      <div className="flex flex-col gap-4">{children}</div>
    </div>
  );
}
