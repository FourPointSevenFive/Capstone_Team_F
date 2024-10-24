import { Input } from "@/components/ui/input";
import { IoSearch } from "react-icons/io5";

export default function SearchBar() {
  return (
    <div className="relative">
      <IoSearch className="absolute left-3 top-2 text-gray-400" />
      <Input
        placeholder="      Search..."
        className="h-8 w-52 rounded-xl text-gray-300"
      />
    </div>
  );
}
