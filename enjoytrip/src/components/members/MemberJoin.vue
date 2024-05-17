<script setup>
import { onMounted, ref, watch, computed } from "vue";
//image
import image from "@/assets/img/bg.jpg";

//material components
import MaterialInput from "@/components/materials/MaterialInput.vue";
import MaterialButton from "@/components/materials/MaterialButton.vue";
import setMaterialInput from "@/assets/js/material-input";
import MaterialAvatar from "@/components/materials/MaterialAvatar.vue";

import datePicker from 'vuejs3-datepicker'
// axios 함수
import { checkId, joinMember } from "@/api/member.js"


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

const memberId = ref()
const memberPassword = ref()
const memberConfirmPassword = ref()
const memberNickname = ref()
const memberEmailId = ref()

const isValidateId = ref(false)
const isValidateEmail = ref(false)
const isValidateNickname = ref(false)
const isValidateBirthdate = ref(true)

const isDoubleCheckedPassword = ref(false)
const isFormatCheckedPassword = ref(false)
const isValidatePassword = ref(false)

const isAllValidationsOK = ref(false)

// 다섯 개의 ref 객체가 모두 true인지 확인하는 함수
function checkAllValidations() {
  if (isValidateId.value && isValidateEmail.value && isValidateNickname.value && isValidateBirthdate.value && isValidatePassword.value) {
    console.log("All validations are complete!");
    isAllValidationsOK.value = true;
  } else {
    console.log("모든 validation이 만족되지 않았음");
    isAllValidationsOK.value = false
  }
}

// 감시하기
watch([isValidateId, isValidateEmail, isValidateNickname, isValidateBirthdate, isValidatePassword], (values) => {
  // const [isValidateId, isValidateEmail, nicknameValid, birthdateValid, passwordValid] = values;
  checkAllValidations();
});




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
  return false
}

// 이메일 유효성 체크
const selectedEmailDomain = ref()
watch(selectedEmailDomain, (newValue, oldValue) => {
  console.log(newValue)
})

watch(memberEmailId, (newValue, oldValue) => {
  if (checkEmailFormat(newValue)) {
    isValidateEmail.value = true;
  } else {
    isValidateEmail.value = false;
    console.log("이메일은 최소 하나 이상의 영어 대문자 또는 소문자, 숫자를 포함해야 합니다.")

  }
})


// 닉네임 유효성 체크
watch(memberNickname, (newValue, oldValue) => {
  if (newValue.trim().length > 0 && newValue) {
    isValidateNickname.value = true
  }
  else {
    isValidateNickname.value = false
  }
})

// id 중복+유효성 체크
const callCheckId = async () => {
  console.log("입력된 아이디 : " + memberId.value)
  try {
    const isDuplicatedId = await checkId(memberId.value);
    if (isDuplicatedId === false && memberId.value) {
      isValidateId.value = true
    } else {
      isValidateId.value = false
    }
  } catch (error) {
    console.log(error);
  }
}

// 생년월일 유효성 체크
const memberBirthdate = ref(new Date())
const dateIconColor = ref("#419bef")

const formattedMemberBirthdate = computed(() => {
  const memberBirthdateValue = memberBirthdate.value;
  return memberBirthdateValue.getFullYear()
    + "-"
    + ((memberBirthdateValue.getMonth() + 1).toString().length < 2 ? "0" + (memberBirthdateValue.getMonth() + 1).toString() : (memberBirthdateValue.getMonth() + 1).toString())
    + "-"
    + (memberBirthdateValue.getDate().toString().length < 2 ? "0" + memberBirthdateValue.getDate().toString() : memberBirthdateValue.getDate().toString());
});

function getDateDifference(birthdateValue) {
  // 오늘 날짜 구하기
  var today = new Date();
  // 오늘 날짜와 birthdateValue 사이의 시간 차이(ms) 계산
  var timeDifference = today.getTime() - birthdateValue.getTime();
  // 일 단위로 변환
  var daysDifference = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
  // 결과 반환
  return daysDifference;
}

const checkDateValidation = (birthdateValue) => {
  const result = getDateDifference(birthdateValue)
  return result < 0 ? false : true
}

watch(memberBirthdate, (newValue, oldValue) => {
  console.log(formattedMemberBirthdate.value)
  if (checkDateValidation(newValue)) {
    isValidateBirthdate.value = true
  } else {
    isValidateBirthdate.value = false
    alert("올바른 생년월일을 입력해주세요")
  }
})

// 비밀번호 형식+더블 체크
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
  return false
}

watch(memberPassword, (newValue, oldValue) => {
  if (checkPasswordFormat(newValue)) {
    isFormatCheckedPassword.value = true;
  } else {
    isFormatCheckedPassword.value = false;
    console.log("비밀번호는 영문 대문자, 소문자, 숫자, 특수문자 조합 중 2가지 이상 8자리여야 합니다.")
  }
})

const doubleCheckPassword = (password, confirmPassword) => {
  if (password === confirmPassword) {
    return true
  } else {
    return false
  }
}

