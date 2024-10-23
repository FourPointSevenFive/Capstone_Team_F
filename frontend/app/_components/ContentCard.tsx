import Link from "next/link";

export default function ContentCard({
  title,
  description,
  image,
  link,
  hashtags,
}: {
  title: string;
  description: string;
  image?: string;
  link?: string;
  hashtags?: string;
}) {
  return (
    <Link href={"./"}>
      <div className="flex">
        <div>
          <p>{title}</p>
          <p>{description}</p>
          <p>{hashtags}</p>
        </div>
        <div>{image}</div>
      </div>
    </Link>
  );
}
