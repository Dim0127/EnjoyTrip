<script setup>
import { onMounted, ref, watch } from "vue";
//Vue Material Kit 2 components
import MaterialInput from "@/components/materials/MaterialInput.vue";
import MaterialButton from "@/components/materials/MaterialButton.vue";
import setMaterialInput from "@/assets/js/material-input";

import { checkExistMember, updatePassword } from "@/api/member.js"

import { useRouter } from "vue-router"
import { useMemberStore } from "@/stores/member"

const router = useRouter()

const memberStore = useMemberStore()
const { checkPasswordFormat, doubleCheckPassword } = memberStore

const memberPasswordCheckMsg = ref("비밀번호는 영문 대문자, 소문자, 숫자, 특수문자\n 조합 중 2가지 이상 8자리여야 합니다.")
const confirmPasswordCheckMsg = ref("비밀번호가 일치하지 않습니다.")

onMounted(() => {
  setMaterialInput();
});

const memberIdCheckMsg = ref("")

const isInputDisabled = ref(true)

const memberPassword = ref()
const memberConfirmPassword = ref()

const isDoubleCheckedPassword = ref(false)
const isFormatCheckedPassword = ref(false)
const isValidatePassword = ref(false)

watch(memberPassword, (newValue) => {
  if (checkPasswordFormat(newValue)) {
    isFormatCheckedPassword.value = true;
    memberPasswordCheckMsg.value = "사용 가능한 비밀번호입니다."
  } else {
    isFormatCheckedPassword.value = false;
    memberPasswordCheckMsg.value = "비밀번호는 영문 대문자, 소문자, 숫자, 특수문자\n 조합 중 2가지 이상 8자리여야 합니다."
  }
})

watch(memberConfirmPassword, (newValue) => {
  if (doubleCheckPassword(memberPassword.value, newValue)) {
    isDoubleCheckedPassword.value = true
    confirmPasswordCheckMsg.value = "비밀번호가 일치합니다."
  } else {
    isDoubleCheckedPassword.value = false
    confirmPasswordCheckMsg.value = "비밀번호가 일치하지 않습니다."
  }
})

watch(isDoubleCheckedPassword, () => {
  if (isDoubleCheckedPassword.value && isFormatCheckedPassword.value) {
    isValidatePassword.value = true;
  } else {
    isValidatePassword.value = false;
  }
})


const callCheckExistMember = async () => {
  try {
    const isMemberIdExist = await checkExistMember(memberId.value);
    if (isMemberIdExist === true && memberId.value) {
      console.log("있냐?", isMemberIdExist)
      memberIdCheckMsg.value = "아이디가 확인되었습니다."
      isInputDisabled.value = false
    } else {
      memberIdCheckMsg.value = "존재하지 않는 회원입니다.\n아이디를 다시 입력해주세요."
    }
  } catch (error) {
    memberIdCheckMsg.value = "존재하지 않는 회원입니다.\n아이디를 다시 입력해주세요."
    // isInputDisabled.value = false
    console.log(error);
  }
}

const callUpdatePassword = async () => {
  try {
    await updatePassword({
      memberId: memberId.value,
      memberPassword: memberPassword.value,
    });
    router.replace("/")
  } catch (error) {
    console.log(error);
  }
}
</script>
<template>

  <div class="container my-auto">
    <div class="row">
      <div class="col-lg-8 col-md-12 col-12 mx-auto">
        <div class="card z-index-0 fadeIn3 fadeInBottom blur shadow-blur">
          <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
            <div class="bg-gradient-info shadow-info border-radius-lg py-3 pe-1">
              <h4 class="text-white font-weight-bolder text-center mt-2 mb-0">
                비밀번호 재설정
              </h4>
            </div>
          </div>

          <div class="card-body">
            <div>
              <div class="row d-flex align-items-center">
                <div class="col-md-6">
                  <span class="text-primary">*</span>
                  <label for="formFileSm" class="form-label ">아이디</label>
                  <MaterialInput class="input-group-static mb-4 " type="text" placeholder="Id" id="memberId"
                    @inputEvent="(inputValue) => {
                      memberId = inputValue
                    }" />
                </div>
                <div class="col-md-6">
                  <MaterialButton variant="gradient" color="warning" size="sm"
                    @click="(isClicked) => callCheckExistMember()">
                    아이디확인</MaterialButton>
                  <span class="text-info mt-3">
                    &nbsp;&nbsp;&nbsp;{{ memberIdCheckMsg }}
                  </span>
                </div>
              </div>

              <br>
              <div class="row">
                <div class="col-md-6">
                  <span class="text-primary">*</span>
                  <label for="formFileSm" class="form-label">비밀번호</label>
                  <MaterialInput class="input-group-static mb-4" type="password" placeholder="Password"
                    :isDisabled="isInputDisabled" id="memberPassword" @inputEvent="(inputValue) => {
                      memberPassword = inputValue
                    }" />
                  <p class="mt-3" style="font-size: 14px;">
                    {{ memberPasswordCheckMsg }}
                  </p>
                </div>
                <div class="col-md-6 ps-md-2">
                  <span class="text-primary">*</span>
                  <label for="formFileSm" class="form-label">비밀번호 확인</label>
                  <MaterialInput class="input-group-static mb-4" type="password" placeholder="confirm Password"
                    :isDisabled="isInputDisabled" @inputEvent="(inputValue) => {
                      memberConfirmPassword = inputValue
                    }" />
                  <p class="mt-3" style="font-size: 14px;">
                    {{ confirmPasswordCheckMsg }}
                  </p>
                </div>
              </div>


              <div class="d-flex justify-content-center row">
                <div class="col-6">
                  <MaterialButton class="my-4 mb-2" variant="gradient" color="info" size="lg" fullWidth
                    @click="callUpdatePassword">
                    확인
                  </MaterialButton>
                </div>
              </div>

            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
