import ContentCard from "./ContentCard";

export default function ContentsBox({ title }: { title: string }) {
  return (
    <div className="flex-col">
      <div className="flex justify-between">
        <p>{title}</p>
        <p>see more</p>
      </div>
      <div>
        <ContentCard
          title="BTS-Beyond the Scene"
          description="description"
          hashtags="#Seoul #Busan"
          link="/bts"
        />
        <ContentCard
          title="BlackPick - in your area"
          description="description"
          hashtags="#Seoul #Concert"
          link="/blackpink"
        />
      </div>
    </div>
  );
}
