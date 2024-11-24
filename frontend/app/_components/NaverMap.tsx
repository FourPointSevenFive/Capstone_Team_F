"use client";

import Script from "next/script";
import { Coordinates, NaverMap } from "@/app/types/naverMaps";
import { useCallback, useRef } from "react";

const mapId = "naver-map";

export default function Map({ loc }: { loc: Coordinates }) {
  const mapRef = useRef<NaverMap>();

  const initializeMap = useCallback(() => {
    const mapOptions = {
      center: new window.naver.maps.LatLng(loc),
      zoom: 15,
      scaleControl: true,
      mapDataControl: true,
      logoControlOptions: {
        position: naver.maps.Position.BOTTOM_LEFT,
      },
    };
    const map = new window.naver.maps.Map(mapId, mapOptions);
    mapRef.current = map;
  }, [loc]);

  return (
    <>
      <Script
        strategy="afterInteractive"
        type="text/javascript"
        src={`https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=${process.env.NEXT_PUBLIC_MAP_CLIENT_ID}`}
        onReady={initializeMap}
      ></Script>
      <div id={mapId} className="size-96 self-center" />
    </>
  );
}
