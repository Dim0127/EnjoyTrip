import { localAxios } from "@/utils/http-commons";

const local = localAxios();

// function loginMember(loginRequest, success, fail) {
//   return new Promise((resolve, reject) => {
//     local
//       .post(`/members/login`, JSON.stringify(loginRequest))
//       .then((response) => {
//         resolve(response.data)
//       })
//       .catch((fail) => {
//         if (fail.response.status === 401) {
//           alert(fail.response.data)
//         }
//         else {
//           reject(fail)
//         }
//       });
//   })
// }

function checkId(memberId, success, fail) {
  return new Promise((resolve, reject) => {
    local
      .get(`/members/isIdDuplicated/${memberId}`)
      .then((response) => {
        resolve(false);
      })
      .catch((fail) => {
        if (fail.response.status === 409) {
          // alert(fail.response.data)
          reject(fail.response.data);
        } else {
          reject(fail);
        }
      });
  });
}

function checkExistMember(memberId, success, fail) {
  return new Promise((resolve, reject) => {
    local
      .get(`/members/isExistMember/${memberId}`)
      .then((response) => {
        resolve(true);
      })
      .catch((fail) => {
        if (fail.response.status === 404) {
          // alert(fail.response.data)
          reject(fail.response.data);
        } else {
          reject(fail);
        }
      });
  });
}

function joinMember(memberDto, success, fail) {
  console.log(memberDto);
  return new Promise((resolve, reject) => {
    local
      .post(`/members/join`, JSON.stringify(memberDto))
      .then((response) => {
        response.data;
        console.log(response.data);
        resolve(true);
      })
      .catch(fail);
  });
}

function updateMember(memberDto, success, fail) {
  return new Promise((resolve, reject) => {
    local
      .put(`/members/update/${memberDto.memberId}`, JSON.stringify(memberDto))
      .then((response) => {
        console.log("Success Update");
        resolve(true);
      })
      .catch(fail);
  });
}

function updatePassword(memberDto, success, fail) {
  return new Promise((resolve, reject) => {
    local
      .put(`/members/updatePassword`, JSON.stringify(memberDto))
      .then((response) => {
        console.log("Success Update Password");
        resolve(true);
      })
      .catch(fail);
  });
}

function deleteMember(memberId, success, fail) {
  return new Promise((resolve, reject) => {
    local
      .delete(`/members/delete/${memberId}`)
      .then((success) => {
        console.log("Success Delete");
        resolve(true);
      })
      .catch(fail);
  });
}

async function userConfirm(param, success, fail) {
  await local.post(`/members/login`, param).then(success).catch(fail);
}

async function findById(memberId, success, fail) {
  local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
  await local.get(`/members/mypage/${memberId}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  local.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
  await local.post(`/members/refresh`, user).then(success).catch(fail);
}

async function logout(memberId, success, fail) {
  await local.get(`/members/logout/${memberId}`).then(success).catch(fail);
}

export {
  // loginMember,
  checkId,
  checkExistMember,
  updatePassword,
  joinMember,
  updateMember,
  deleteMember,
  userConfirm,
  findById,
  tokenRegeneration,
  logout,
};
