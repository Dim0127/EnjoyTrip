import { localAxios } from "@/utils/http-commons";

const local = localAxios();

function isExist(hotplaceId, success, fail) {
    local
        .get(`/hotplaces/get/${hotplaceId}`)
        .then((response) => {
            return response;
        })
        .catch(fail);
}

export { isExist, };
