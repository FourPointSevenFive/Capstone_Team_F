import BackButton from "../_components/BackButton";
import LocationCard from "../map/_components/LocationCard";
import Stamp from "./_components/Stamp";

export default function Page() {
  return (
    <div className="flex flex-col gap-5">
      <div className="flex justify-between">
        <BackButton />
        <p className="text-xl">MY PAGE</p>
      </div>
      <div className="w-full rounded-[12px] bg-neutral-200 p-4 h-96">map</div>
      <MyList />
      <MyProofShot />
      <MyStamps />
      <MyStats />
    </div>
  );
}

const MyList = () => {
  const total = 91; // TODO: fetch from API
  return (
    <>
      <div className="flex justify-between pt-10 pl-1 pr-1">
        <p className="text-2xl">MY LIST</p>
        <p className="text-xl">TOTAL {total}</p>
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
    </>
  );
};

const MyProofShot = () => {
  return (
    <div className="pr-1 pl-1">
      <p className="text-2xl">Proof Shot</p>
      <p className="text-lg">My Proof Shot</p>
      <div className="grid grid-cols-3 gap-2">
        <div>pic</div>
        <div>pic</div>
        <div>pic</div>
        <div>pic</div>
        <div>pic</div>
        <div>pic</div>
        <div>pic</div>
        <div>pic</div>
        <div>pic</div>
        <div>pic</div>
      </div>
    </div>
  );
};

const MyStamps = () => {
  const total = 10; //  TODO: fetch from API
  return (
    <div>
      <p>My Stamps</p>
      <p>TOTAL {total} </p>
      <div className="grid grid-cols-3 gap-y-3 gap-x-11 max-w-xl mx-auto">
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
    <div className="flex flex-col">
      <p>my stats</p>
      <div className="flex justify-center">title badge</div>
      <div className="flex justify-end">
        <div>circle graph</div>
        <div>total, category count</div>
      </div>
    </div>
  );
};
