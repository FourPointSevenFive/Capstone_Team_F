"use client";

import Link from "next/link";
import { FaHeart } from "react-icons/fa";
import Image from "next/image";
import { useLocationStore } from "@/stores/locationStore";
import { useEffect, useState } from "react";
import { cn, fetcherWithAuth } from "@/lib/utils";

import { useSession } from "next-auth/react";

interface MyLocation {
  id: number;
  location_id?: number;
  title: string;
  latitude?: number;
  longitude?: number;
  description: string;
  video_link?: string;
  favorite_cnt?: number;
  pose?: string;
  created_at: string;
  updated_at?: string;
  images?: LocationImage[];
  category?: "K_POP" | "DRAMA" | "MOVIE" | "NOVEL";
  image?: string;
}
interface LocationImage {
  id: number;
  image_url: string;
  description: string;
  created_at: string;
}

interface IsFavoriteProps {
  result: boolean;
  total: number;
}

export default function LocationCard({
  locationinfo,
}: {
  locationinfo: MyLocation;
}) {
  const { status } = useSession();
  const [isFavorite, setIsFavorite] = useState(false);
  const postFavoriteOn = async () => {
    const data = await fetcherWithAuth
      .post(
        `api/v1/user/favorite/on?location_id=${locationinfo.location_id ?? locationinfo.id}`,
      )
      .json();
    return data;
  };

  const postFavoriteOff = async () => {
    const data = await fetcherWithAuth
      .delete(
        `api/v1/user/favorite/off?location_id=${locationinfo.location_id ?? locationinfo.id}`,
      )
      .json();
    return data;
  };

  const getIsfavorite = async () => {
    if (status !== "authenticated") return;
    const data = await fetcherWithAuth
      .get(
        `api/v1/user/favorite?location_id=${locationinfo.location_id ?? locationinfo.id}`,
      )
      .json();
    return data;
  };

  useEffect(() => {
    if (status !== "authenticated") return;
    getIsfavorite().then((data) => {
      //console.log(data);
      const favoriteData = data as IsFavoriteProps;
      setIsFavorite(favoriteData.result);
    });
  }, []);

  const setSelectedLocation = useLocationStore(
    (state) => state.setSelectedLocation,
  );

  const handleLocationClick = () => {
    setSelectedLocation(locationinfo);
  };

  const handleFavoriteClick = (
    e: React.MouseEvent<SVGElement, MouseEvent>,
  ): void => {
    e.stopPropagation();
    e.preventDefault();
    setIsFavorite(!isFavorite);

    if (!isFavorite) {
      postFavoriteOn();
    } else {
      postFavoriteOff();
    }
  };

  return (
    <Link href={"/map/1"} className="w-full" onClick={handleLocationClick}>
      <div className="verticle justify-left"></div>
      <div className="mb-4 flex items-center justify-between">
        <div className="w-64 text-sm font-bold">{locationinfo.title}</div>
        <div>
          {status === "authenticated" && (
            <FaHeart
              className={
                cn("size-6") + (isFavorite ? " text-red-500" : " text-gray-500")
              }
              onClick={handleFavoriteClick}
            />
          )}
        </div>
      </div>
      <div className="min-h-[200px] w-full rounded-[12px] pl-2">
        <div>
          {locationinfo.images && locationinfo.images[0] && (
            <Image
              key={locationinfo.images[0].id}
              src={locationinfo.images[0].image_url}
              alt={locationinfo.images[0].description}
              width={300}
              height={0}
              style={{ height: "auto" }}
              className="overflow-hidden rounded-lg"
            />
          )}
          {locationinfo.image && (
            <Image
              src={locationinfo.image}
              alt={locationinfo.title}
              width={300}
              height={0}
              style={{ height: "auto" }}
              className="overflow-hidden rounded-lg"
            />
          )}
        </div>
      </div>
      <p className="w-full pl-1 pr-1 pt-2.5 text-xs">
        {locationinfo.description}
      </p>
      <div className="mt-2 h-0.5 w-full bg-gray-100"></div>
    </Link>
  );
}
