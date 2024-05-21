import { storage } from "@/firebase.js";
import { ref, uploadBytesResumable, getDownloadURL } from "firebase/storage";

async function uploadImage(file, memberId) {
  return new Promise((resolve, reject) => {
    const timestamp = new Date().getTime();
    const fileextentsion = ".jpg";
    const fileName = timestamp + "-" + memberId + fileextentsion;

    const newFile = new File([file], fileName, {
      type: 'image/jpg',
      lastModified: Date.now(),
    });

    const filePath = `members/` + fileName;
    const storageRef = ref(storage, filePath);
    const uploadTask = uploadBytesResumable(storageRef, newFile);

    uploadTask.on(
      'state_changed',
      (snapshot) => {
        // 진행사항 확인
      },
      (error) => {
        // Handle unsuccessful uploads
        console.error('Upload failed', error);
        reject(error);
      },
      async () => {
        // Handle successful uploads
        try {
          const downloadURL = await getDownloadURL(uploadTask.snapshot.ref);
          console.log('File available at', downloadURL);
          resolve(downloadURL);
        } catch (error) {
          console.error('Error getting download URL', error);
          reject(error);
        }
      }
    );
  });
}

export { uploadImage };