import axios from "axios";

const { VITE_LOCAL_API_URL, VITE_DATA_API_URL, VITE_KAKAO_API_URL } = import.meta.env;


function localAxios() {
  const instance = axios.create({
    baseURL: VITE_LOCAL_API_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

function commonDataAxios() {
  const instance = axios.create({
    baseURL: VITE_DATA_API_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });

  instance.defaults.headers.common["Authorization"] = "";
  instance.defaults.headers.post["Content-Type"] = "application/json";
  instance.defaults.headers.put["Content-Type"] = "application/json";

  return instance;
}

function kakaoAxios() {
  const instance = axios.create({
    baseURL: VITE_KAKAO_API_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}


export { localAxios, commonDataAxios, kakaoAxios };
