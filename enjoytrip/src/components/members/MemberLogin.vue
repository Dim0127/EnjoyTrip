<script setup>
import { onMounted } from "vue";

import image from "@/assets/img/bg.jpg";

//Vue Material Kit 2 components
import MaterialInput from "@/components/materials/MaterialInput.vue";
import MaterialSwitch from "@/components/materials/MaterialSwitch.vue";
import MaterialButton from "@/components/materials/MaterialButton.vue";

// material-input
import setMaterialInput from "@/assets/js/material-input";
onMounted(() => {
  setMaterialInput();
});
</script>
<template>
  <DefaultNavbar transparent />
  <Header>
    <div class="page-header align-items-start min-vh-100" :style="{
      backgroundImage: `url(${image})`,
      backgroundSize: 'cover',
    }" loading="lazy">
      <span class="mask bg-gradient-dark opacity-1"></span>
      <div class="container my-auto">
        <div class="row">
          <div class="col-lg-4 col-md-8 col-12 mx-auto">
            <div class="card z-index-0 fadeIn3 fadeInBottom">
              <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                <div class="bg-gradient-info shadow-info border-radius-lg py-3 pe-1">
                  <h4 class="text-white font-weight-bolder text-center mt-2 mb-0">
                    로그인
                  </h4>

                </div>
              </div>
              <div class="card-body">
                <form role="form" class="text-start">
                  <MaterialInput id="memberId" class="input-group-outline my-3"
                    :label="{ text: '아이디', class: 'form-label' }" type="text" />
                  <MaterialInput id="memberPassword" class="input-group-outline mb-3"
                    :label="{ text: '비밀번호', class: 'form-label' }" type="password" />
                  <MaterialSwitch class="d-flex align-items-center mb-3" id="rememberMe" labelClass="mb-0 ms-3" checked>
                    아이디 기억하기</MaterialSwitch>
                  <a href="#" class="text-info text-gradient font-weight-bold d-flex justify-content-center">비밀번호를
                    잊어버렸다면?</a>
                  <div class="text-center">
                    <MaterialButton class="my-4 mb-2" variant="gradient" color="info" fullWidth>로그인</MaterialButton>
                  </div>
                  <p class="mt-4 text-sm text-center">
                    아직 EnjoyTrip의 회원이 아니라면?
                    <a href="#" class="text-primary text-gradient font-weight-bold ms-1">회원가입</a>
                  </p>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>
  </Header>
</template>
