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

function getMyReviewList(memberId) {
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
            .then((response) => {
                if (response.status === 204) {
                    console.log("리뷰쓴적업으셈")
                    resolve(false)
                } else {
                    resolve(true);
                    
                }
            })
            .catch((error) => {
                reject(error);
            });
        });
}

function getMyReviewForHotplace(search){
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

function insertHelpful(hotplaceId, writerId, memberId){
    return new Promise((resolve, reject)=>{
        local
            .get(`/reviews/helpful/${hotplaceId}/${writerId}/${memberId}`)
            .then((response)=>{
                console.log("따봉을 눌렀도다")
                resolve(response);
            })
            .catch((error)=>{
                console.log(error);
                reject(error);
            })
    })
}

function deleteHelpful(hotplaceId, writerId, memberId){
    return new Promise((resolve, reject)=>{
        local
            .delete(`/reviews/helpful/${hotplaceId}/${writerId}/${memberId}`)
            .then((response)=>{
                console.log("따봉을 해제했노라")
                resolve(response);
            })
            .catch((error)=>{
                console.log(error);
                reject(error);
            })
    })
}

function countHelpful(hotplaceId, writerId){
    return new Promise((resolve, reject)=>{
        local
            .get(`/reviews/helpful/${hotplaceId}/${writerId}`)
            .then((response)=>{
                console.log("따봉 몇 개임?", response.data)
                resolve(response.data);
            })
            .catch((error)=>{
                console.log(error);
                reject(error);
            })
    })
}

function isPushedHelpful(hotplaceId, writerId, memberId){
    return new Promise((resolve, reject)=>{
        local
            .get(`/reviews/helpful/isPushedHelpful/${hotplaceId}/${writerId}/${memberId}`)
            .then((response)=>{
                console.log("내가 눌렀을까나리", response.data)
                resolve(response.data);
            })
            .catch((error)=>{
                console.log(error);
                reject(error);
            })
    })
}

export {
    getByHotplaceId,
    getMyReviewList,
    isExist,
    createReview,
    getMyReviewForHotplace,
    updateReview,
    deleteReview,
    incrementHelpfulCount,
    insertHelpful,
    deleteHelpful,
    countHelpful,
    isPushedHelpful
};