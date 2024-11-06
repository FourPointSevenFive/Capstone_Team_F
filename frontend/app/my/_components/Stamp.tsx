export default function Stamp({
  title,
  date,
}: {
  title: string;
  date: string;
}) {
  return (
    <div className="flex flex-col border-2 rounded-lg shadow-lg w-48 aspect-square">
      <div className="flex justify-center pt-14">
        <img src="./stamp.png"/>
      </div>
      <div className="flex justify-center pt-12 text-gray-600">{title}</div>
      <div className="flex justify-center pt-1 text-sm text-gray-600">{date}</div>
    </div>
  );
}
