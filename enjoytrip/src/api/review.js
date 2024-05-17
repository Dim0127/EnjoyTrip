import { localAxios } from "@/utils/http-commons";

const local = localAxios();

function getByHotplaceId(hotplaceId) {
    return new Promise((resolve, reject) => {
    local
        .get(`/reviews/hotplace/${hotplaceId}`)
        .then((response) => {
            resolve(response.data);
        })
        .catch((error) => {
            reject(error);
        });
    });
}

function getByMemberId(memberId) {
    return new Promise((resolve, reject) => {
    local
        .get(`/reviews/member/${memberId}`)
        .then((response) => {
            resolve(response.data);
        })
        .catch((error) => {
            reject(error);
        });
    });
}

function createReview(newReview){
    return new Promise((resolve, reject) => {
        console.log(newReview);
        local
            .post(`/reviews/create`, JSON.stringify(newReview))
            .then((response) => {
                resolve(response.data);
            })
            .catch((error) => {
                reject(error);
            });
        });
}

export {getByHotplaceId, getByMemberId, createReview};