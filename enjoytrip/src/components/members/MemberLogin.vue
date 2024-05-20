<script setup>
import { onMounted, ref, watch } from "vue";
//Vue Material Kit 2 components
import MaterialInput from "@/components/materials/MaterialInput.vue";
import MaterialButton from "@/components/materials/MaterialButton.vue";
import setMaterialInput from "@/assets/js/material-input";

import { storeToRefs } from "pinia"
import { useRouter } from "vue-router"
import { useMemberStore } from "@/stores/member"

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
  }
}

onMounted(() => {
  setMaterialInput();
});


</script>
<template>

  <div class="container my-auto ">
    <div class="row ">
      <div class="col-lg-4 col-md-8 col-12 mx-auto ">
        <div class="card z-index-0 fadeIn3 fadeInBottom blur shadow-blur">
          <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
            <div class="bg-gradient-info shadow-info border-radius-lg py-3 pe-1">
              <h4 class="text-white font-weight-bolder text-center mt-2 mb-0">
                로그인
              </h4>
            </div>
          </div>

          <div class="card-body">
            <div>

              <MaterialInput id="memberId" class="input-group-outline my-3"
                :label="{ text: '아이디', class: 'form-label' }" type="text" :isRequired="true" @inputEvent="(inputValue) => {
                  loginUser.memberId = inputValue
                }" />
              <MaterialInput id="memberPassword" class="input-group-outline mb-3"
                :label="{ text: '비밀번호', class: 'form-label' }" type="password" :isRequired="true" @inputEvent="(inputValue) => {
                  loginUser.memberPassword = inputValue
                }" />

              <!-- <MaterialSwitch class="d-flex align-items-center mb-3" id="rememberMe" labelClass="mb-0 ms-3" checked>
                  아이디 기억하기</MaterialSwitch> -->
              <RouterLink :to="{ name: 'forgot-password' }"
                class="nav-link text-info text-gradient font-weight-bold ms-1 d-flex justify-content-center">비밀번호를
                잊어버렸다면?</RouterLink>

              <div class="text-center">
                <MaterialButton class="my-4 mb-2" variant="gradient" color="info" fullWidth @click="login">로그인
                </MaterialButton>
              </div>
              <p class="mt-4 text-sm text-center">
                아직 EnjoyTrip의 회원이 아니라면?
                <RouterLink :to="{ name: 'join' }" class="nav-link text-primary text-gradient font-weight-bold ms-1">
                  회원가입</RouterLink>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
