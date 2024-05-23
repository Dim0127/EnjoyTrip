<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router';
const router = useRouter();
import { isExist, getMyReviewForHotplace, createReview, updateReview, deleteReview } from '@/api/review';

import MaterialAvatar from "@/components/materials/MaterialAvatar.vue";
import ReviewStarRating from "@/components/reviews/ReviewStarRating.vue"
import { storeToRefs } from 'pinia'
import Swal from 'sweetalert2'

import { useMemberStore } from "@/stores/member"
const memberStore = useMemberStore()
const { getUserInfo } = memberStore
const { isLogin, userInfo } = storeToRefs(memberStore)

import { uploadImage } from "@/api/firebase";
const defaultImageUrl = import.meta.env.VITE_DEFAULT_IMAGE_URL;

const memberId = ref()

defineProps({
  action: {
    type: Object,
    color: String,
    route: String,
    label: String,
    default: () => ({
      color: "bg-gradient-success",
      label: "등록하기",
    }),
  },
});

const emits = defineEmits(['reviewCreated', 'reviewUpdated', 'reviewDeleted']);

const callGetMyReviewForHotplace = async () => {
  try {
    const search = {
      hotplaceId: router.currentRoute.value.params.hotplaceId,
      memberId: memberId.value,
    }
    const myReview = await getMyReviewForHotplace(search);
    if (myReview !== null) {
      rate.value = myReview.score;
      comment.value = myReview.comment;
      imageName.value = myReview.imageName;
    }

  } catch (error) {
    console.log(error);
  }
}

const clearMyReview = () => {
  rate.value = null;
  comment.value = null;
}

const state = ref(null);
const checkIsExist = async () => {
  try {
    const search = {
      hotplaceId: router.currentRoute.value.params.hotplaceId,
      memberId: memberId.value,
    }
    const result = await isExist(search);
    state.value = result;
    if (state.value) {
      callGetMyReviewForHotplace();
    }
    else {
      clearMyReview();
    }
  } catch (error) {
    console.log(error);
  }
}

onMounted(
  async () => {
    let token = sessionStorage.getItem("accessToken")
    if (isLogin.value) {
      await getUserInfo(token)
      memberId.value = userInfo.value.memberId
      await checkIsExist();
    }
  }

)
const rate = ref(null);
const comment = ref(null);
const imageName = ref(null);

const previewImage = ref(null);
const selectedImage = ref(null);
const selectedImageChange = async (event) => {
  const imageFile = event.target.files[0];
  if (imageFile && imageFile.name.toLowerCase().endsWith('.jpg')) {
    const reader = new FileReader();

    reader.onload = function (e) {
      previewImage.value = imageFile;
      selectedImage.value = e.target.result;
    };

    reader.onerror = function (e) {
      console.error('Error reading file:', e);
    };

    reader.readAsDataURL(imageFile);
  } else {
    previewImage.value = null;
    selectedImage.value = null;
  }
}


const callCreateReview = async () => {
  const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
      confirmButton: "btn btn-success",
      cancelButton: "btn btn-danger me-3"
    },
    buttonsStyling: false
  });
  try {
    var reviewImageName = null;
    var reviewImageUrl = null;

    if (previewImage.value) {
      const imageData = await uploadImage(previewImage.value, "reviews/" + router.currentRoute.value.params.hotplaceId, memberId.value);
      reviewImageName = imageData.imageName;
      reviewImageUrl = imageData.imageUrl;
    }

    const newReview = {
      hotplaceId: router.currentRoute.value.params.hotplaceId,
      memberId: memberId.value,
      score: rate.value,
      comment: comment.value,
      createdAt: null,
      imageName: reviewImageName,
      imageUrl: reviewImageUrl,
    }

    await createReview(newReview);
    checkIsExist();
    emits('reviewCreated', true);
  } catch (error) {

    swalWithBootstrapButtons.fire({
      title: "리뷰를 작성하려면<br>로그인 해 주세요",
      icon: "info",
      showCancelButton: true,
      confirmButtonText: "로그인 하러 가기",
      cancelButtonText: "나중에 쓰기",
      reverseButtons: true

    }).then(async (result) => { // 비동기 함수로 변경
      if (result.isConfirmed) {
        try {
          router.replace({ name: "login" });
        } catch (error) {
          console.log(error);
        }
      } else if (result.dismiss === Swal.DismissReason.cancel) {

      }
    });

    console.log(error);
  }
}

