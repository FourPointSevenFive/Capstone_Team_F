import Header from "./_components/Header";
import ContentsBox from "./_components/ContentsBox";
import CarouselMain from "./_components/Carousel";

export default function Home() {
  return (
    <div className="">
      <Header />
      <CarouselMain />
      <SearchBar />
      <main className="flex flex-col gap-10">
        <ContentsBox title="K-Pop" />
        <ContentsBox title="Drama" />
        <ContentsBox title="Movie" />
        <ContentsBox title="Novel" />
      </main>
    </div>
  );
}
