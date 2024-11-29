import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";


export default function SignInPage() {
  return (
    <div className="flex flex-col gap-8 px-8">
      <Input placeholder="Email" type="email" className="h-12 bg-slate-100" />
      <Input
        placeholder="Password"
        type="password"
        className="h-12 bg-slate-100"
      />
      <Button className="h-12 bg-blue-500 hover:bg-blue-300">Sign In</Button>
      <Button variant={"ghost"}>Create An Account</Button>
    </div>
  );
}
