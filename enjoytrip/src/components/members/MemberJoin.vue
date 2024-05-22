<script setup>
import { onMounted, ref, watch } from "vue";

//material components
import MaterialInput from "@/components/materials/MaterialInput.vue";
import MaterialButton from "@/components/materials/MaterialButton.vue";
import setMaterialInput from "@/assets/js/material-input";
import MaterialAvatar from "@/components/materials/MaterialAvatar.vue";

import datePicker from 'vuejs3-datepicker'

import Swal from 'sweetalert2'

import { checkId, joinMember } from "@/api/member.js"
import { useRouter } from "vue-router"

import { useMemberStore } from "@/stores/member"
const memberStore = useMemberStore()
const { checkPasswordFormat, doubleCheckPassword, checkEmailFormat } = memberStore

import { uploadImage } from "@/api/firebase";
const defaultImageUrl = import.meta.env.VITE_DEFAULT_IMAGE_URL;

const router = useRouter()

onMounted(() => {
  setMaterialInput();
});

const memberId = ref()
const memberPassword = ref()
const memberConfirmPassword = ref()
const memberNickname = ref()
const memberEmailId = ref()
const selectedEmailDomain = ref('ssafy.com')
const memberBirthdate = ref(new Date())

const isValidateId = ref(false)
const isValidateEmail = ref(false)
const isValidateNickname = ref(false)
const isValidateBirthdate = ref(true)

const isDoubleCheckedPassword = ref(false)
const isFormatCheckedPassword = ref(false)
const isValidatePassword = ref(false)

const isAllValidationsOK = ref(false)

const memberIdCheckMsg = ref("아이디 중복확인을 해 주세요.")
const memberPasswordCheckMsg = ref("비밀번호는 영문 대문자, 소문자, 숫자, 특수문자\n 조합 중 2가지 이상 8자리여야 합니다.")
const confirmPasswordCheckMsg = ref("비밀번호가 일치하지 않습니다.")
const emailIdCheckMsg = ref("이메일은 최소 하나 이상의 영어 대문자 또는 소문자, 숫자를 포함해야 합니다.")

onMounted(() => {
  memberBirthdate.value = new Date();
})

// 다섯 개의 ref 객체가 모두 true인지 확인하는 함수
function checkAllValidations() {
  if (isValidateId.value && isValidateEmail.value && isValidateNickname.value && isValidateBirthdate.value && isValidatePassword.value) {
    console.log("이제 회원가입할 수 있어요");
    isAllValidationsOK.value = true;
  } else {
    isAllValidationsOK.value = false
  }
}

// 감시하기
watch([isValidateId, isValidateEmail, isValidateNickname, isValidateBirthdate, isValidatePassword], (values) => {
  checkAllValidations();
});

// 닉네임 유효성 체크
watch(memberNickname, (newValue) => {
  if (newValue.trim().length > 0 && newValue) {
    isValidateNickname.value = true
  }
  else {
    isValidateNickname.value = false
  }
})

// id 중복+유효성 체크
const callCheckId = async () => {
  try {
    const isDuplicatedId = await checkId(memberId.value);
    if (isDuplicatedId === false && memberId.value) {
      isValidateId.value = true
      memberIdCheckMsg.value = "사용 가능한 아이디입니다."

    } else {
      isValidateId.value = false
    }
  } catch (error) {
    memberIdCheckMsg.value = "이미 존재하는 아이디입니다."
    console.log(error);
  }
}

// 생년월일 유효성 체크
const checkDateValidation = () => {
  return new Date().setHours(0, 0, 0, 0) - new Date(memberBirthdate.value).setHours(0, 0, 0, 0) >= 0
    ? true
    : false;
};

const dateIconColor = ref("#419bef")
watch(memberBirthdate, (newValue) => {
  if (checkDateValidation(newValue)) {
    isValidateBirthdate.value = true
  } else {
    isValidateBirthdate.value = false
    alert("올바른 생년월일을 입력해주세요")
  }
})

// 비밀번호 형식+더블 체크
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

//이메일 도메인 셀렉 이벤트
const showDropdown = ref(false)
const emailDomains = ref([
  "ssafy.com",
  "naver.com",
  "gmail.com",
  "daum.net",
  "kakao.com"
])
const selectDomain = (emailDomain) => {
  selectedEmailDomain.value = emailDomain;
  showDropdown.value = false;
};

watch(memberEmailId, (newValue) => {
  if (checkEmailFormat(newValue)) {
    isValidateEmail.value = true;
    emailIdCheckMsg.value = ""
  } else {
    isValidateEmail.value = false;
  }
})

//date -> string
const formatDate = () => {
  const year = memberBirthdate.value.getFullYear();
  const month = String(memberBirthdate.value.getMonth() + 1).padStart(2, '0');
  const day = String(memberBirthdate.value.getDate()).padStart(2, '0');

  return `${year}-${month}-${day}`;
}

