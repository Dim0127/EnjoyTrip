import { storage } from "@/firebase";
import { ref, uploadBytesResumable, getDownloadURL, deleteObject } from "firebase/storage";

async function uploadImage(file, group, memberId) {
  return new Promise((resolve, reject) => {
    // firebase에 저장하는 이미지 이름
    // 이미지 이름 : [업로드 날짜]-[member의 아이디]
    const timestamp = new Date().getTime();
    const fileextentsion = ".jpg";
    const fileName = timestamp + "-" + memberId + fileextentsion;

    // 이미지 타입 : image = .jpg
    const newFile = new File([file], fileName, {
      type: "image/jpg",
      lastModified: Date.now(),
    });

    // 이미지 경로 : [members or hotplcaes] / filename
    const filePath = group + `/` + fileName;
    const storageRef = ref(storage, filePath);
    const uploadTask = uploadBytesResumable(storageRef, newFile);

    uploadTask.on(
      "state_changed",
      (snapshot) => {
        // 진행사항 확인
      },
      (error) => {
        // Handle unsuccessful uploads
        console.error("Upload failed", error);
        reject(error);
      },
      async () => {
        // Handle successful uploads
        try {
          const downloadURL = await getDownloadURL(uploadTask.snapshot.ref);
          resolve({ imageName: filePath, imageUrl: downloadURL });
        } catch (error) {
          console.error("Error getting download URL", error);
          reject(error);
        }
      }
    );
  });
}

// 이미지 파일 이름을 매개변수로 받아 삭제하는 함수
async function deleteImage(imagePath) {
  console.log("이미지 삭제 시작!" + imagePath);
  return new Promise((resolve, reject) => {
    const storageRef = ref(storage, imagePath);
    deleteObject(storageRef)
      .then(() => {
        resolve("Success Image in firebase");
      })
      .catch((error) => {
        reject(error);
      });
  });
}

export { uploadImage, deleteImage };
