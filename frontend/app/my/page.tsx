import Header from "../_components/Header";
import MapContainer from "../_components/Map";
import LocationCard from "../map/_components/LocationCard";
import Stamp from "./_components/Stamp";

export default function Page() {
  return (
    <div className="flex flex-col gap-5">
      <div className="flex justify-between">
        <Header />
      </div>
      <MapContainer />
      <div className="flex flex-col gap-20">
        <MyList />
        <MyProofShot />
        <MyStamps />
        <MyStats />
      </div>
    </div>
  );
}

const MyList = () => {
  const total = 91; // TODO: fetch from API
  return (
    <div>
      <Title title="MY LIST" total={total} />
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
  return (
    <div className="pl-1 pr-1">
      <Title title="PROOF SHOTS" />
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
      <Title title="MY STAMPS" total={total} />

      <div className="mx-auto grid max-w-xl grid-cols-3 gap-x-2 gap-y-3">
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
      <Title title="MY STATS" />
      <div className="flex justify-center">title badge</div>
      <div className="flex justify-end">
        <div>circle graph</div>
        <div>total, category count</div>
      </div>
    </div>
  );
};

const Title = ({ title, total }: { title: string; total?: number }) => {
  return (
    <div className="mb-10 flex justify-between">
      <p className="text-lg font-bold">{title}</p>
      {total && (
        <div className="flex gap-2 self-end">
          <p className="font-semibold">TOTAL </p>
          <p className="font-semibold text-blue-500">{total}</p>
        </div>
      )}
    </div>
  );
};
