"use client";

import Header from "@/app/_components/Header";
import SearchBar from "../_components/SearchBar";
import CustomBadge from "../_components/CustomBadge";
import MapSkeleton from "../_components/MapSkeleton";
import { Suspense, useEffect } from "react";
import MapWithMarker from "../_components/NaverMap";
import { clsx } from "clsx";
import { useState } from "react";
import { Drawer } from "vaul";
import { fetcher } from "@/lib/utils";
import { useSearchParams } from "next/navigation";
import LocationCard from "./_components/LocationCard";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";

interface LocationImage {
  id: number;
  image_url: string;
  description: string;
  created_at: string;
}

interface Location {
  id: number;
  title: string;
  latitude: number;
  longitude: number;
  description: string;
  video_link: string;
  favorite_cnt: number;
  pose: string;
  created_at: string;
  updated_at: string;
  images: LocationImage[];
}

interface ApiResponse {
  id: number;
  category: "K_POP" | "DRAMA" | "MOVIE" | "NOVEL";
  title: string;
  hashtag: string;
  locations: Location[];
}

export default function Page() {
  const searchParams = useSearchParams().get("content_id");
  const [category, setCategory] = useState<string>("K_POP");
  const [locations, setLocations] = useState<ApiResponse>({
    id: 0,
    category: "K_POP",
    title: "",
    hashtag: "",
    locations: [],
  });

  const [currentLocationId, setCurrentLocationId] = useState<number | null>(
    null,
  );

  const getLocations = async () => {
    const data: ApiResponse = await fetcher(
      `api/v1/location?content_id=${searchParams}`,
    ).json();
    setLocations(data);
  };

  const getLocationsByCategory = async (category: string) => {
    const data: ApiResponse[] = await fetcher(
      `api/v1/location?category=${category}`,
    ).json();
    const categoryData = data.flatMap((response) => response.locations);
    setLocations((prevState) => ({
      ...prevState,
      locations: categoryData,
      category: category as "K_POP" | "DRAMA" | "MOVIE" | "NOVEL",
    })); // 위치 데이터를 업데이트
  };
  useEffect(() => {
    if (searchParams) {
      getLocations();
    } else {
      getLocationsByCategory(category);
    }
  }, [category, searchParams]);

  return (
    <div className="flex flex-col gap-4">
      <Header />
      <div className="flex items-center justify-between gap-2">
        {searchParams ? (
          <CustomBadge title={locations.title} category={locations.category} />
        ) : (
          <CategorySelect onSelect={(category) => setCategory(category)} />
        )}
        {/* 
        <SearchBar /> */}
      </div>
      <Suspense fallback={<MapSkeleton />}>
        <MapWithMarker
          locations={locations.locations}
          category={locations.category}
          setCurrentLocationId={setCurrentLocationId}
        />
      </Suspense>
      <VaulDrawer
        locations={locations.locations}
        currentLocationId={currentLocationId}
      />
    </div>
  );
}

const snapPoints = ["350px", "150px", 1];
function VaulDrawer({
  locations,
  currentLocationId,
}: {
  locations: Location[];
  currentLocationId: number | null;
}) {
  const [snap, setSnap] = useState<number | string | null>(snapPoints[0]);
  const rearrangedLocations = currentLocationId
    ? [
        ...locations.filter((location) => location.id === currentLocationId),
        ...locations.filter((location) => location.id !== currentLocationId),
      ]
    : locations;

  return (
    <Drawer.Root
      snapPoints={snapPoints}
      activeSnapPoint={snap}
      setActiveSnapPoint={setSnap}
      modal={false}
      defaultOpen={true}
      dismissible={false}
    >
      <Drawer.Overlay className="fixed inset-0 bg-black/40" />
      <Drawer.Portal>
        <Drawer.Content
          data-testid="content"
          className="border-b-none fixed bottom-0 left-0 right-0 mx-[-1px] my-5 flex h-full max-h-[90%] w-[95%] flex-col place-self-center rounded-t-[10px] border border-gray-200 bg-white"
        >
          <Drawer.Title className="flex items-center justify-center py-5 font-medium text-gray-900">
            <div className="mb-2 h-2 w-48 self-center rounded-xl bg-gray-200" />
          </Drawer.Title>
          <div
            className={clsx(
              "mx-auto flex w-full max-w-md flex-col items-center gap-16 p-4 pt-5",
              {
                "overflow-y-auto": snap === 1,
                "overflow-hidden": snap !== 1,
              },
            )}
          >
            {rearrangedLocations.map((location) => (
              <LocationCard locationinfo={location} key={location.id} />
            ))}
          </div>
        </Drawer.Content>
      </Drawer.Portal>
    </Drawer.Root>
  );
}

const CategorySelect = ({
  onSelect,
}: {
  onSelect: (category: string) => void;
}) => {
  return (
    <Select onValueChange={onSelect}>
      <SelectTrigger className="w-[100px]">
        <SelectValue defaultValue="K_POP" placeholder="K Pop" />
      </SelectTrigger>
      <SelectContent>
        <SelectItem value="K_POP">K Pop</SelectItem>
        <SelectItem value="DRAMA">DRAMA</SelectItem>
        <SelectItem value="MOVIE">MOVIE</SelectItem>
        <SelectItem value="NOVEL">NOVEL</SelectItem>
      </SelectContent>
    </Select>
  );
};
