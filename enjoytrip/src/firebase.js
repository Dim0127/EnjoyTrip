import { initializeApp } from "firebase/app";
import { getStorage } from "firebase/storage";

const firebaseConfig = {
  apiKey: "AIzaSyAsjThUIEd6ypcaI1_FziF67Jz4CPr_-eU",
  authDomain: "enjoytrip-4371c.firebaseapp.com",
  projectId: "enjoytrip-4371c",
  storageBucket: "enjoytrip-4371c.appspot.com",
  messagingSenderId: "19321624291",
  appId: "1:19321624291:web:4fed6e19dfff9c85ed57d6",
  measurementId: "G-QG0ZCW9PP8",
};
const firebaseApp = initializeApp(firebaseConfig);
const storage = getStorage(firebaseApp);

export default { firebaseApp, storage };