watch(memberConfirmPassword, (newValue, oldValue) => {
  if (doubleCheckPassword(memberPassword.value, newValue)) {
    isDoubleCheckedPassword.value = true
  } else {
    isDoubleCheckedPassword.value = false
  }
})

watch(isDoubleCheckedPassword, (newValue, oldValue) => {
  if (isDoubleCheckedPassword.value && isFormatCheckedPassword.value) {
    isValidatePassword.value = true;
  } else {
    isValidatePassword.value = false;
  }
})

const callJoinMember = async () => {
  try {
    const isDuplicatedId = await joinMember({
      memberId: memberId.value,
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

function selectDomain(domain){
  selectedEmailDomain.value = domain
  console.log(domain)
  console.log("selectedDomain")
}

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
          <div class="mt-6 col-xl-5 col-lg-6 col-md-7 d-flex flex-column ms-auto me-auto ms-lg-auto me-lg-5"
            style="height:1200px">
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
                <div>
                  <div class="card-body p-0 my-3">

                    <div class="row d-flex align-items-center">
                      <div class="col-md-6">
                        <span class="text-primary">*</span>
                        <label for="formFileSm" class="form-label">아이디</label>
                        <MaterialInput class="input-group-static mb-4" type="text" placeholder="Id" id="memberId"
                          @inputEvent="(inputValue) => {
                            memberId = inputValue
                          }" />
                      </div>
                      <div class="col-md-6">
                        <MaterialButton variant="gradient" color="warning" size="sm"
                          @click="(isClicked) => callCheckId()">중복확인</MaterialButton>
                      </div>
                    </div>

                    <div class="row">
                      <div class="col-md-6">
                        <span class="text-primary">*</span>
                        <label for="formFileSm" class="form-label">비밀번호</label>
                        <MaterialInput class="input-group-static mb-4" type="password" placeholder="Password"
                          id="memberPassword" @inputEvent="(inputValue) => {
                            memberPassword = inputValue
                          }" />
                      </div>
                      <div class="col-md-6 ps-md-2">
                        <span class="text-primary">*</span>
                        <label for="formFileSm" class="form-label">비밀번호 확인</label>
                        <MaterialInput class="input-group-static mb-4" type="password" placeholder="confirm Password"
                          @inputEvent="(inputValue) => {
                            memberConfirmPassword = inputValue
                          }" />
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <span class="text-primary">*</span>
                        <label for="formFileSm" class="form-label">닉네임</label>
                        <MaterialInput class="input-group-static mb-4" type="text" placeholder="Nickname"
                          id="memberName" @inputEvent="(inputValue) => {
                            memberNickname = inputValue
                          }" />
                      </div>

                    </div>
                    <div class="row d-flex align-items-center">
                      <div class="col-md-6">
                        <span class="text-primary">*</span>
                        <label for="formFileSm" class="form-label">이메일</label>
                        <MaterialInput class="input-group-static mb-4" type="text" placeholder="EmailId"
                          id="memberEmailId" @inputEvent="(inputValue) => {
                            memberEmailId = inputValue
                          }" />
                      </div>
                      <div class="dropdown col-md-6">

                        <MaterialButton variant="gradient" color="light" class="dropdown-toggle"
                          :class="{ show: showDropdown }" @focusout="showDropdown = false" id="dropdownMenuButton"
                          data-bs-toggle="dropdown" :area-expanded="showDropdown"
                          @click.prevent="showDropdown = !showDropdown">
                          {{ selectedEmailDomain || '@ Ssafy.com' }}
                        </MaterialButton>

                        
                        <ul class="dropdown-menu px-2 py-3" :class="{ show: showDropdown }"
                          aria-labelledby="dropdownMenuButton">
                          <li v-for="emailDomain in emailDomains">
                            <a class="dropdown-item border-radius-md" @click.prevent="selectDomain(emailDomain)">{{emailDomain}}</a>
                          </li>
                        </ul>
                      </div>
                    </div>

                    <div class="row mb-4" style="position: relative;">
                      <label for="datePicker" class="form-label">생년월일</label>
                      <datePicker v-model="memberBirthdate" :icon-color="dateIconColor" placeholder="YYYY-MM-DD"
                        style="z-index: 1;"></datePicker>
                    </div>


                    <div class="row mt-2">
                      <label for="formFileSm" class="form-label">프로필 사진</label>
                      <div class="row">
                        <div class=" d-flex align-items-center">
                          <MaterialAvatar image="/src/assets/img/team-1.jpg" alt="Avatar" size="xl"
                            class="p-0 mb-3 ms-1 me-3" />
                          <input class="form-control form-control-sm border" id="formFileSm" type="file">
                        </div>
                      </div>
                    </div>

                    <div class="row">
                      <div class="col-md-12 text-center">
                        <MaterialButton variant="gradient" color="info" class="mt-3 mb-0"
                          @click="(isClicked) => callJoinMember()"
                          :disabled="!isAllValidationsOK">회원가입
                        </MaterialButton>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