//이미지 미리보기
const selectedImage = ref(null);
const memberImage = ref(null);
const selectedImageChange = async (event) => {
  const imageFile = event.target.files[0];
  if (imageFile && imageFile.name.toLowerCase().endsWith('.jpg')) {
    const reader = new FileReader();

    reader.onload = function (e) {
      memberImage.value = imageFile;
      selectedImage.value = e.target.result;
    };

    reader.onerror = function (e) {
      console.error('Error reading file:', e);
    };

    reader.readAsDataURL(imageFile);  // 파일을 Data URL로 읽기
  }
  else {
    memberImage.value = null;
    selectedImage.value = null;
  }
}

const callJoinMember = async () => {
  try {
    var memberImageName = null;
    var memberImageUrl = null;

    if (memberImage.value) {
      const imageData = await uploadImage(memberImage.value, "members", memberId.value);
      memberImageName = imageData.imageName;
      memberImageUrl = imageData.imageUrl;
    }

    await joinMember({
      memberId: memberId.value,
      memberPassword: memberPassword.value,
      emailId: memberEmailId.value,
      emailDomain: selectedEmailDomain.value,
      memberName: memberNickname.value,
      memberBirth: formatDate(memberBirthdate),
      imageName: memberImageName,
      imageUrl: memberImageUrl,
    });
    Swal.fire({
      title: "회원가입 성공!",
      text: "EnjoyTrip에서 즐거운 시간을 보내세요!",
      icon: "success"
    }).then(() => {
      router.replace({ name: "login" })
    });
  } catch (error) {
    console.log(error);
  }
}
</script>

<template>
  <section>
    <div class="d-flex align-items-center">
      <div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6 mb-4 d-flex align-items-center col-10">
        <div class="col-8" style="height:1100px">
          <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2 bg-transparent">
            <div class=" border-radius-lg p-3" style="background-color: #5badde;">
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
                    <MaterialButton variant="gradient" color="warning" size="sm" @click="(isClicked) => callCheckId()">
                      중복확인</MaterialButton>
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
                      id="memberPassword" @inputEvent="(inputValue) => {
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
                      @inputEvent="(inputValue) => {
                        memberConfirmPassword = inputValue
                      }" />
                    <p class="mt-3" style="font-size: 14px;">
                      {{ confirmPasswordCheckMsg }}
                    </p>
                  </div>
                </div>
                <br>

                <div class="row">
                  <div class="col-md-6">
                    <span class="text-primary">*</span>
                    <label for="formFileSm" class="form-label">닉네임</label>
                    <MaterialInput class="input-group-static mb-4" type="text" placeholder="Nickname" id="memberName"
                      @inputEvent="(inputValue) => {
                        memberNickname = inputValue
                      }" />
                  </div>
                </div>
                <br>

                <div class="row d-flex align-items-center">
                  <div class="col-md-6">
                    <span class="text-primary">*</span>
                    <label for="formFileSm" class="form-label">이메일</label>
                    <MaterialInput class="input-group-static mb-4" type="text" placeholder="EmailId" id="memberEmailId"
                      @inputEvent="(inputValue) => { memberEmailId = inputValue }" />
                    <span style="font-size: 14px;">
                      {{ emailIdCheckMsg }}
                    </span>
                  </div>
                  @
                  <!-- 이메일 도메인 -->
                  <div class="dropdown col-md-6">
                    <MaterialButton id="dropdownMenuButton" variant="gradient" color="light" class="dropdown-toggle"
                      data-bs-toggle="dropdown" :class="{ show: showDropdown }" :area-expanded="showDropdown"
                      @click="showDropdown = false" @click.prevent="showDropdown = !showDropdown"
                      :src="{ selectedEmailDomain }">
                      {{ selectedEmailDomain || '@ ssafy.com' }}
                    </MaterialButton>
                    <ul class="dropdown-menu px-2 py-3" :class="{ show: showDropdown }"
                      aria-labelledby="dropdownMenuButton">
                      <li v-for="emailDomain of emailDomains" :key="emailDomain" @click="selectDomain(emailDomain)">
                        <a class="dropdown-item border-radius-md">
                          {{ emailDomain }}
                        </a>
                      </li>
                    </ul>
                  </div>
                </div>
                <br>

                <div class="row mb-4" style="position: relative;">
                  <label for="datePicker" class="form-label">생년월일</label>
                  <datePicker v-model="memberBirthdate" :icon-color="dateIconColor" placeholder="YYYY-MM-DD"
                    :clear-button=true></datePicker>
                </div>
                <br>

                <div class="row mt-2">
                  <label for="formFileSm" class="form-label">프로필 사진</label>
                  <div class="row">
                    <div class=" d-flex align-items-center">
                      <MaterialAvatar :image="selectedImage ? selectedImage : defaultImageUrl" alt="Avatar" size="xl"
                        class="p-0 mb-3 ms-1 me-3" />
                      <input class="form-control form-control-sm border" id="formFileSm" type="file" accept=".jpg"
                        @change="selectedImageChange">
                    </div>
                  </div>
                </div>

                <div class="row">
                  <div class="col-md-12 text-center">
                    <MaterialButton variant="gradient" color="info" class="mt-3 mb-0" size="lg"
                      @click="(isClicked) => callJoinMember()" :disabled="!isAllValidationsOK">회원가입
                    </MaterialButton>
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
