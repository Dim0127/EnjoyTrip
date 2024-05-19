import { localAxios } from "@/util/http-commons";

const local = localAxios();

function getAll(success, fail) {
  return new Promise((resolve, reject) => {
    local
      .get(`/hotplaces/get`)
      .then((response) => {
        resolve(response.data);
      })
      .catch((error) => {
        reject(error);
      });
  });
}

function isExist(hotplaceId) {
  return new Promise((resolve, reject) => {
    local
      .get(`/hotplaces/get/${hotplaceId}`)
      .then((response) => {
        if (response.data === "") {
          resolve(false); // hotplaceId에 해당하는 데이터가 없을 경우 false 반환
        } else {
          resolve(true); // hotplaceId에 해당하는 데이터가 있을 경우 true 반환
        }
      })
      .catch((error) => {
        reject(error);
      });
  });
}

function createHotplace(hotplaceDto, success, fail) {
  return new Promise((resolve, reject) => {
    local
      .post(`/hotplaces/create`, JSON.stringify(hotplaceDto))
      .then((response) => {
        resolve(true);
      })
      .catch((error) => {
        reject(error);
      });
  });
}

export { getAll, isExist, createHotplace };
