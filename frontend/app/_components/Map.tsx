"use client";

import { useEffect, useState } from "react";
import { Coordinates } from "@/app/types/naverMaps";
import Map from "./NaverMap";
import MapSkeleton from "./MapSkeleton";

export default function MapContainer() {
  const [loc, setLoc] = useState<Coordinates>();

  const initLocation = () => {
    navigator.geolocation.getCurrentPosition((position) => {
      console.log(position);
      setLoc([position.coords.longitude, position.coords.latitude]);
    });
  };

  useEffect(() => {
    setLoc([127.011506, 37.283732]);
  }, []);

  return loc ? <Map loc={loc} /> : <MapSkeleton />;
}
