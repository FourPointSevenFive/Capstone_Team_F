"use client";
import Header from "../_components/Header";
import MapContainer from "../_components/Map";
import LocationCard from "../map/_components/LocationCard";
import Stamp from "./_components/Stamp";
//import CustomBadge from "../_components/CustomBadge";
import PieChart from "./_components/PieChart";
import { signOut, useSession } from "next-auth/react";
import { Button } from "@/components/ui/button";
import { CiLogout } from "react-icons/ci";
import Link from "next/link";

export default function Page() {
  const { data: session, status } = useSession();

  return (
    <>
      {status === "unauthenticated" && <RedirectModal />}
      <div className="flex flex-col">
        <div className="flex flex-col justify-between">
          <Header />
        </div>
        <div className="mb-4 flex w-full items-center justify-between">
          <p className="text-xl font-bold">My Page</p>
          <Button
            variant="ghost"
            className="self-end p-0"
            onClick={() => signOut()}
          >
            <CiLogout />
            Log out
          </Button>
        </div>

        <MapContainer />
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
  const total = 91; // TODO: fetch from API
  return (
    <div className="pt-4">
      <div className="pl-1 pr-1">
        <Title title="My List" total={total} />
      </div>
      <div className="flex flex-col gap-3">
        <LocationCard
          title="Bongsuyuk"
          photo="photohere"
          description="Jungkook’s favorite restaurant. They serve boiled pork and pork nabe.Be aware, there’s usually a wait!"
          address="39-16, Ingye-ro 94beon-gil, Paldal-gu, Suwon-si, Gyeonggi-do"
        />
        <LocationCard
          title="skku"
          photo="photo"
          description="blabla"
          address="39-16, Ingye-ro 94beon-gil, Paldal-gu, Suwon-si, Gyeonggi-do"
        />
      </div>
    </div>
  );
};

const MyProofShot = () => {
  const total = 21;
  return (
    <div className="flex w-full flex-col gap-5 pl-1 pr-1">
      <Title title="My ProofShots" total={total} />
      <div className="grid grid-cols-3 gap-1">
        {Array.from({ length: 10 }).map((_, index) => (
          <div
            key={index}
            className="flex aspect-square items-center justify-center bg-neutral-200 pl-1 pr-1"
          >
            photo {index + 1}
          </div>
        ))}
      </div>
    </div>
  );
};

const MyStamps = () => {
  const total = 10; //  TODO: fetch from API
  return (
    <div>
      <Title title="My Stamps" total={total} />
      <div className="mx-auto grid aspect-square max-w-xl grid-cols-3 gap-x-3 gap-y-3">
        <Stamp title="Bongsuyuk" date="July 7, 2023" />
        <Stamp title="SKKU" date="July 7, 2023" />
        <Stamp title="Busan" date="July 7, 2023" />
        <Stamp title="SquidGame" date="July 7, 2023" />
        <Stamp title="Namsan" date="July 7, 2023" />
        <Stamp title="Theglory" date="July 7, 2023" />
        <Stamp title="Bongsuyuk" date="July 7, 2023" />
        <Stamp title="Bongsuyuk" date="July 7, 2023" />
        <Stamp title="Bongsuyuk" date="July 7, 2023" />
        <Stamp title="Bongsuyuk" date="July 7, 2023" />
        <Stamp title="Bongsuyuk" date="July 7, 2023" />
      </div>
    </div>
  );
};

const MyStats = () => {
  return (
    <div className="flex flex-col gap-2">
      <Title title="My Stats" />
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
    <div className="flex justify-between">
      <p className="text-xl font-bold">{title}</p>
      {total && (
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
