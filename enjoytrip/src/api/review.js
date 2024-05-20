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

function isExist(search){
    return new Promise((resolve, reject) => {
        local
            .post(`/reviews/`, JSON.stringify(search))
            .then(() => {
                resolve(true);
            })
            .catch((error) => {
                if(error.response && error.response.status === 401){
                    resolve(false);
                }
                else{
                    reject(error);
                }
            });
        });
}

function getMyReview(search){
    return new Promise((resolve, reject) => {
        local
            .post(`/reviews/`, JSON.stringify(search))
            .then((response) => {
                resolve(response.data);
            })
            .catch((error) => {
                reject(error);
            });
        });
}

function updateReview(newReview){
    return new Promise((resolve, reject) => {
        local
            .put(`/reviews/update`, JSON.stringify(newReview))
            .then((response) => {
                resolve(response.data);
            })
            .catch((error) => {
                reject(error);
            });
        });
}

function deleteReview(hotplaceId, memberId){
    return new Promise((resolve, reject) => {
        local
            .delete(`/reviews/delete/${hotplaceId}/${memberId}`)
            .then((response) => {
                resolve(response.data);
            })
            .catch((error) => {
                reject(error);
            });
        });
}

function incrementHelpfulCount(searchRequest){
    return new Promise((resolve, reject)=>{
        local
            .put(`/reviews/incrementHelpfulCount`)
            .then((response)=>{
                console.log("따봉")
                resolve(response);
            })
            .catch((error)=>{
                console.log(error);
                reject(error);
            })
    })
}

export {getByHotplaceId, getByMemberId, isExist, getMyReview, createReview, updateReview, deleteReview, incrementHelpfulCount};