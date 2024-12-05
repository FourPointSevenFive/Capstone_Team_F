"use client";
import Header from "../_components/Header";

import LocationCard from "../map/_components/LocationCard";
import Stamp from "./_components/Stamp";
//import CustomBadge from "../_components/CustomBadge";
import PieChart from "./_components/PieChart";
import { signOut, useSession } from "next-auth/react";
import { Button } from "@/components/ui/button";
import { CiLogout } from "react-icons/ci";
import Link from "next/link";
import { fetcherWithAuth } from "@/lib/utils";
import { useEffect, useState } from "react";
import Image from "next/image";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { ScrollArea } from "@/components/ui/scroll-area";
import { NaverMap } from "react-naver-maps";
import MapWithMarker from "../_components/NaverMap";

const getFavoriteLocations = async () => {
  const data: FavoriteApiResponse = (await fetcherWithAuth
    .get("api/v1/user/favorite?limit=50")
    .json()) as FavoriteApiResponse;
  return data;
};

interface FavoriteApiResponse {
  total: number;
  favorites: FavoriteProps[];
}

interface FavoriteProps {
  id: number;
  location_id: number;
  title: string;
  description: string;
  image: string;
  created_at: string;
}

export default function Page() {
  const { data: session, status } = useSession();
  console.log(session);

  if (status === "unauthenticated") {
    return <RedirectModal />;
  }

  return (
    <>
      <div className="flex flex-col">
        <div className="flex flex-col justify-between">
          <Header />
        </div>
        <Button
          variant="ghost"
          className="self-end p-0"
          onClick={() => signOut()}
        >
          <CiLogout />
          Log out
        </Button>

        <p className="mt-4 w-fit self-center border-b-2 border-blue-100 p-1 text-xl font-bold"></p>
        <div className="flex flex-col gap-20">
          <MyList />
          <MyProofShot />
          <MyStamps />
          <MyStats />
        </div>
      </div>
    </>
  );
}

const MyList = () => {
  const [total, setTotal] = useState(0);
  const [favorites, setFavorites] = useState<FavoriteApiResponse["favorites"]>(
    [],
  );

  const getFavorites = async () => {
    try {
      const data: FavoriteApiResponse = await getFavoriteLocations();
      setFavorites(data.favorites);
      setTotal(data.total);
    } catch (error) {
      console.error("Failed to fetch favorites:", error);
    }
  };

  useEffect(() => {
    getFavorites();
  }, []);

  return (
    <div className="pt-10">
      <div className="flex flex-col gap-2 pb-8 pl-1 pr-1">
        <Title title="My List 💕 " total={total} />
        <MyListModal favoriteList={favorites} />
      </div>
      <div className="flex flex-col gap-10 rounded-xl border border-neutral-100 p-4">
        {favorites
          ?.slice(0, 2)
          .map((favorite) => (
            <LocationCard key={favorite.id} locationinfo={favorite} />
          ))}
      </div>
    </div>
  );
};

const MyProofShot = () => {
  const [total, setTotal] = useState(0);
  const [proofshots, setProofshots] = useState<ProofShot["proof_shoots"]>([]);
  interface ProofShot {
    total: number;
    proof_shoots: {
      id: string;
      location_id: string;
      category: string;
      title: string;
      description: string;
      image: string;
      created_at: string;
    }[];
  }

  // 데이터를 가져오는 함수
  const getProofShots = async () => {
    try {
      const data: ProofShot = await fetcherWithAuth
        .get(`api/v1/user/proof-shot`)
        .json();
      setProofshots(data.proof_shoots);
      setTotal(data.total);
    } catch (error) {
      console.error("Failed to fetch proof shots:", error);
    }
  };

  useEffect(() => {
    // 컴포넌트가 렌더링될 때 데이터 가져오기
    getProofShots();
  }, []); // 빈 배열은 처음 마운트 시에만 실행됨

  return (
    <div className="flex w-full flex-col gap-5 pr-1">
      <Title title="My ProofShots 📷" total={total} />
      <div className="grid grid-cols-3 gap-1">
        {proofshots?.map((proofshot) => (
          <div
            key={proofshot.id} // 각 항목에 고유한 키를 사용
            className="flex aspect-square items-center justify-center bg-neutral-200 pl-1 pr-1"
          >
            <Image
              src={proofshot.image}
              alt={proofshot.title}
              className="object-cover"
            />
          </div>
        ))}
      </div>
    </div>
  );
};

