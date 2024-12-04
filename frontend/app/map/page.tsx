"use client";

import Header from "@/app/_components/Header";
import SearchBar from "../_components/SearchBar";
import CustomBadge from "../_components/CustomBadge";
import MapSkeleton from "../_components/MapSkeleton";
import { Suspense, useEffect } from "react";
import SimpleMap from "../_components/NaverMap";
import { clsx } from "clsx";
import { useState } from "react";
import { Drawer } from "vaul";
import { fetcher } from "@/lib/utils";
import { useSearchParams } from "next/navigation";
import LocationCard from "./_components/LocationCard";

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
  category: string;
  title: string;
  hashtag: string;
  locations: Location[];
}

export default function Page() {
  const searchParams = useSearchParams().get("content_id");
  const [locations, setLocations] = useState<ApiResponse>({
    id: 0,
    category: "",
    title: "",
    hashtag: "",
    locations: [],
  });

  const getLocations = async () => {
    const data: ApiResponse = await fetcher(
      `api/v1/location?content_id=${searchParams}`,
    ).json();
    setLocations(data);
  };

  useEffect(() => {
    getLocations();
  }, []);

  return (
    <div className="flex flex-col gap-4">
      <Header />
      <div className="flex justify-between">
        <CustomBadge title="BTS" category="kpop" />
        <SearchBar />
      </div>
      <Suspense fallback={<MapSkeleton />}>
        <SimpleMap />
      </Suspense>
      <VaulDrawer locations={locations.locations} />
    </div>
  );
}

const snapPoints = ["350px", "150px", 1];
function VaulDrawer({ locations }: { locations: Location[] }) {
  const [snap, setSnap] = useState<number | string | null>(snapPoints[0]);

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
              "mx-auto flex w-full max-w-md flex-col items-center gap-10 p-4 pt-5",
              {
                "overflow-y-auto": snap === 1,
                "overflow-hidden": snap !== 1,
              },
            )}
          >
            {locations?.map((location) => (
              <LocationCard
                key={location.id}
                title={location.title}
                description={location.description}
              />
            ))}
          </div>
        </Drawer.Content>
      </Drawer.Portal>
    </Drawer.Root>
  );
}
