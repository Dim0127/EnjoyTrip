<script setup>
import { onMounted, ref, watch, computed } from "vue";
import { useRouter } from "vue-router"
import { storeToRefs } from "pinia"
import { useMemberStore } from "@/stores/member"
//material components
import MaterialInput from "@/components/materials/MaterialInput.vue";
import MaterialButton from "@/components/materials/MaterialButton.vue";
import setMaterialInput from "@/assets/js/material-input";
import MaterialAvatar from "@/components/materials/MaterialAvatar.vue";

import Swal from 'sweetalert2'

import datePicker from 'vuejs3-datepicker'
import { deleteMember, updateMember } from "@/api/member.js"

const memberStore = useMemberStore()
const { getUserInfo, checkEmailFormat } = memberStore
const { isLogin, userInfo } = storeToRefs(memberStore)

import { uploadImage, deleteImage } from "@/api/firebase";
const defaultImageUrl = import.meta.env.VITE_DEFAULT_IMAGE_URL;

const router = useRouter();

const memberNickname = ref();
const memberEmailId = ref();
const selectedEmailDomain = ref('ssafy.com');
const memberBirthdate = ref(new Date());
const memberImageUrl = ref();

const getMemberInfo = async () => {
  let token = sessionStorage.getItem("accessToken")
  if (isLogin.value) {
    await getUserInfo(token)

    memberNickname.value = userInfo.value.memberName;
    memberEmailId.value = userInfo.value.emailId;
    selectedEmailDomain.value = userInfo.value.emailDomain;
    memberBirthdate.value = new Date(userInfo.value.memberBirth);
    memberImageUrl.value = userInfo.value.imageUrl ? userInfo.value.imageUrl : defaultImageUrl;
    selectedImage.value = memberImageUrl.value;
  }
}

onMounted(() => {
  setMaterialInput();
  getMemberInfo();
});

const showDeleteModal = () => {
  const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
      confirmButton: "btn btn-success",
      cancelButton: "btn btn-danger me-3"
    },
    buttonsStyling: false
  });
  swalWithBootstrapButtons.fire({
    title: "정말로<br>EnjoyTrip을 떠나실 건가요?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "네, 탈퇴할래요.",
    cancelButtonText: "아니요.",
    reverseButtons: true

  }).then(async (result) => { // 비동기 함수로 변경
    if (result.isConfirmed) {
      try {
        await deleteMember(userInfo.value.memberId);
        isLogin.value = false;
        router.replace("/");

        swalWithBootstrapButtons.fire({
          title: "아쉽지만 언젠가 다시 만나요!",
          text: "회원탈퇴가 완료되었습니다.",
          icon: "success"
        });
      } catch (error) {
        console.log(error);
      }
    } else if (
      result.dismiss === Swal.DismissReason.cancel
    ) {
      swalWithBootstrapButtons.fire({
        title: "EnjoyTrip에서<br>즐거운 시간을 보내세요 :)",
        icon: "info"
      });
    }
  });
}

const dateIconColor = ref("#419bef")

// false되어야 수정 가능
const isInputDisabled = ref(true)

const isValidateEmail = ref(true)
const isValidateNickname = ref(true)
const isValidateBirthdate = ref(true)
const isAllValidationsOK = ref(true)

function checkAllValidations() {
  if (isValidateEmail.value && isValidateNickname.value && isValidateBirthdate.value) {
    isAllValidationsOK.value = true;
  } else {
    isAllValidationsOK.value = false
  }
}

// 감시하기
watch([isValidateEmail, isValidateNickname, isValidateBirthdate], () => {
  checkAllValidations();
});

watch(memberNickname, (newValue) => {
  if (newValue.trim().length > 0 && newValue) {
    isValidateNickname.value = true
  }
  else {
    isValidateNickname.value = false
  }
})

watch(memberEmailId, (newValue) => {
  if (checkEmailFormat(newValue)) {
    isValidateEmail.value = true;
  } else {
    isValidateEmail.value = false;
  }
})

//이메일 도메인 셀렉 이벤트
const showDropdown = ref(false)
const emailDomains = ref([
  "naver.com",
  "gmail.com",
  "daum.net",
  "kakao.com"
])

