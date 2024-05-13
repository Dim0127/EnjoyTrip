import { localAxios } from "@/utils/http-commons";

const local = localAxios();

function loginMember(loginRequest, success, fail) {
  local
    .post(`/members/login`, JSON.stringify(loginRequest))
    .then((response) => {
      response.data;
      console.log(response.data);
    })
    .catch(fail);
}

function checkId(isAvailableId, success, fail) {
  local
    .get(`/members/${isAvailableId}`)
    .then((response) => {
      response.data;
      console.log("Can Use!");
    })
    .catch(fail);
}

function joinMember(memberDto, success, fail) {
  local
    .post(`/members/join`, JSON.stringify(memberDto))
    .then((response) => {
      response.data;
      console.log(response.data);
    })
    .catch(fail);
}

function updateMember(memberDto, success, fail) {
  local
    .put(`/members/update/${memberDto.memberId}`, JSON.stringify(memberDto))
    .then((response) => {
      console.log("Success Update");
    })
    .catch(fail);
}

function deleteMember(memberId, success, fail) {
  local
    .get("/members/delete/${memberId}")
    .then((success) => {
      console.log("Success Delete");
    })
    .catch(fail);
}

export { loginMember, checkId, joinMember, updateMember, deleteMember };
