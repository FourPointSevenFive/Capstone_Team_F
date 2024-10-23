import Header from "@/app/_components/Header";
import SearchBar from "../_components/SearchBar";
import LocationCard from "./_components/LocationCard";

export default function Page() {
  return (
    <>
      <Header />
      <div className="flex justify-between">
        <div>badge</div>
        <SearchBar />
      </div>
      <div className="h-96 w-full border-2">map</div>
      <div className="flex flex-col gap-3">
        map list
        <LocationCard
          title="Bongsuyuk"
          photo="photohere"
          description="mookup"
          address="~~ "
        />
        <LocationCard
          title="skku"
          photo="photo"
          description="blabla"
          address="somewhere"
        />
      </div>
    </>
  );
}
