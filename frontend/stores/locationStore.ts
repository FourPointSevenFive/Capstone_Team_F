// stores/locationStore.ts
import { create } from "zustand";

interface LocationState {
  selectedLocation: Location | null;
  setSelectedLocation: (location: Location) => void;
}

interface Location {
  id: number;
  title: string;
  latitude?: number;
  longitude?: number;
  description: string;
  video_link?: string;
  favorite_cnt?: number;
  pose?: string;
  created_at: string;
  updated_at?: string;
  images?: LocationImage[];
  category?: "K_POP" | "DRAMA" | "MOVIE" | "NOVEL";
  image?: string;
}

interface LocationImage {
  id: number;
  image_url: string;
  description: string;
  created_at: string;
}

export const useLocationStore = create<LocationState>((set) => ({
  selectedLocation: null,
  setSelectedLocation: (location) => set({ selectedLocation: location }),
}));
