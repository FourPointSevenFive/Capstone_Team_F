import Header from "./_components/Header";
import ContentsBox from "./_components/ContentsBox";
import CarouselMain from "./_components/Carousel";
import SearchBar from "./_components/SearchBar";

export default function Home() {
  return (
    <div className="flex flex-col gap-4">
      <Header />
      <CarouselMain />
      <div className="flex justify-end">
        <SearchBar />
      </div>

      <main className="flex flex-col gap-16">
        <ContentsBox title="K - Pop" />
        <ContentsBox title="Drama" />
        <ContentsBox title="Movie" />
        <ContentsBox title="Novel" />
      </main>
    </div>
  );
}
