import { ref } from "vue";
import { useRouter } from "vue-router";
import { defineStore } from "pinia";
import { jwtDecode } from "jwt-decode";

import { userConfirm, findById, tokenRegeneration, logout } from "@/api/member.js";
import { httpStatusCode } from "@/utils/http-status";

export const useMemberStore = defineStore("memberStore", () => {
  const router = useRouter();

  const isLogin = ref(false);
  const isLoginError = ref(false);
  const userInfo = ref(null);
  const isValidToken = ref(false);

  // 초기화 함수 추가
  const initializeStore = () => {
    const accessToken = sessionStorage.getItem("accessToken");
    if (accessToken) {
      isLogin.value = true;
      isValidToken.value = true;
      getUserInfo(accessToken);
    }
  };

  const userLogin = async (loginUser) => {
    await userConfirm(
      loginUser,
      (response) => {
        if (response.status === httpStatusCode.CREATE) {
          console.log("로그인 성공!!!!");
          let { data } = response;
          let accessToken = data["access-token"];
          let refreshToken = data["refresh-token"];
          isLogin.value = true;
          isLoginError.value = false;
          isValidToken.value = true;
          sessionStorage.setItem("accessToken", accessToken);
          sessionStorage.setItem("refreshToken", refreshToken);
        }
      },
      (error) => {
        console.log("로그인 실패!!!!");
        isLogin.value = false;
        isLoginError.value = true;
        isValidToken.value = false;
        console.error(error);
      }
    );
  };

  const getUserInfo = async (token) => {
    let decodeToken = jwtDecode(token);
    await findById(
      decodeToken.memberId,
      (response) => {
        if (response.status === httpStatusCode.OK) {
          userInfo.value = response.data.userInfo;
          // console.log("유저 정보  ",userInfo.value)
        } else {
          console.log("유저 정보 없음!!!!");
        }
      },
      async (error) => {
        console.error(
          "g[토큰 만료되어 사용 불가능.] : ",
          error.response.status,
          error.response.statusText
        );
        isValidToken.value = false;

        await tokenRegenerate();
      }
    );
  };

  const tokenRegenerate = async () => {
    await tokenRegeneration(
      JSON.stringify(userInfo.value),
      (response) => {
        if (response.status === httpStatusCode.CREATE) {
          let accessToken = response.data["access-token"];
          sessionStorage.setItem("accessToken", accessToken);
          isValidToken.value = true;
        }
      },
      async (error) => {
        // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
        if (error.response.status === httpStatusCode.UNAUTHORIZED) {
          // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
          await logout(
            userInfo.value.memberId,
            (response) => {
              if (response.status === httpStatusCode.OK) {
                console.log("리프레시 토큰 제거 성공");
              } else {
                console.log("리프레시 토큰 제거 실패");
              }
              alert("RefreshToken 기간 만료!!! 다시 로그인해 주세요.");
              isLogin.value = false;
              userInfo.value = null;
              isValidToken.value = false;
              router.push({ name: "user-login" });
            },
            (error) => {
              console.error(error);
              isLogin.value = false;
              userInfo.value = null;
            }
          );
        }
      }
    );
  };
  const userLogout = async () => {
    console.log("로그아웃 아이디 : " + userInfo.value.memberId);
    await logout(
      userInfo.value.memberId,
      (response) => {
        if (response.status === httpStatusCode.OK) {
          isLogin.value = false;
          userInfo.value = null;
          isValidToken.value = false;

          console.log(userInfo);
          sessionStorage.removeItem("accessToken");
          sessionStorage.removeItem("refreshToken");
        } else {
          console.error("유저 정보 없음!!!!");
        }
      },
      (error) => {
        console.log(error);
      }
    );
  };

  const checkPasswordFormat = (password) => {
    const pt1 = /^(?=.*[A-Z])(?=.*[a-z])[A-Za-z\d!@#$%^&*]{8,}$/;
    const pt2 = /^(?=.*[A-Z])(?=.*\d)[A-Za-z\d!@#$%^&*]{8,}$/;
    const pt3 = /^(?=.*[A-Z])(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
    const pt4 = /^(?=.*[a-z])(?=.*\d)[A-Za-z\d!@#$%^&*]{8,}$/;
    const pt5 = /^(?=.*[a-z])(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
    const pt6 = /^(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;

    for (let pt of [pt1, pt2, pt3, pt4, pt5, pt6]) {
      if (pt.test(password)) return true;
    }
    return false;
  };

  const doubleCheckPassword = (password, confirmPassword) => {
    if (password === confirmPassword) {
      return true;
    } else {
      return false;
    }
  };

  const checkEmailFormat = (email) => {
    const pt1 = /^(?=.*[A-Z])(?=.*[a-z])[A-Za-z\d]{,}$/;
    const pt2 = /^(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{1,}$/;
    const pt3 = /^(?=.*[A-Z])[A-Za-z\d]{1,}$/;
    const pt4 = /^(?=.*[a-z])(?=.*\d)[A-Za-z\d]{1,}$/;
    const pt5 = /^(?=.*[a-z])[A-Za-z\d]{1,}$/;
    const pt6 = /^(?=.*\d)[A-Za-z\d]{1,}$/;
    const pt7 = /^[A-Za-z\d]{1,}$/;
    for (let pt of [pt1, pt2, pt3, pt4, pt6, pt7]) {
      if (pt.test(email)) return true;
    }
    return false;
  };

  return {
    isLogin,
    isLoginError,
    userInfo,
    isValidToken,
    initializeStore,
    userLogin,
    getUserInfo,
    tokenRegenerate,
    userLogout,
    checkPasswordFormat,
    checkEmailFormat,
    doubleCheckPassword,
  };
});
