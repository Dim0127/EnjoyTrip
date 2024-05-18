<script setup>
import { ref, onMounted, watch } from 'vue'
import { useRouter } from 'vue-router';
const router = useRouter();
import { isExist, getMyReview, createReview, updateReview, deleteReview } from '@/api/review';
import ReviewStarRating from "@/components/reviews/ReviewStarRating.vue"

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

const callGetMyReview = async () => {
  try {
    const search = {
      hotplaceId: router.currentRoute.value.params.hotplaceId,
	    memberId: 'admin',
    }
    const myReview = await getMyReview(search);
    if(myReview !== null){
      rate.value = myReview.score;
      comment.value = myReview.comment;
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
	    memberId: 'admin',
    }
    const result = await isExist(search);
    state.value = result;
    if(state.value){
      callGetMyReview();
    }
    else{
      clearMyReview();
    }
  } catch (error) {
    console.log(error);
  }
}

onMounted(() => {
  checkIsExist();
})

const rate = ref(null);
const comment = ref(null);

const callCreateReview = async () => {
  try {
    const newReview = {
      hotplaceId: router.currentRoute.value.params.hotplaceId,
	    memberId: 'admin',
	    score: rate.value,
	    comment: comment.value,
      createdAt: null,
    }
    await createReview(newReview);
    checkIsExist();
    emits('reviewCreated', true);
  } catch (error) {
    console.log(error);
  }
}

const callUpdateReview = async () => {
  try {
    const newReview = {
      hotplaceId: router.currentRoute.value.params.hotplaceId,
	    memberId: 'admin',
	    score: rate.value,
	    comment: comment.value,
      createdAt: null,
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
    await deleteReview(router.currentRoute.value.params.hotplaceId, 'admin');
    checkIsExist();
    emits('reviewDeleted', true);
  } catch (error) {
    console.log(error);
  }
}
</script>

<template>
  <div class="card">
    <div class="card-body text-center">
      <h5>해당 장소에 대한 리뷰를 남겨주세요!</h5>
      <ReviewStarRating v-model:rate="rate"></ReviewStarRating>
      <textarea name="message" class="form-control border" id="message" placeholder="   리뷰를 작성해주세요" rows="3" v-model="comment"></textarea>
    
      <div v-if="state !== null && state">
        <button type="button" class="btn btn-sm mb-0 mt-3" :class="action.color" @click="callUpdateReview">
          수정
        </button>
        <button type="button" class="btn btn-sm mb-0 mt-3" :class="action.color" @click="callDeleteReview">
          삭제
        </button>
      </div>

      <div v-else>
        <button type="button" class="btn btn-sm mb-0 mt-3" :class="action.color" @click="callCreateReview">
          {{ action.label }}
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
