<script setup>
import { ref, watch } from "vue";
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
</script>

<template>
  <div class="card">
    <div class="card-body text-center">
      <h5>해당 장소에 대한 리뷰를 남겨주세요!</h5>
      <ReviewStarRating></ReviewStarRating>
      <textarea name="message" class="form-control border" id="message" placeholder="   리뷰를 작성해주세요" rows="3"></textarea>

      <button type="button" class="btn btn-sm mb-0 mt-3" :class="action.color">
        {{ action.label }}
      </button>
    </div>
  </div>

</template>

<style scoped></style>
