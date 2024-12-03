import Header from "@/app/_components/Header";
import SearchBar from "../_components/SearchBar";
import CustomBadge from "../_components/CustomBadge";
import MapContainer from "../_components/Map";
import MapSkeleton from "../_components/MapSkeleton";
import { Suspense } from "react";
import VaulDrawer from "./_components/VaulDrawer";

export default function Page() {
  return (
    <div className="flex flex-col gap-4">
      <Header />
      <div className="flex justify-between">
        <CustomBadge title="BTS" category="kpop" />
        <SearchBar />
      </div>
      <Suspense fallback={<MapSkeleton />}>
        <MapContainer />
      </Suspense>
      <VaulDrawer />
    </div>
  );
}
