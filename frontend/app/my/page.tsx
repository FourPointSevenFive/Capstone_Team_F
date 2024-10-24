import LocationCard from "../map/_components/LocationCard";
import Stamp from "./_components/Stamp";

export default function Page() {
  return (
    <div className="flex flex-col gap-5">
      <div className="flex justify-between">
        <div>backbutton</div>
        <p>MY PAGE</p>
      </div>
      <div className="h-96 w-full border-2">map</div>
      <MyList />
      <MyProofShot />
      <MyStamps />
      <MyStats />
    </div>
  );
}

export const MyList = () => {
  const total = 91; // TODO: fetch from API
  return (
    <>
      <div className="flex justify-between">
        <p>MY LIST</p>
        <p>TOTAL {total}</p>
      </div>
      <div className="flex flex-col gap-3">
        <LocationCard
          title="Bongsuyuk"
          photo="photohere"
          description="mookup"
          address="~~ "
        />
        <LocationCard
          title="skku"
          photo="photo"
          description="blabla"
          address="somewhere"
        />
      </div>
    </>
  );
};

export const MyProofShot = () => {
  return (
    <div>
      <p>My Proof Shot</p>
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

export const MyStamps = () => {
  const total = 10; //  TODO: fetch from API
  return (
    <div>
      <p>My Stamps</p>
      <p>TOTAL {total} </p>
      <div className="grid grid-cols-3 gap-2">
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

export const MyStats = () => {
  return (
    <div className="flex flex-col">
      <p>my stats</p>
      <div>title badge</div>
      <div className="flex justify-end">
        <div>circle graph</div>
        <div>total, category count</div>
      </div>
    </div>
  );
};
