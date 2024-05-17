<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router';
const router = useRouter();
import { createReview } from '@/api/review';
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
    const result = await createReview(newReview);
    console.log(result);
  } catch (error) {
    console.log(error);
  }
}
</script>

<template>
  <div class="card">
    <div class="card-body text-center">
      <h5>해당 장소에 대한 리뷰를 남겨주세요!</h5>
      <ReviewStarRating @rate = "(rate) => rate=rate"></ReviewStarRating>
      <textarea name="message" class="form-control border" id="message" placeholder="   리뷰를 작성해주세요" rows="3" v-model="comment"></textarea>

      <button type="button" class="btn btn-sm mb-0 mt-3" :class="action.color" @click="callCreateReview">
        {{ action.label }}
      </button>
    </div>
  </div>

</template>

<style scoped></style>
