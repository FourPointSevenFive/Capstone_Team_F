import { Separator } from "@/components/ui/separator";
import { cn } from "@/lib/utils";

export default function UnderBar({ isMy = false }: { isMy?: boolean }) {
  return (
    <Separator
      className={cn(
        "h-[3px] w-[72px] bg-gradient-to-r from-red-500 to-blue-500",
        isMy && "w-[30px]",
      )}
    />
  );
}
