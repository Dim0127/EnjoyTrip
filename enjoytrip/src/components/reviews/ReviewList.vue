<script setup>
import {ref} from 'vue'
import ReviewListItem from "@/components/reviews/items/ReviewListItem.vue";

const reviews = ref([
  {
  memberName: "집에 가고 시픈 이슬이",
  comment: "아주아주 좋은 곳이에염",
  rating: "3.0"
},
{
memberName: "놀고 싶은 이슬이",
comment: "나쁘지 않네염",
  rating: "2.0"
}
])
</script>

<template>
  <div v-for="review in reviews" class="row mt-3 move-on-hover">
    <ReviewListItem :name="review.memberName" color="bg-gradient-success" date="3 weeks ago" :review="review.comment" :rating="review.rating">
    </ReviewListItem>
  </div>
</template>

<style scoped></style>
