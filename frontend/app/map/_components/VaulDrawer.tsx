"use client";

import { clsx } from "clsx";
import { useState } from "react";
import { Drawer } from "vaul";
import LocationCard from "./LocationCard";

const snapPoints = ["148px", "355px", 1];

export default function VaulDrawer() {
  const [snap, setSnap] = useState<number | string | null>(snapPoints[0]);

  return (
    <Drawer.Root
      snapPoints={snapPoints}
      activeSnapPoint={snap}
      setActiveSnapPoint={setSnap}
      modal={false}
      defaultOpen={true}
      dismissible={false}
    >
      <Drawer.Overlay className="fixed inset-0 bg-black/40" />
      <Drawer.Portal>
        <Drawer.Content
          data-testid="content"
          className="border-b-none fixed bottom-0 left-0 right-0 mx-[-1px] my-5 flex h-full max-h-[90%] w-[95%] flex-col place-self-center rounded-t-[10px] border border-gray-200 bg-white"
        >
          <Drawer.Title className="flex items-center justify-center py-5 font-medium text-gray-900">
            <div className="mb-4 h-2 w-48 self-center rounded-xl bg-gray-200" />
          </Drawer.Title>
          <div
            className={clsx(
              "mx-auto flex w-full max-w-md flex-col items-center p-4 pt-5",
              {
                "overflow-y-auto": snap === 1,
                "overflow-hidden": snap !== 1,
              },
            )}
          >
            <LocationCard
              title="skku"
              photo="photo"
              description="blabla"
              address="somewhere"
            />
            <LocationCard
              title="skku"
              photo="photo"
              description="blabla"
              address="somewhere"
            />
            <LocationCard
              title="skku"
              photo="photo"
              description="blabla"
              address="somewhere"
            />
            <LocationCard
              title="skku"
              photo="photo"
              description="blabla"
              address="somewhere"
            />
          </div>
        </Drawer.Content>
      </Drawer.Portal>
    </Drawer.Root>
  );
}
