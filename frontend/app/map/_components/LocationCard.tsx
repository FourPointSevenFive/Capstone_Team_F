export default function LocationCard({
  title,
  photo,
  description,
  address,
}: {
  title: string;
  photo?: string;
  description: string;
  address: string;
}) {
  return (
    <div className="flex flex-col border-2">
      <div className="flex justify-between">
        <p>{title}</p>
        <div>favorite</div>
      </div>
      <div>
        <div>{photo}</div>
        <p>{description}</p>
        <p>{address}</p>
      </div>
    </div>
  );
}
