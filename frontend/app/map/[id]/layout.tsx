import BackButton from "./_components/BackButton";

export default function Layout({ children }: { children: React.ReactNode }) {
  return (
    <div>
      <BackButton />
      <div>{children}</div>
    </div>
  );
}
