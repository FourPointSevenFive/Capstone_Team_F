export default function Page({ params }: { params: { id: string } }) {
  const { id } = params;
  return (
    <div className="flex flex-col">
      <div>badge</div>
      <div className="flex justify-between">
        <p>title</p>
        <div className="flex gap-2">
          <p>subtitle</p>
          <div>favorite</div>
        </div>
      </div>
      <div className="h-96 w-full border-2">photo</div>
      <div>description</div>
      <div>stamp section</div>
      <div className="flex justify-between">
        <div>proof shots</div>
        <div>add button</div>
      </div>
      <div>photo grid</div>
    </div>
  );
}
