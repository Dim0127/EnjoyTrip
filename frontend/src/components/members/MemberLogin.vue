<script setup>
import { onMounted, ref, watch } from "vue";
//Vue Material Kit 2 components
import MaterialInput from "@/components/materials/MaterialInput.vue";
import MaterialButton from "@/components/materials/MaterialButton.vue";
import setMaterialInput from "@/assets/js/material-input";

import { storeToRefs } from "pinia"
import { useRouter } from "vue-router"
import { useMemberStore } from "@/stores/member"

import Swal from 'sweetalert2'

const router = useRouter()

const memberStore = useMemberStore()
const { isLogin } = storeToRefs(memberStore)

const { userLogin, getUserInfo } = memberStore

const loginUser = ref({
  memberId: "",
  memberPassword: "",
})

const login = async () => {
  await userLogin(loginUser.value)
  let token = sessionStorage.getItem("accessToken")
  if (isLogin.value) {
    getUserInfo(token)
    router.replace("/")
  } else {
    Swal.fire({
      title: "로그인 실패!",
      text: "아이디나 비밀번호를 확인해주세요.",
      icon: "error"
    });
  }
}

onMounted(() => {
  setMaterialInput();
});

</script>
<template>

  <div class="container my-auto mb-4">
    <div class="row ">
      <div class="col-lg-4 col-md-8 col-12 mx-auto ">
        <div class="card z-index-0 fadeIn3 fadeInBottom blur shadow-blur">
          <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
            <div class="border-radius-lg py-3 pe-1" style="background-color: #5eb0f7;">
              <h4 class="text-white font-weight-bolder text-center mt-2 mb-0">
                Enjoy Trip
              </h4>
            </div>
          </div>
          <div class="card-body">
            <p for="formFileSm" class="form-label ms-2">아이디</p>
            <MaterialInput id="memberId" class="input-group-outline mb-3" type="text" :isRequired="true" @inputEvent="(inputValue) => {
              loginUser.memberId = inputValue
            }" />
            <p for="formFileSm" class="form-label ms-2">비밀번호</p>
            <MaterialInput id="memberPassword" class="input-group-outline mb-3" type="password" :isRequired="true"
              @inputEvent="(inputValue) => {
                loginUser.memberPassword = inputValue
              }" />

            <RouterLink :to="{ name: 'forgot-password' }"
              class="nav-link text-info text-gradient font-weight-bold ms-1 mt-4 mb-2 d-flex justify-content-center">비밀번호를
              잊어버렸다면?</RouterLink>

            <div class="text-center">
              <MaterialButton class="mb-2" style="background-color: #5eb0f7; color:white;font-size:16px" fullWidth
                @click="login">
                로그인
              </MaterialButton>
            </div>
            <p class="mt-3 mb-1 text-sm text-center">
              아직 EnjoyTrip의 회원이 아니라면?
              <RouterLink :to="{ name: 'join' }" class="nav-link text-primary text-gradient font-weight-bold ms-2 mb-2"
                style="font-size:15px;">
                회원가입</RouterLink>
            </p>
          </div>


        </div>
      </div>
    </div>
  </div>

</template>
