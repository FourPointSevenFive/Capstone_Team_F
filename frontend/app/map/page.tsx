import Header from "@/app/_components/Header";
import SearchBar from "../_components/SearchBar";
import LocationCard from "./_components/LocationCard";
import CustomBadge from "../_components/CustomBadge";
import { ScrollArea } from "@/components/ui/scroll-area";
import {
  Drawer,
  DrawerContent,
  DrawerHeader,
  DrawerTitle,
  DrawerTrigger,
} from "@/components/ui/drawer";
import { BsChevronDoubleUp } from "react-icons/bs";
import MapContainer from "../_components/Map";
import MapSkeleton from "../_components/MapSkeleton";
import { Suspense } from "react";

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

      <div className="border-1 flex flex-col justify-center gap-3 rounded-2xl border border-neutral-100 p-2">
        <CardList />
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
    </div>
  );
}

function CardList() {
  return (
    <Drawer>
      <DrawerTrigger className="self-center">
        <BsChevronDoubleUp className="text-neutral-600" />
      </DrawerTrigger>
      <DrawerContent className="w-dvw justify-center">
        <DrawerHeader>
          <DrawerTitle>{}</DrawerTitle>
        </DrawerHeader>
        <ScrollArea>
          <div className="flex h-[80vh] w-full flex-col gap-5 px-5 pb-4">
            <LocationCard
              title="skku"
              photo="photo"
              description="blabla"
              address="somewhere"
            />
            <LocationCard
              title="skku"
              photo="photo"
              description="blabla"
              address="somewhere"
            />
            <LocationCard
              title="skku"
              photo="photo"
              description="blabla"
              address="somewhere"
            />
            <LocationCard
              title="skku"
              photo="photo"
              description="blabla"
              address="somewhere"
            />
            <LocationCard
              title="skku"
              photo="photo"
              description="blabla"
              address="somewhere"
            />
          </div>
        </ScrollArea>
      </DrawerContent>
    </Drawer>
  );
}
