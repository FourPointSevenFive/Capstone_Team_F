export default function Stamp({
  title,
  date,
}: {
  title: string;
  date: string;
}) {
  return (
    <div className="flex flex-col border-2">
      <div>{title}</div>
      <div>{date}</div>
    </div>
  );
}
