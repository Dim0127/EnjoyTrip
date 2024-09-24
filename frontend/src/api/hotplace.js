import { localAxios } from "@/utils/http-commons";

const local = localAxios();

function getAll(listParams) {
  console.log(listParams)
  return new Promise((resolve, reject) => {
    local
      .post(`/hotplaces/get`, JSON.stringify(listParams))
      .then((response) => {
        resolve(response.data);
      })
      .catch((error) => {
        reject(error);
      });
  });
}

function getHotplace(hotplaceId) {
  return new Promise((resolve, reject) => {
    local
      .get(`/hotplaces/get/${hotplaceId}`)
      .then((response) => {
        if (response.data === "") {
          resolve(false);
        } else {
          resolve(response.data);
        }
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
          resolve(false);
        } else {
          resolve(true);
        }
      })
      .catch((error) => {
        reject(error);
      });
  });
}

function createHotplace(hotplaceDto) {
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

function selectHotplaceWithReview(searchRequest){
  return new Promise((resolve, reject)=>{
    local
      .post(`/hotplaces/selectHotplaceWithReview`, JSON.stringify(searchRequest))
      .then((response) => {
        // console.log(response.data)
        resolve(response.data);
      })
      .catch((error) => {
        reject(error);
      });
  })
}
export { getAll, getHotplace, isExist, createHotplace, selectHotplaceWithReview };
