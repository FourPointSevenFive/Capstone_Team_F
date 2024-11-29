import Header from "./_components/Header";
import ContentsBox from "./_components/ContentsBox";
import CarouselMain from "./_components/Carousel";
import SearchBar from "./_components/SearchBar";
import ContentCard from "./_components/ContentCard";
import { fetcher } from "@/lib/utils";

export interface ContentResponseDto {
  id: number;
  category: string;
  title: string;
  description: string;
  content_image_url: string;
  hashtag: string[];
}

// Map 구조를 위한 타입 정의
export interface ContentMap {
  K_POP: ContentResponseDto[];
  DRAMA: ContentResponseDto[];
  MOVIE: ContentResponseDto[];
  NOVEL: ContentResponseDto[];
}

export default async function Home() {
  const data: ContentMap = await fetcher.get("api/v1/content/initial").json();
  console.log(data);
  return (
    <div className="flex flex-col gap-4">
      <Header />
      <CarouselMain />
      <div className="flex justify-end">
        <SearchBar />
      </div>

      <main className="flex flex-col gap-16">
        <ContentsBox title="K-Pop">
          <ContentCard
            title="BTS-Beyond the Scene"
            description="Follow the footsteps of BTS,
the pioneers of the global K-pop phenomenon."
            hashtags="#Seoul #Busan #BT21"
            link="/bts"
          />
          {data.K_POP?.map((content) => (
            <ContentCard
              key={content.id}
              title={content.title}
              description={content.description}
              hashtags={content.hashtag.join(" ")}
              link={`/content/${content.id}`}
            />
          ))}
        </ContentsBox>
        <ContentsBox title="Drama">
          <ContentCard
            title="BTS-Beyond the Scene"
            description="Follow the footsteps of BTS,
the pioneers of the global K-pop phenomenon."
            hashtags="#Seoul #Busan #BT21"
            link="/bts"
          />
          {data.DRAMA?.map((content) => (
            <ContentCard
              key={content.id}
              title={content.title}
              description={content.description}
              hashtags={content.hashtag.join(" ")}
              link={`/content/${content.id}`}
            />
          ))}
        </ContentsBox>
        <ContentsBox title="Movie">
          <ContentCard
            title="BTS-Beyond the Scene"
            description="Follow the footsteps of BTS,
the pioneers of the global K-pop phenomenon."
            hashtags="#Seoul #Busan #BT21"
            link="/bts"
          />
          {data.MOVIE?.map((content) => (
            <ContentCard
              key={content.id}
              title={content.title}
              description={content.description}
              hashtags={content.hashtag.join(" ")}
              link={`/content/${content.id}`}
            />
          ))}
        </ContentsBox>
        <ContentsBox title="Novel">
          <ContentCard
            title="BTS-Beyond the Scene"
            description="Follow the footsteps of BTS,
the pioneers of the global K-pop phenomenon."
            hashtags="#Seoul #Busan #BT21"
            link="/bts"
          />
          {data.NOVEL?.map((content) => (
            <ContentCard
              key={content.id}
              title={content.title}
              description={content.description}
              hashtags={content.hashtag.join(" ")}
              link={`/content/${content.id}`}
            />
          ))}
        </ContentsBox>
      </main>
    </div>
  );
}