const selectDomain = (emailDomain) => {
  selectedEmailDomain.value = emailDomain;
  showDropdown.value = false;
};

// 생년월일 유효성 체크
const checkDateValidation = () => {
  return new Date().setHours(0, 0, 0, 0) - new Date(memberBirthdate.value).setHours(0, 0, 0, 0) >= 0
    ? true
    : false;
};

watch(memberBirthdate, (newValue) => {
  if (checkDateValidation(newValue)) {
    isValidateBirthdate.value = true
  } else {
    isValidateBirthdate.value = false
    alert("올바른 생년월일을 입력해주세요")
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

//기존 등록된 이미지 삭제하기 - 복구 불가능
const showDeleteImageModal = async () => {
  const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
      confirmButton: "btn btn-success",
      cancelButton: "btn btn-danger me-3"
    },
    buttonsStyling: false
  });
  swalWithBootstrapButtons.fire({
    title: "등록된 사진을<br> 삭제하시겠습니까?",
    icon: "question",
    showCancelButton: true,
    confirmButtonText: "네",
    cancelButtonText: "아니요",
    reverseButtons: true
  }).then(async (result) => { // 비동기 함수로 변경
    if (userInfo.value.imageName == null) {
      swalWithBootstrapButtons.fire({
        title: "등록된 사진이 없습니다!",
        icon: "success"
      });
    }
    else if (result.isConfirmed) {
      try {
        await deleteImage(userInfo.value.imageName);

        await callUpdatemember({
          memberId: userInfo.value.memberId,
          memberPassword: userInfo.value.memberPassword,
          emailId: userInfo.value.emailId,
          emailDomain: userInfo.value.emailDomain,
          memberName: memberNickname.value,
          memberBirth: formatDate(memberBirthdate.value),
          imageName: null,
          imageUrl: null,
        });

        await getMemberInfo();

        isInputDisabled.value = true;
        swalWithBootstrapButtons.fire({
          title: "등록된 사진이 삭제 되었습니다!",
          icon: "success"
        });
      }
      catch (error) {
        console.log(error);
      }
    }
    else if (
      result.dismiss === Swal.DismissReason.cancel
    ) {
      swalWithBootstrapButtons.fire({
        title: "삭제가 취소되었습니다",
        icon: "info"
      });
    }
  })
}

const showUpdateModal = async () => {
  const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
      confirmButton: "btn btn-success",
      cancelButton: "btn btn-danger me-3"
    },
    buttonsStyling: false
  });
  swalWithBootstrapButtons.fire({
    title: "수정하시겠습니까?",
    icon: "question",
    showCancelButton: true,
    confirmButtonText: "네",
    cancelButtonText: "아니요",
    reverseButtons: true
  }).then(async (result) => { // 비동기 함수로 변경
    if (result.isConfirmed) {
      try {
        var memberImageName = null;
        var memberImageUrl = null;

        if (memberImage.value) {
          //우선 바꿀 사진을 먼저 저장하고
          const imageData = await uploadImage(memberImage.value, "members", userInfo.value.memberId);
          memberImageName = imageData.imageName;
          memberImageUrl = imageData.imageUrl;

          //기존에 등록된 사진을 삭제한다
          if (userInfo.value.imageName) {
            await deleteImage(userInfo.value.imageName);
          }
        }

        await callUpdatemember({
          memberId: userInfo.value.memberId,
          memberPassword: userInfo.value.memberPassword,
          emailId: memberEmailId.value,
          emailDomain: selectedEmailDomain.value,
          memberName: memberNickname.value,
          memberBirth: formatDate(memberBirthdate.value),
          imageName: memberImageName,
          imageUrl: memberImageUrl,
        });

        await getMemberInfo();

        isInputDisabled.value = true;
        swalWithBootstrapButtons.fire({
          title: "수정이 완료되었습니다!",
          icon: "success"
        });
      } catch (error) {
        console.log(error);
      }

    } else if (
      result.dismiss === Swal.DismissReason.cancel
    ) {
      swalWithBootstrapButtons.fire({
        title: "수정이 취소되었습니다",
        icon: "info"
      });
    }
  });
}

const callUpdatemember = async (memberDto) => {
  try {
    await updateMember(memberDto);
    return true;
  }
  catch (error) {
    console.log(error);
    return false;
  }
}
</script>