const MyStamps = () => {
  interface Stamp {
    total: number;
    stamps: {
      id: string;
      location_id: string;
      category: "K_POP" | "DRAMA" | "MOVIE" | "NOVEL";
      title: string;
      created_at: string;
    }[];
  }

  const [total, setTotal] = useState(0);
  const [stamps, setStamps] = useState<Stamp["stamps"]>([]);

  const getStamps = async () => {
    try {
      const data: Stamp = await fetcherWithAuth
        .get(`api/v1/user/stamp?limit=100`)
        .json();
      setStamps(data.stamps);
      setTotal(data.total);
    } catch (error) {
      console.error("Failed to fetch stamps:", error);
    }
  };

  useEffect(() => {
    getStamps();
  }, []);

  return (
    <div>
      <Title title="My Stamps 💟" total={total} />
      <div className="mx-auto mt-6 grid aspect-square max-w-xl grid-cols-3 gap-x-3 gap-y-3">
        {stamps?.map((stamp) => (
          <Stamp
            key={stamp.id}
            title={stamp.title}
            date={stamp.created_at}
            category={stamp.category}
          />
        ))}
      </div>
    </div>
  );
};

const MyStats = () => {
  return (
    <div className="flex flex-col gap-2">
      <Title title="My Stats 📊" />
      <div className="flex flex-col items-center justify-center pt-3">
        <PieChart
          data={[50, 40, 30, 10]}
          labels={["kpop", "drama", "movie", "novel"]}
        />
      </div>
    </div>
  );
};

const Title = ({ title, total }: { title: string; total?: number }) => {
  return (
    <div className="flex items-center justify-between">
      <p className="text-lg font-semibold">{title}</p>
      {total !== undefined && (
        <div className="flex gap-2 self-end">
          <p className="font-semibold">TOTAL </p>
          <p className="font-semibold text-blue-500">{total}</p>
        </div>
      )}
    </div>
  );
};

const RedirectModal = () => {
  return (
    <>
      <div className="absolute inset-0 z-50 flex items-center justify-center bg-black/50">
        <div className="w-80 rounded-lg bg-white p-8 shadow-xl">
          <h2 className="mb-4 text-xl font-bold text-gray-800">My Page</h2>
          <p className="mb-6 text-gray-600">Please sign in to continue.</p>
          <div className="flex justify-around">
            <Link href="/login">
              <button className="rounded bg-blue-600 px-4 py-2 text-white hover:bg-blue-700">
                Sign In
              </button>
            </Link>
            <Link href="/">
              <button className="rounded bg-gray-200 px-4 py-2 text-gray-800 hover:bg-gray-300">
                Back to Home
              </button>
            </Link>
          </div>
        </div>
      </div>
      <style jsx global>{`
        body {
          overflow: hidden;
        }
      `}</style>
    </>
  );
};

const MyListModal = ({ favoriteList }: { favoriteList: FavoriteProps[] }) => {
  return (
    <div className="flex justify-end">
      <Dialog>
        <DialogTrigger className="self-end rounded-2xl border border-blue-500 px-3 py-1 text-sm text-black">
          View All
        </DialogTrigger>
        <DialogContent className="h-[80%] w-[90%] max-w-lg rounded-lg bg-white p-6 shadow-lg sm:w-[60%]">
          <DialogHeader className="mb-4">
            <DialogTitle className="text-lg font-semibold text-gray-700">
              Location Details
            </DialogTitle>
          </DialogHeader>
          <ScrollArea className="h-full w-full overflow-y-auto">
            <div className="flex flex-col gap-4">
              {favoriteList.map((favorite) => (
                <LocationCard key={favorite.id} locationinfo={favorite} />
              ))}
            </div>
          </ScrollArea>
        </DialogContent>
      </Dialog>
    </div>
  );
};
