import Header from "@/app/_components/Header";
import SearchBar from "../_components/SearchBar";
import LocationCard from "./_components/LocationCard";
import CustomBadge from "../_components/CustomBadge";
import {
  Sheet,
  SheetContent,
  SheetDescription,
  SheetHeader,
  SheetTitle,
  SheetTrigger,
} from "@/components/ui/sheet";
import { ScrollArea } from "@/components/ui/scroll-area";

export default function Page() {
  return (
    <div className="flex flex-col gap-4">
      <Header />
      <div className="flex justify-between">
        <CustomBadge title="BTS" category="kpop" />
        <SearchBar />
      </div>
      <div className="flex flex-col gap-3">
        <div>
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

        <Sheet>
          <SheetTrigger>trigger</SheetTrigger>
          <SheetHeader></SheetHeader>
          <SheetTitle>Sheet Title</SheetTitle>
          <SheetDescription>Sheet Description</SheetDescription>
          <SheetContent side="bottom">
            <ScrollArea className="h-[90vh]">
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
              <LocationCard
                title="skku"
                photo="photo"
                description="blabla"
                address="somewhere"
              />
            </ScrollArea>
          </SheetContent>
        </Sheet>
      </div>
    </div>
  );
}
