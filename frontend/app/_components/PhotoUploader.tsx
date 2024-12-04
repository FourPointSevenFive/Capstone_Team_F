import React, { useRef } from "react";
import { MdAddToPhotos } from "react-icons/md";

const PhotoUploader = () => {
  const inputRef = useRef<HTMLInputElement | null>(null);

  const handleClick = () => {
    inputRef.current?.click();
  };

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const files = event.target.files;
    const gallery = document.getElementById("photoGallery");

    if (gallery) gallery.innerHTML = ""; // 기존 사진 초기화

    if (files) {
      Array.from(files).forEach((file) => {
        if (file.type.startsWith("image/")) {
          const reader = new FileReader();
          reader.onload = (e) => {
            const img = document.createElement("img");
            img.src = e.target?.result as string;
            img.style.width = "150px";
            img.style.margin = "10px";
            img.style.borderRadius = "8px";
            img.style.boxShadow = "0 4px 6px rgba(0, 0, 0, 0.1)";
            if (gallery) gallery.appendChild(img);
          };
          reader.readAsDataURL(file);
        }
      });
    }
  };

  return (
    <div className="flex flex-col items-center">
      {/* 클릭 트리거 */}
      <div
        className="flex cursor-pointer items-center justify-center gap-1 rounded-md bg-neutral-200 p-4 hover:bg-neutral-300"
        onClick={handleClick}
      >
        <p className="text-sm font-bold text-neutral-500">ADD</p>
        <MdAddToPhotos size={24} className="text-neutral-500" />
      </div>

      {/* 숨겨진 파일 입력 */}
      <input
        ref={inputRef}
        type="file"
        accept="image/*"
        name="proofshot"
        multiple
        className="hidden"
        onChange={handleFileChange}
      />

      {/* 갤러리 */}
      <div
        id="photoGallery"
        className="mt-4 flex flex-wrap justify-center gap-4"
      ></div>
    </div>
  );
};

export default PhotoUploader;
