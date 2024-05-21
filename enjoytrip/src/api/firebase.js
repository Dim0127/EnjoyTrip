import { getDownloadURL } from "firebase/storage";
import { storage } from "@/firebase";

const storageRef = storage.ref();

function uploadImage(imageFile, memberId) {
  const memberLocation = storageRef.child("members/" + memberId);
  const uploadTask = memberLocation.put(imageFile);

  return new Promise((resolve, reject) => {
    uploadTask.on(
      "state_changed",
      null, // 상태 변화 리스너는 사용하지 않음
      (error) => {
        reject(error); // 업로드 중 에러 발생 시 reject
      },
      () => {
        // 업로드 성공 시 다운로드 URL 반환
        getDownloadURL(uploadTask.snapshot.ref)
          .then((downloadURL) => {
            resolve(downloadURL);
          })
          .catch((error) => {
            reject(error); // 다운로드 URL 가져오기 실패 시 reject
          });
      }
    );
  });
}

export { uploadImage };
