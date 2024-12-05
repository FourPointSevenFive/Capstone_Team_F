"use client";

import Image from "next/image";
import kheart from "@/public/kheart_gray.png";
import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import {
  Carousel,
  CarouselContent,
  CarouselItem,
  CarouselPrevious,
  CarouselNext,
} from "@/components/ui/carousel";
import { DialogueBox } from "./_components/DialogueBox";
import { useLocationStore } from "@/stores/locationStore";
import { calculateDistance } from "@/lib/calculateDistance";
import { use, useEffect, useState } from "react";
import { fetcher, fetcherWithAuth } from "@/lib/utils";
import { auth } from "@/lib/auth";
import { Session } from "next-auth";
import stampIcon from "@/public/icons/stamp.png";
import PhotoUploader from "@/app/_components/PhotoUploader";
import { useSession } from "next-auth/react";

interface LocationImage {
  id: number;
  image_url: string;
  description: string;
  created_at: string;
}

interface StampApiResponse {
  total: number;
  stamps: StampProps[];
}

interface StampProps {
  id: number;
  location_id: number;
  created_at: string;
}

export default function Page() {
  const loc = useLocationStore((state) => state.selectedLocation);

  return (
    <div className="flex flex-col items-center gap-5 px-8 py-4">
      <div className="flex w-full justify-center self-start">
        <p className="flex-1 font-bold">{loc?.title}</p>
      </div>
      <div className="w-full">
        <ImageCarousel images={loc?.images} />
      </div>
      <p className="mb-6 self-start text-sm text-neutral-700">
        {loc?.description}
      </p>
      <StampSection />
      <ProofShoots locationId={loc?.id ?? null} />
    </div>
  );
}

function StampSection() {
  const [gpsEnabled, setGpsEnabled] = useState(false);
  const [stampCollected, setStampCollected] = useState(false);
  const [stampCollectedAt, setStampCollectedAt] = useState<string>("");
  const loc = useLocationStore((state) => state.selectedLocation);

  useEffect(() => {
    // 현재 위치 가져오기
    navigator.geolocation.getCurrentPosition(
      (position) => {
        const { latitude, longitude } = position.coords;

        // 거리 계산
        const distance = calculateDistance(
          latitude,
          longitude,
          37.555778909,
          127.023026145,
        );

        // 1km 이하일 경우 GPS 활성화
        if (distance <= 1) {
          setGpsEnabled(true);
        }
      },
      (error) => {
        console.error("Error getting location:", error);
      },
    );
  }, [loc]);

  const [session, setSession] = useState<Session | null>(null);

  useEffect(() => {
    auth().then((session) => setSession(session));
  }, []);

  useEffect(() => {
    getStampStatus();
  }, [session, stampCollected]);

  const getStampStatus = async () => {
    if (!session || !loc) return;

    const data: StampApiResponse = await fetcherWithAuth
      .get(`api/v1/user/stamp/location?location_id=${loc.id}`)
      .json();

    if (data.stamps[0]) {
      setStampCollected(true);
      setStampCollectedAt(data.stamps[0].created_at);
    }
  };

  const buttonClick = async () => {
    if (!gpsEnabled || !session || !loc) return;

    try {
      const response = await fetcherWithAuth.post(
        `api/v1/user/stamp/location?location_id=${loc.id}`,
        {},
      );

      if (response.ok) {
        //console.log("Stamp collected successfully:");
        setStampCollected(true);
      } else {
        //console.error("Failed to collect stamp:");
        // 실패 메시지 출력 등 추가 로직
      }
    } catch (error) {
      //console.error("Error during stamp collection:", error);
      // 네트워크 오류 또는 기타 에러 처리
    }
  };

  if (!session) {
    return (
      <div className="border-1 flex h-32 w-80 cursor-pointer flex-col items-center justify-center rounded-2xl border border-neutral-200">
        <Image src={kheart} alt="kheart" width={50} height={50} />
        <p className="text-center text-xs font-light text-neutral-400">
          Please log in to collect your stamp!
        </p>
      </div>
    );
  }

  if (stampCollected) {
    return (
      <div className="border-1 flex h-32 w-80 cursor-pointer flex-col items-center justify-center rounded-2xl border border-neutral-200">
        <Image src={stampIcon} alt="kheart" width={50} height={50} />
        <p className="text-center text-xs font-light text-neutral-400">
          Visited on {new Date(stampCollectedAt).toLocaleDateString()}
        </p>
      </div>
    );
  }

  return (
    <>
      {gpsEnabled ? (
        <button
          className="border-1 flex h-32 w-80 cursor-pointer flex-col items-center justify-center rounded-2xl border border-neutral-200"
          onClick={buttonClick}
        >
          <Image src={kheart} alt="kheart" width={50} height={50} />
          <p className="text-center text-xs font-light text-neutral-700">
            GPS enabled! You can collect your stamp now.
            <br /> Click here!
          </p>
        </button>
      ) : (
        <div className="border-1 flex h-32 w-80 cursor-pointer flex-col items-center justify-center rounded-2xl border border-neutral-200">
          <Image src={kheart} alt="kheart" width={50} height={50} />
          <p className="text-center text-xs font-light text-neutral-400">
            You haven't visited this place yet! <br /> Please enable GPS and
            collect your stamp!
          </p>
        </div>
      )}
    </>
  );
}

function ProofShoots({ locationId }: { locationId: number | undefined }) {
  interface ProofShotApiResponse {
    proof_shots: ProofShotProps[];
    total: number;
  }

  interface ProofShotProps {
    category: string;
    created_at: string;
    description: string;
    id: number;
    image: string;
    location_id: number;
    title: string;
  }

  const { status } = useSession();

  const [proofShots, setProofShots] = useState<ProofShotProps[]>([]);

  const getProofShots = async () => {
    if (!locationId) return; // locationId가 없을 경우 함수 실행 중단

    try {
      const response = await fetcher.get(
        `api/v1/proof-shot/location?location_id=${locationId}`,
      );
      const data: ProofShotApiResponse = await response.json();

      // data가 배열인지 확인 후 설정
      if (data.proof_shots[0]) {
        setProofShots(data.proof_shots);
      }
    } catch (error) {
      console.error("Failed to fetch proof shots:", error);
    }
  };

  useEffect(() => {
    getProofShots();
    //console.log(proofShots);
  }, [locationId]);

  return (
    <div className="flex w-full flex-col gap-5 py-10">
      <div className="flex items-center justify-between">
        <h1 className="font-semibold">Proof Shoots</h1>
        {status === "authenticated" && (
          <PhotoUploader locationId={locationId ?? undefined} />
        )}
      </div>
      <div className="grid grid-cols-3 gap-1">
        {proofShots[0] &&
          proofShots.map((proofShot, index) => (
            <div
              key={index}
              className="flex aspect-square items-center justify-center rounded-md border pr-1"
            >
              <Image
                src={proofShot.image}
                alt="proof shot"
                className="object-cover"
                width={40}
                height={40}
              />
            </div>
          ))}
      </div>
    </div>
  );
}

function ImageCarousel({ images }: { images: LocationImage[] | undefined }) {
  return (
    <Carousel className="w-full">
      <CarouselContent>
        {images?.map((image) => (
          <CarouselItem key={image.id}>
            <Image
              src={image.image_url}
              alt={image.description}
              width={300}
              height={0}
              style={{ height: "auto" }}
              className="overflow-hidden rounded-lg"
            />
          </CarouselItem>
        ))}
      </CarouselContent>
      <CarouselPrevious className="border border-black bg-gray-200" />
      <CarouselNext className="bg-gray-200" />
    </Carousel>
  );
}