<template>
  <div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6 mb-4 d-flex align-items-center">

    <div class="col-5 pt-3">
      <div class="row mt-2 mb-3">
        <div class="row">
          <div class="col-2 d-flex align-items-center">
            <MaterialAvatar :image="selectedImage ? selectedImage : defaultImageUrl" alt="Avatar" size="xxl"
              class="p-0 mb-3" />
          </div>

          <div class="col-6 ms-2">
            <h3 class="mt-3">{{ userInfo.memberName }}</h3>
            <div class="row">
              <div class="col-12 d-flex align-items-center">
                <input class="form-control form-control-sm border" id="formFileSm" type="file" accept=".jpg"
                  :disabled="isInputDisabled" @change="selectedImageChange">
                <MaterialButton variant="outline" color="primary" class="w-auto col-6" size="sm"
                  :disabled="isInputDisabled" @click="showDeleteImageModal"> 삭제
                </MaterialButton>
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
              <MaterialInput class="input-group-static mb-4" type="text" :placeholder="userInfo.memberId"
                :inputValue="userInfo.memberId" id="memberId" :isDisabled="true" :isRequired="true" />
            </div>
          </div>

          <div class="row">
            <div class="col-md-6">
              <span class="text-primary">*</span>
              <label for="formFileSm" class="form-label">닉네임</label>
              <MaterialInput class="input-group-static mb-4" type="text" :placeholder="userInfo.memberName"
                :inputValue="userInfo.memberName" id="memberName" :isDisabled="isInputDisabled" :isRequired="true"
                @inputEvent="(inputValue) => {
                  memberNickname = inputValue
                }" />
            </div>
          </div>

          <div class="row d-flex align-items-center">
            <div class="col-md-6">
              <span class="text-primary">*</span>
              <label for="formFileSm" class="form-label">이메일</label>
              <MaterialInput class="input-group-static mb-4" type="text" :placeholder="userInfo.emailId"
                :inputValue="userInfo.emailId" id="memberEmailId" :isDisabled="isInputDisabled" :isRequired="true"
                @inputEvent="(inputValue) => { memberEmailId = inputValue }" />

            </div>

            <!-- 이메일 도메인 -->
            <div class="dropdown col-md-6">
              <MaterialButton id="dropdownMenuButton" variant="gradient" color="light" class="dropdown-toggle"
                data-bs-toggle="dropdown" :class="{ show: showDropdown }" :area-expanded="showDropdown"
                :disabled="isInputDisabled" :isRequired="true" @click="showDropdown = false"
                @click.prevent="showDropdown = !showDropdown" :inputValue="selectedEmailDomain">
                {{ selectedEmailDomain }}
              </MaterialButton>
              <ul class="dropdown-menu px-2 py-3" :class="{ show: showDropdown }" aria-labelledby="dropdownMenuButton">
                <li v-for="emailDomain of emailDomains" :key="emailDomain" @click="selectDomain(emailDomain)">
                  <a class="dropdown-item border-radius-md">
                    @{{ emailDomain }}
                  </a>
                </li>
              </ul>
            </div>
          </div>

          <div class="row mb-4" style="position: relative;">
            <label for="datePicker" class="form-label">생년월일</label>
            <datePicker v-model="memberBirthdate" :icon-color="dateIconColor" placeholder="YYYY-MM-DD"
              :clear-button=true :prevent-disable-date-selection="true" :disabled="isInputDisabled">
            </datePicker>
          </div>

          <div class="row">
            <div class="col-12 d-flex justify-content-end">
              <MaterialButton color="danger" class="mt-3 mb-0 me-3" size="lg" @click.prevent="showDeleteModal">회원탈퇴
              </MaterialButton>

              <MaterialButton v-if="isInputDisabled" variant="gradient" color="info" class="mt-3 mb-0 me-6" size="lg"
                @click.prevent="isInputDisabled = !isInputDisabled">수정하기</MaterialButton>
              <MaterialButton v-else variant="gradient" color="info" class="mt-3 mb-0 me-6" size="lg"
                @click.prevent="showUpdateModal" :disabled="!isAllValidationsOK">저장하기</MaterialButton>
            </div>
          </div>

        </div>
      </form>
    </div>
  </div>

</template>
