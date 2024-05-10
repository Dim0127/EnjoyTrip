import { localAxios } from "@/utils/http-commons";

const local = localAxios();

function login(loginRequest, success, fail) {
  local
    .post(`/members/login`, JSON.stringify(loginRequest))
    .then((response) => {
      response.data;
      console.log(response.data);
    })
    .catch(fail);
}

function join(memberDto, success, fail) {
  local
    .post(`/members/login`, JSON.stringify(loginRequest))
    .then((response) => {
      response.data;
      console.log(response.data);
    })
    .catch(fail);
}

export { login };
