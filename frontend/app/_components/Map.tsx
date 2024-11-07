"use client";

import { useEffect, useState } from "react";
import { Coordinates } from "@/app/types/naverMaps";
import Map from "./NaverMap";
import MapSkeleton from "./MapSkeleton";

export default function MapContainer() {
  const [loc, setLoc] = useState<Coordinates>();

  const initLocation = () => {
    navigator.geolocation.getCurrentPosition((position) => {
      setLoc([position.coords.longitude, position.coords.latitude]);
    });
  };

  useEffect(() => {
    initLocation();
  }, []);

  return loc ? <Map loc={loc} /> : <MapSkeleton />;
}
