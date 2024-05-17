import { localAxios } from "@/utils/http-commons";

const local = localAxios();

function loginMember(loginRequest, success, fail) {
  return new Promise((resolve, reject) => {
    local
      .post(`/members/login`, JSON.stringify(loginRequest))
      .then((response) => {
        resolve(response.data)
      })
      .catch((fail) => {
        console.log(fail.response.status)
        if (fail.response.status === 401) {
          alert(fail.response.data)
        }
        else {
          reject(fail)
        }
      });
  })
}

function checkId(memberId, success, fail) {
  return new Promise((resolve, reject) => {
    local
      .get(`/members/isIdDuplicated/${memberId}`)
      .then((response) => {
        console.log("사용 가능한 아이디입니다.");
        resolve(false)
      })
      .catch((fail) => {
        console.log(fail.response.status)
        if (fail.response.status === 409) {
          alert(fail.response.data)
          reject(fail)
        }
        else {
          reject(fail)
        }
      });
  })
}

function joinMember(memberDto, success, fail) {
  return new Promise((resolve, reject) => {
  local
    .post(`/members/join`, JSON.stringify(memberDto))
    .then((response) => {
      response.data;
      console.log(response.data);
    })
    .catch(fail);
  })
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
