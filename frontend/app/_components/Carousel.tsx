import * as React from "react";

import { Card, CardContent } from "@/components/ui/card";
import {
  Carousel,
  CarouselContent,
  CarouselItem,
} from "@/components/ui/carousel";
import carouselLogo from "@/public/icons/carousel-logo.png";
import Image from "next/image";
import btn1 from "@/public/icons/carousel-btn-1.png";

export default function CarousselMain() {
  return (
    <Carousel className="w-full">
      <CarouselContent>
        {Array.from({ length: 3 }).map((_, index) => (
          <CarouselItem key={index}>
            <div>
              <Card>
                <CardContent className="flex h-60 items-center justify-center bg-gradient-to-tl from-blue-100 to-red-50">
                  <div className="flex w-full items-center justify-between">
                    <div className="mt-8 flex flex-col gap-5">
                      <p className="text-5xl font-bold">HallyuGo</p>
                      <p className="text-sm font-semibold">
                        Map App for K-culuture Lover
                      </p>
                      <Image
                        src={btn1}
                        alt="btn1"
                        height={8}
                        className="mt-10"
                      />
                    </div>
                    <Image
                      src={carouselLogo}
                      alt="init carousel"
                      height={200}
                    />
                  </div>
                </CardContent>
              </Card>
            </div>
          </CarouselItem>
        ))}
      </CarouselContent>
    </Carousel>
  );
}
