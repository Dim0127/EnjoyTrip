<script setup>
import {onMounted, ref, watch} from 'vue'
import { useRouter } from 'vue-router';
const router = useRouter();
import { getByHotplaceId } from '@/api/review';
import ReviewListItem from "@/components/reviews/items/ReviewListItem.vue";

const props = defineProps(['hotplaceId', 'updateList'])

const reviews = ref([])
const getReviewData = async (hotplaceId) => {
  try {
      const reviewData = await getByHotplaceId(hotplaceId);
      reviews.value = reviewData;
    } catch (error) {
      console.log(error);
    }
};

onMounted(()=>{
  const hotplaceId = router.currentRoute.value.params;
  getReviewData(hotplaceId.hotplaceId);
})

const reloadReviewData = () => {
  const hotplaceId = router.currentRoute.value.params;
  getReviewData(hotplaceId.hotplaceId);
}

watch(() => props.updateList, () => {
  reloadReviewData();
});
</script>

<template>
  <div v-if="reviews.length===0" class="row mt-3 move-on-hover text-center">
    해당 핫플레이스에는 리뷰가 없습니다!
  </div>
  <div v-else>
    <div v-for="review in reviews" :key="`${review.hotplaceId}-${review.memberId}`" class="row mt-3 move-on-hover">
      <ReviewListItem color="bg-gradient-success" :review="review">
      </ReviewListItem>
    </div>
  </div>
</template>

<style scoped>
</style>