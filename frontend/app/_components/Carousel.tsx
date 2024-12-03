import * as React from "react";

import { Card, CardContent } from "@/components/ui/card";
import {
  Carousel,
  CarouselContent,
  CarouselItem,
  CarouselPrevious,
  CarouselNext,
} from "@/components/ui/carousel";
import carouselLogo from "@/public/icons/carousel-logo.png";
import Image from "next/image";
import itzy from "@/public/itzy.png";

export default function CarousselMain() {
  return (
    <Carousel className="w-full">
      <CarouselContent>
        <CarouselItem>
          <div>
            <Card>
              <CardContent className="flex h-60 items-center justify-center bg-gradient-to-tl from-blue-100 to-red-50">
                <div className="flex w-full items-center justify-between">
                  <div className="flex flex-col gap-5">
                    <p className="text-3xl font-bold">HallyuGo</p>
                    <p className="text-sm font-semibold">
                      Map App for K-culuture Lover
                    </p>
                  </div>
                  <Image src={carouselLogo} alt="init carousel" height={150} />
                </div>
              </CardContent>
            </Card>
          </div>
        </CarouselItem>
        <CarouselItem>
          <Card>
            <CardContent className="flex h-60 items-center justify-center rounded-xl bg-gradient-to-tl from-pink-500 to-violet-500">
              <div>
                <Image src={itzy} alt="itzy" height={150} />
                <div className="flex w-full items-end self-start">
                  <p className="text-2xl font-bold text-white">ITZY </p>
                  <p className="text-sm font-semibold text-white">- NOT SHY!</p>
                </div>
              </div>
            </CardContent>
          </Card>
        </CarouselItem>
      </CarouselContent>
      <div className="absolute bottom-4 left-1/2">
        <CarouselPrevious />
        <CarouselNext />
      </div>
    </Carousel>
  );
}
