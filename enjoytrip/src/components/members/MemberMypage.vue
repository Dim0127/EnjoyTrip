<script setup>
import { onMounted, ref, watch, computed } from "vue";
import { useMemberStore } from "@/stores/member"
import { storeToRefs } from "pinia"
//material components
import MaterialInput from "@/components/materials/MaterialInput.vue";
import MaterialButton from "@/components/materials/MaterialButton.vue";
import setMaterialInput from "@/assets/js/material-input";
import MaterialAvatar from "@/components/materials/MaterialAvatar.vue";

import datePicker from 'vuejs3-datepicker'
import { deleteMember, updateMember } from "@/api/member.js"

const memberStore = useMemberStore()
const { getUserInfo } = memberStore
const { isLogin, userInfo } = storeToRefs(memberStore)

onMounted(() => {
  setMaterialInput();
  getMemberInfo();
  console.log(userInfo.value)
});

const getMemberInfo = async () => {
  let token = sessionStorage.getItem("accessToken")
  if (isLogin.value) {
    await getUserInfo(token)
  }
}

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
    + "-"
    + birthdateValue.getMonth()
    + "-"
    + birthdateValue.getDate().toString();
})

const isInputDisabled = ref(true)

const callDeleteMember = async () => {
  try {
    await deleteMember(userInfo.value.memberId);
    
  } catch (error) {
    memberIdCheckMsg.value = "회원 탈퇴 실패"
    console.log(error);
  }
}


const memberPassword = ref()
const memberConfirmPassword = ref()
const memberNickname = ref()
const memberEmailId = ref()

const isValidateEmail = ref(false)
const isValidateNickname = ref(false)
const isValidateBirthdate = ref(true)

const isAllValidationsOK = ref(false)


const callUpdateMember = async () => {
  try {
    await updateMember({
      memberId: userInfo.value.memberId,
      memberPassword: memberPassword.value,
      emailId: memberEmailId.value,
      emailDomain: "naver.com",
      memberName: memberNickname.value,
      memberBirth: formattedMemberBirthdate.value,
    });
    
  } catch (error) {
    console.log(error);
  }
}

</script>
<template>
  <div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6 mb-4 d-flex align-items-center">

    <div class="col-5 pt-3">
      <div class="row mt-2 mb-3">
        <div class="row">
          <div class="col-2 d-flex align-items-center">
            <MaterialAvatar image="/src/assets/img/team-1.jpg" alt="Avatar" size="xxl" class="p-0 mb-3" />
          </div>
          <div class="col-6 ms-2">
            <h3 class="mt-3">{{userInfo.memberName}}</h3>
            <div class="row">
              <div class="col-12 d-flex align-items-center">
                <input class="form-control form-control-sm border me-2" id="formFileSm" type="file">
                <MaterialButton variant="outline" color="primary" class="w-auto col-6" size="sm">파일삭제</MaterialButton>
              </div>
            </div>
          </div>

        </div>
      </div>

      <span class="text-primary">
        *은 필수입력사항입니다.
      </span>
      <form id="contact-form" method="post" autocomplete="off">
        <div class="card-body p-0 my-3">

          <div class="row d-flex align-items-center">
            <div class="col-md-6">
              <span class="text-primary">*</span>
              <label for="formFileSm" class="form-label">아이디</label>
              <MaterialInput class="input-group-static mb-4" type="text"  :placeholder="userInfo.memberId" id="memberId" :isDisabled="true" :isRequired="true"/>
            </div>
          </div>

          <div class="row">
            <div class="col-md-6">
              <span class="text-primary">*</span>
              <label for="formFileSm" class="form-label">닉네임</label>
              <MaterialInput class="input-group-static mb-4" type="text" :placeholder="userInfo.memberName" id="memberName" :isDisabled="isInputDisabled" :isRequired="true"
              />
            </div>

          </div>
          <div class="row d-flex align-items-center">
            <div class="col-md-6">
              <span class="text-primary">*</span>
              <label for="formFileSm" class="form-label">이메일</label>
              <MaterialInput class="input-group-static mb-4" type="text"  :placeholder="userInfo.emailId" id="memberEmailId" :isDisabled="isInputDisabled"  :isRequired="true">
              </MaterialInput>
            </div>
            <div class="dropdown col-md-6">
              <MaterialButton variant="gradient" color="light" class="dropdown-toggle" :class="{ show: showDropdown }"
              :isDisabled="isInputDisabled"
                @focusout="showDropdown = false" id="dropdownMenuButton" data-bs-toggle="dropdown"
                :area-expanded="showDropdown" @click.prevent="showDropdown = !showDropdown">
                @ Ssafy.com
              </MaterialButton>

              <ul class="dropdown-menu px-2 py-3" :class="{ show: showDropdown }" aria-labelledby="dropdownMenuButton">
                <li v-for="emailDomain in emailDomains">
                  <a class="dropdown-item border-radius-md">{{ emailDomain }}</a>
                </li>
              </ul>
            </div>
          </div>

          <div class="row mb-4" style="position: relative;">
            <label for="datePicker" class="form-label">생년월일</label>
            <datePicker v-model="birthdate" :icon-color="dateIconColor" placeholder="YYYY-MM-DD" style="z-index: 1;" >
            </datePicker>
          </div>

          <div class="row">
            <div class="col-12 d-flex justify-content-end">
              <MaterialButton color="danger" class="mt-3 mb-0 me-3" size="lg"
              @click.prevent="callDeleteMember">회원탈퇴</MaterialButton>

              <MaterialButton v-if="isInputDisabled" variant="gradient" color="info" class="mt-3 mb-0 me-6" size="lg"
              @click.prevent="isInputDisabled = !isInputDisabled">수정하기</MaterialButton>
              <MaterialButton v-else variant="gradient" color="info" class="mt-3 mb-0 me-6" size="lg"
              @click.prevent="callUpdateMember">저장하기</MaterialButton>
            </div>
          </div>

        </div>
      </form>
    </div>
  </div>

</template>
