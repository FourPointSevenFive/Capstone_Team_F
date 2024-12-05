"use client";

import { useRef } from "react";
import {
  Container as MapDiv,
  NaverMap,
  Marker,
  useNavermaps,
} from "react-naver-maps";

type Category = "K_POP" | "DRAMA" | "MOVIE" | "NOVEL";
interface Location {
  id: number;
  title: string;
  latitude: number;
  longitude: number;
}

function MyMap({
  locations,
  category,
  setCurrentLocationId,
}: {
  locations: Location[];
  category: Category;
  setCurrentLocationId: (id: number) => void;
}) {
  const navermaps = useNavermaps();

  const makerImage = {
    K_POP: "/icons/marker-1.png",
    DRAMA: "/icons/marker-3.png",
    MOVIE: "/icons/marker-4.png",
    NOVEL: "/icons/marker-2.png",
  };

  const mapRef = useRef<InstanceType<typeof navermaps.Map> | null>(null);
  return (
    <MapDiv style={{ width: "100%", height: "350px" }}>
      <NaverMap
        defaultCenter={new navermaps.LatLng(36.4109466, 126.976882)}
        defaultZoom={5}
        ref={mapRef}
      >
        <>
          {locations.map((location) => (
            <Marker
              key={location.id}
              position={
                new navermaps.LatLng(location.latitude, location.longitude)
              }
              icon={{
                url: makerImage[category],
                size: new navermaps.Size(25, 30),
                scaledSize: new navermaps.Size(25, 30),
                origin: new navermaps.Point(0, 0),
                anchor: new navermaps.Point(16, 16),
              }}
              // marker 클릭 시 해당 위치로 이동
              onClick={() => {
                mapRef?.current?.setZoom(15);
                mapRef?.current?.panTo(
                  new navermaps.LatLng(location.latitude, location.longitude),
                );
                setCurrentLocationId(location.id);
              }}
            />
          ))}
        </>
      </NaverMap>
    </MapDiv>
  );
}

export default function MapWithMarker({
  locations,
  category,
  setCurrentLocationId,
}: {
  locations: Location[];
  category: Category;
  setCurrentLocationId: (id: number) => void;
}) {
  return (
    <MapDiv style={{ width: "100%", height: "400px" }}>
      <MyMap
        locations={locations}
        category={category}
        setCurrentLocationId={setCurrentLocationId}
      />
    </MapDiv>
  );
}
