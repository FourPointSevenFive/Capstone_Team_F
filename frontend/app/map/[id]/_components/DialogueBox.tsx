import { ReactNode } from "react";
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogTrigger,
} from "@/components/ui/dialog";
import Image from "next/image";

interface DialogueBoxProps {
  children: ReactNode;
}

export function DialogueBox({ children }: DialogueBoxProps) {
  return (
    <Dialog>
      <DialogTrigger asChild>{children}</DialogTrigger>
      <DialogTitle></DialogTitle>
      <DialogContent className="max-w-[500px] max-h-[400px] flex flex-col items-center justify-center">
        <div className="h-60 w-60 border-2 mt-7 mb-2 rounded-[15px]"> 
            photo
        </div>
        <div className=""> 
            <Image src={"/icons/stamp.png"} alt="stampImage" width={60} height={60}>
            {}
            </Image>       
        </div>
        <div className="text-sm"> 
            USERNAME visited on DATE
        </div>
      </DialogContent>
    </Dialog>
  );
}