const showUpdateModal = () => {
  const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
      confirmButton: "btn btn-success",
      cancelButton: "btn btn-danger me-3"
    },
    buttonsStyling: false
  });
  swalWithBootstrapButtons.fire({
    title: "수정하시겠습니까?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "네, 수정할래요.",
    cancelButtonText: "아니요.",
    reverseButtons: true

  }).then(async (result) => { // 비동기 함수로 변경
    if (result.isConfirmed) {
      try {
        await callUpdateReview();

        swalWithBootstrapButtons.fire({
          title: "수정이 완료되었습니다.",
          icon: "success"
        });
      } catch (error) {
        console.log(error);
      }
    } else if (
      result.dismiss === Swal.DismissReason.cancel
    ) {

    }
  });
}

const callUpdateReview = async () => {
  try {
    var reviewImageName = null;
    var reviewImageUrl = null;

    if (previewImage.value) {
      const imageData = await uploadImage(previewImage.value, "reviews/" + router.currentRoute.value.params.hotplaceId, memberId.value);
      reviewImageName = imageData.imageName;
      reviewImageUrl = imageData.imageUrl;
    }

    const newReview = {
      hotplaceId: router.currentRoute.value.params.hotplaceId,
      memberId: memberId.value,
      score: rate.value,
      comment: comment.value,
      createdAt: null,
      imageName: reviewImageName,
      imageUrl: reviewImageUrl,
    }
    await updateReview(newReview);
    checkIsExist();
    emits('reviewUpdated', true);
  } catch (error) {
    console.log(error);
  }
}


const callDeleteReview = async () => {
  try {
    //기존에 등록된 사진을 삭제한다
    if (imageName.value) {
      await deleteImage(imageName.value);
    }

    await deleteReview(router.currentRoute.value.params.hotplaceId, memberId.value);
    checkIsExist();
    emits('reviewDeleted', true);
  } catch (error) {
    console.log(error);
  }
}

const showDeleteModal = () => {
  const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
      confirmButton: "btn btn-success",
      cancelButton: "btn btn-danger me-3"
    },
    buttonsStyling: false
  });
  swalWithBootstrapButtons.fire({
    title: "리뷰를 삭제하시겠습니까?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "네, 삭제할래요.",
    cancelButtonText: "아니요.",
    reverseButtons: true

  }).then(async (result) => { // 비동기 함수로 변경
    if (result.isConfirmed) {
      try {
        await callDeleteReview();

        swalWithBootstrapButtons.fire({
          title: "리뷰가 삭제되었습니다!",
          icon: "success"
        });
      } catch (error) {
        console.log(error);
      }
    } else if (
      result.dismiss === Swal.DismissReason.cancel
    ) {

    }
  });
}

</script>

<template>
  <div class="card mb-4">
    <div class="card-body text-center d-flex flex-column justify-content-between align-items-center">
      <h5>해당 장소에 대한 리뷰를 남겨주세요!</h5>
      <div class="d-flex align-items-center mt-4">
        <i class="material-icons" aria-hidden="true" style="font-size: 24px;">photo_camera</i>
        <!-- <input class="form-control form-control-sm border ms-3 w-100" id="formFileSm" type="file" accept=".jpg"
          @change="selectedImageChange"> -->
      </div>
      <!-- 
      <label for="formFileSm" class="form-label">프로필 사진</label> -->
      <div class="row">
        <div class=" d-flex align-items-center">
          <MaterialAvatar :image="selectedImage ? selectedImage : defaultImageUrl" alt="Avatar" size="xl"
            class="p-0 mb-3 ms-1 me-3" />
          <input class="form-control form-control-sm border" id="formFileSm" type="file" accept=".jpg"
            @change="selectedImageChange">
        </div>
      </div>

      <ReviewStarRating v-model:rate="rate"></ReviewStarRating>

      <textarea name="message" class="form-control border p-3" id="message" placeholder="   리뷰를 작성해주세요" rows="3"
        v-model="comment"></textarea>

      <div v-if="state !== null && state">
        <button type="button" class="btn btn-sm mb-0 mt-3 me-2" style="background-color: #0f6dd4; color: white;"
          @click="showUpdateModal">
          수정
        </button>

        <button type="button" class="btn btn-sm mb-0 mt-3" style="background-color: #0f6dd4; color: white;"
          @click="showDeleteModal">
          삭제
        </button>
      </div>

      <div v-else>
        <button type="button" class="btn btn-sm mb-0 mt-3" style="background-color: #0f6dd4;color:white"
          @click="callCreateReview">
          {{ action.label }}
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
