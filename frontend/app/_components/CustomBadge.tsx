import { Badge } from "@/components/ui/badge";
import { cn } from "@/lib/utils";

const borderColors = {
  kpop: "border-violet-400",
  drama: "border-yellow-400",
  novel: "border-rose-400",
  movie: "border-sky-400",
};

type categoryType = "kpop" | "drama" | "novel" | "movie";

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
      className={cn("h-6 rounded-3xl", borderColors[category])}
    >
      {title}
    </Badge>
  );
}
