<script setup>
import { onMounted, ref, watch, computed } from "vue";
//image
import image from "@/assets/img/bg.jpg";

//material components
import MaterialInput from "@/components/materials/MaterialInput.vue";
import MaterialButton from "@/components/materials/MaterialButton.vue";

// material-input
import setMaterialInput from "@/assets/js/material-input";

import datePicker from 'vuejs3-datepicker'

onMounted(() => {
  setMaterialInput();
});
const showDropdown = ref(false)
const emailDomains = ref([
  "naver.com",
  "gmail.com",
  "daum.net",
  "kakao.com"
])

const birthdate = ref(new Date())
const dateIconColor = ref("#419bef")
const formattedDate = computed(() => {
  const birthdateValue = birthdate.value
        return birthdateValue.getFullYear()
        +"-"
        +birthdateValue.getMonth()
        +"-"
        +birthdateValue.getDate().toString();
      })

watch(birthdate, (newValue, oldValue)=>{
  console.log(formattedDate.value)
})
</script>
<template>

  <section>
    <div class="page-header">
      <div class="container">
        <div class="row">
          <div
            class="col-6 d-lg-flex d-none h-100 my-auto pe-0 position-absolute top-0 start-0 text-center justify-content-center flex-column">
            <div class="position-relative h-100 m-3 px-7 border-radius-lg d-flex flex-column justify-content-center"
              :style="{
                backgroundImage: `url(${image})`,
                backgroundSize: 'cover',
              }" loading="lazy"></div>
          </div>
          <div class="mt-8 col-xl-5 col-lg-6 col-md-7 d-flex flex-column ms-auto me-auto ms-lg-auto me-lg-5"  style="height:1200px">
            <div class="card d-flex blur justify-content-center shadow-lg my-sm-0 my-sm-6 mt-8 mb-5">
              <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2 bg-transparent">
                <div class="bg-gradient-info shadow-info border-radius-lg p-3">
                  <h4 class="text-white text-success mb-0">회원가입</h4>
                </div>
              </div>
              <div class="card-body">
                <span>
                  EnjoyTrip의 회원이 되어 당신만의 여행 후기를 공유해주세요!
                <br>
                <br>
                </span>
                <span class="text-primary mt-3">
                  *은 필수입력사항입니다.
                </span>
                <form id="contact-form" method="post" autocomplete="off">
                  <div class="card-body p-0 my-3">

                    <div class="row d-flex align-items-center">
                      <div class="col-md-6">
                        <span class="text-primary">*</span>
                        <label for="formFileSm" class="form-label">아이디</label>
                        <MaterialInput class="input-group-static mb-4" type="text" placeholder="Id"
                        id="memberId" />
                      </div>
                      <div class="col-md-6">
                        <MaterialButton variant="gradient" color="warning" size="sm">중복확인</MaterialButton>
                      </div>
                    </div>
                    
                    <div class="row">
                      <div class="col-md-6">
                        <span class="text-primary">*</span>
                        <label for="formFileSm" class="form-label">비밀번호</label>
                        <MaterialInput class="input-group-static mb-4" type="password"
                        placeholder="Password" id="memberPassword" />
                      </div>
                      <div class="col-md-6 ps-md-2">
                        <span class="text-primary">*</span>
                        <label for="formFileSm" class="form-label">비밀번호 확인</label>
                        <MaterialInput class="input-group-static mb-4" type="password"
                        placeholder="confirm Password" />
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <span class="text-primary">*</span>
                        <label for="formFileSm" class="form-label">닉네임</label>
                        <MaterialInput class="input-group-static mb-4" type="text" placeholder="Nickname"
                        id="memberName" />
                      </div>
                      
                    </div>
                    <div class="row d-flex align-items-center">
                      <div class="col-md-6">
                        <span class="text-primary">*</span>
                        <label for="formFileSm" class="form-label">이메일</label>
                        <MaterialInput class="input-group-static mb-4" type="text" placeholder="EmailId"
                          id="memberEmailId" />
                      </div>
                      <div class="dropdown col-md-6">
                        <MaterialButton variant="gradient" color="light" class="dropdown-toggle"
                          :class="{ show: showDropdown }" @focusout="showDropdown = false" id="dropdownMenuButton"
                          data-bs-toggle="dropdown" :area-expanded="showDropdown"
                          @click.prevent="showDropdown = !showDropdown">
                          @ Ssafy.com
                        </MaterialButton>

                        <ul class="dropdown-menu px-2 py-3" :class="{ show: showDropdown }"
                          aria-labelledby="dropdownMenuButton">
                          <li v-for="emailDomain in emailDomains">
                            <a class="dropdown-item border-radius-md">{{ emailDomain }}</a>
                          </li>
                        </ul>
                      </div>
                    </div>

                    <div class="row mb-4" style="position: relative;">
                      <label for="datePicker"class="form-label">생년월일</label>
                      <datePicker v-model="birthdate" :icon-color="dateIconColor" placeholder="YYYY-MM-DD" style="z-index: 1;"></datePicker>
                    </div>

                    <div class="row mt-2">
                      <div class="mb-3">
                        <label for="formFileSm" class="form-label">프로필 사진</label>
                        <input class="form-control form-control-sm border" id="formFileSm" type="file">
                      </div>
                    </div>

                    <div class="row">
                      <div class="col-md-12 text-center">
                        <MaterialButton variant="gradient" color="info" class="mt-3 mb-0">회원가입</MaterialButton>
                      </div>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
