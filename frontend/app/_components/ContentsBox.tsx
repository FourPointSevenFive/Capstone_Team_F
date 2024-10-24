import { Button } from "@/components/ui/button";
import ContentCard from "./ContentCard";

export default function ContentsBox({ title }: { title: string }) {
  return (
    <div className="flex flex-col gap-4">
      <div className="flex items-center justify-between px-3">
        <p className="text-xl font-bold">{title}</p>
        <Button variant="ghost" className="text-gray-500">
          see more
        </Button>
      </div>
      <div className="flex flex-col gap-4">
        <ContentCard
          title="BTS-Beyond the Scene"
          description="Follow the footsteps of BTS,
the pioneers of the global K-pop phenomenon."
          hashtags="#Seoul #Busan #BT21"
          link="/bts"
        />
        <ContentCard
          title="BlackPick - in your area"
          description="Explore BLACKPINK's rise to worldwide stardom and their iconic moments."
          hashtags="#Seoul #Concert #MusicVideo"
          link="/blackpink"
        />
      </div>
    </div>
  );
}
