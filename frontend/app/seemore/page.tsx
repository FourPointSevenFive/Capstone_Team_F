"use client";

import { fetcher } from "@/lib/utils";
import ContentCard from "../_components/ContentCard";
import { useSearchParams } from "next/navigation";
import { useEffect, useState } from "react";
import Header from "../_components/Header";
import { Skeleton } from "@/components/ui/skeleton";

interface ContentResponseDto {
  id: number;
  category: string;
  title: string;
  description: string;
  content_image_url: string;
  hashtag: string;
}

const getData = async (content: string) => {
  const data: ContentResponseDto[] = await fetcher
    .get(`api/v1/content?category=${encodeURIComponent(content)}`)
    .json();
  return data;
};

export default function SeeMore() {
  // next/navigation에서 useSearchParams 훅 사용
  const searchParams = useSearchParams();
  const content = searchParams.get("category");

  const [data, setData] = useState<ContentResponseDto[] | null>(null);

  useEffect(() => {
    if (!content) {
      return;
    }
    getData(content).then((data) => {
      setData(data);
    });
  }, [content]);

  if (!content) {
    return <div>No content specified</div>;
  }

  if (!data) {
    return (
      <div className="flex flex-col gap-4">
        <Skeleton className="h-10 w-1/2" />
        <Skeleton className="h-10 w-1/2" />
        <Skeleton className="h-48 w-full" />
        <Skeleton className="h-48 w-full" />
        <Skeleton className="h-48 w-full" />
      </div>
    );
  }

  return (
    <div>
      <Header />
      <div className="mt-10 flex flex-col">
        <p className="mb-5 ml-2 text-lg font-bold">
          All About The {content} ⭐️
        </p>
        <div className="flex flex-col gap-5">
          {data.map((content) => (
            <ContentCard
              key={content.id}
              title={content.title}
              description={content.description}
              hashtags={content.hashtag}
              link={`/content/${content.id}`}
              image={content.content_image_url}
            />
          ))}
        </div>
      </div>
    </div>
  );
}
