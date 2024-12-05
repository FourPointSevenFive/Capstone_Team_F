import { Badge } from "@/components/ui/badge";
import { cn } from "@/lib/utils";

const borderColors = {
  K_POP: "border-violet-400",
  DRAMA: "border-yellow-400",
  NOVEL: "border-rose-400",
  MOVIE: "border-sky-400",
};

type categoryType = "K_POP" | "DRAMA" | "NOVEL" | "MOVIE";

export default function CustomBadge({
  title,
  category,
}: {
  title: string;
  category: categoryType;
}) {
  return (
    <Badge
      variant="outline"
      className={cn(
        "h-6 overflow-hidden whitespace-nowrap rounded-3xl",
        borderColors[category],
      )}
    >
      {title}
    </Badge>
  );
}
