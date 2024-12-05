import { fetcherWithAuth } from "@/lib/utils";
import React, { useRef, useState } from "react";
import { MdAddToPhotos } from "react-icons/md";

const PhotoUploader = ({ locationId }: { locationId?: number }) => {
  const inputRef = useRef<HTMLInputElement | null>(null);
  const [selectedFile, setSelectedFile] = useState<File | null>(null);
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);

  const handleClick = () => {
    inputRef.current?.click();
  };

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];

    if (file) {
      setSelectedFile(file);
      setIsModalOpen(true); // 파일을 선택하면 모달을 엽니다.
    }
  };

  const handleUpload = async () => {
    if (!selectedFile) {
      alert("Please select a file to upload.");
      return;
    }

    if (!locationId) {
      alert("Please provide a location ID.");
      return;
    }

    const formData = new FormData();

    // ProofShotRequestDto를 JSON으로 변환하여 추가
    const proofShotRequestDto = {
      location_id: locationId,
      description: ".",
    };

    try {
      // JSON 데이터를 Blob으로 추가하면서 Content-Type을 명시합니다.
      const proofShotBlob = new Blob([JSON.stringify(proofShotRequestDto)], {
        type: "application/json",
      });
      formData.append("proof_shot", proofShotBlob);
      formData.append("image", selectedFile);

      const response = await fetcherWithAuth.post(
        "api/v1/user/proof-shot/upload",
        {
          body: formData,
        },
      );

      if (!response.ok) {
        const errorText = await response.text();
        //console.error("Server Response:", response.status, errorText);
        throw new Error(
          `Failed to upload photo: ${response.status} ${errorText}`,
        );
      }

      //console.log("Upload successful:", result);
      alert("Upload successful!");
      setIsModalOpen(false); // 업로드 성공 시 모달 닫기
    } catch (error) {
      console.error("Error uploading file:", error);
      alert("Error uploading file. Please try again.");
    }
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setSelectedFile(null);
  };

  return (
    <div className="flex flex-col items-center">
      {/* 클릭 트리거 */}
      <div
        className="flex cursor-pointer items-center justify-center gap-1 rounded-xl border border-blue-300 p-1 hover:bg-neutral-300"
        onClick={handleClick}
      >
        <p className="text-sm font-bold text-blue-500">ADD</p>
        <MdAddToPhotos size={15} className="text-blue-500" />
      </div>

      {/* 숨겨진 파일 입력 */}
      <input
        ref={inputRef}
        type="file"
        accept="image/*"
        className="hidden"
        onChange={handleFileChange}
      />

      {/* 모달 */}
      {isModalOpen && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
          <div className="w-[90%] max-w-md rounded-lg bg-white p-6 shadow-lg">
            <h2 className="mb-4 text-lg font-semibold">Upload Photo</h2>
            <div id="photoGallery" className="mb-4 flex justify-center">
              {selectedFile && (
                <img
                  src={URL.createObjectURL(selectedFile)}
                  alt="Selected preview"
                  style={{
                    width: "150px",
                    borderRadius: "8px",
                    boxShadow: "0 4px 6px rgba(0, 0, 0, 0.1)",
                  }}
                />
              )}
            </div>
            <div className="flex justify-end gap-4">
              <button
                className="rounded-md bg-gray-300 px-4 py-2 text-gray-700 hover:bg-gray-400"
                onClick={handleCloseModal}
              >
                Cancel
              </button>
              <button
                className="rounded-md bg-blue-500 px-4 py-2 text-white hover:bg-blue-600"
                onClick={handleUpload}
              >
                Upload
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default PhotoUploader;
