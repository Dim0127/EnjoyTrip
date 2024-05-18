import { commonDataAxios } from "@/util/http-commons";
const { VITE_DATA_API_SERVICE_KEY } = import.meta.env;

const commonData = commonDataAxios();

function getArea() {
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

function getResult(keyword, areaCode, contentCode) {
  const params = {
    MobileOS: "WIN",
    MobileApp: "enjoytrip",
    _type: "json",
    serviceKey: VITE_DATA_API_SERVICE_KEY,
    keyword: keyword,
  };

  if (areaCode) {
    params.areaCode = areaCode;
  }

  if (contentCode) {
    params.contentTypeId = contentCode;
  }

  return new Promise((resolve, reject) => {
    commonData
      .get("/searchKeyword1", { params })
      .then((response) => {
        console.log(response.data.response.body.items.item);
        resolve(response.data.response.body.items.item);
      })
      .catch((error) => {
        reject(error);
      });
  });
}

export { commonData, getArea, getResult };
