import { commonDataAxios } from "@/util/http-commons";
const { VITE_DATA_API_SERVICE_KEY } = import.meta.env;

const commonData = commonDataAxios();

function getSido() {
  return new Promise((resolve, reject) => {
    commonData
      .get("/areaCode1", {
        params: {
          MobileOS: "WIN",
          MobileApp: "enjoytrip",
          _type: "json",
          serviceKey: VITE_DATA_API_SERVICE_KEY,
        },
      })
      .then((response) => {
        resolve(response.data.response.body.items.item);
      })
      .catch((error) => {
        reject(error);
      });
  });
}

function getGugun(sido) {
  return new Promise((resolve, reject) => {
    commonData
      .get("/areaCode1", {
        params: {
          MobileOS: "WIN",
          MobileApp: "enjoytrip",
          _type: "json",
          areaCode: sido,
          serviceKey: VITE_DATA_API_SERVICE_KEY,
        },
      })
      .then((response) => {
        resolve(response.data.response.body.items.item);
      })
      .catch((error) => {
        reject(error);
      });
  });
}

function getResult(sidoCode, gugunCode, contentTypeCode) {
  const params = {
    MobileOS: "WIN",
    MobileApp: "enjoytrip",
    _type: "json",
    serviceKey: VITE_DATA_API_SERVICE_KEY,
  };

  if (sidoCode !== 0) {
    params.areaCode = sidoCode;
  }

  if (gugunCode !== 0) {
    params.sigunguCode = gugunCode;
  }

  if (contentTypeCode !== 0) {
    params.contentTypeId = contentTypeCode;
  }
  
  return new Promise((resolve, reject) => {
    commonData
      .get("/areaBasedSyncList1", { params })
      .then((response) => {
        resolve(response.data.response.body.items.item);
      })
      .catch((error) => {
        reject(error);
      });
  });
}

export { commonData, getSido, getGugun, getResult };
