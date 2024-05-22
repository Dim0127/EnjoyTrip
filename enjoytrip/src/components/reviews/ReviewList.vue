<script setup>
import ReviewListItem from "@/components/reviews/items/ReviewListItem.vue";
import { onMounted, ref, watch } from 'vue'
import { useRouter } from 'vue-router';
const router = useRouter();

import {
  getByHotplaceId
} from '@/api/review';

import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
const memberStore = useMemberStore()
const { getUserInfo } = memberStore
const { isLogin, userInfo } = storeToRefs(memberStore)

const memberId = ref()

const props = defineProps(['hotplaceId', 'updateList'])

const reviews = ref([])

const getReviewData = async (hotplaceId) => {
  reviews.value = [];
  try {
    reviews.value = await getByHotplaceId(hotplaceId);
  } catch (error) {
    console.log(error);
  }
};

onMounted(async () => {
  let token = sessionStorage.getItem("accessToken")
  if (isLogin.value) {
    await getUserInfo(token)
    memberId.value = userInfo.value.memberId
  }
  const hotplaceId = router.currentRoute.value.params;
  // 리뷰들 다 가져왔당
  await getReviewData(hotplaceId.hotplaceId);

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
  <div v-if="reviews.length === 0" class="row mt-3 move-on-hover text-center">
    해당 핫플레이스에는 리뷰가 없습니다!
  </div>
  <div v-else>
    <div v-for="review in reviews" :key="`${review.hotplaceId}-${review.memberId}`" class="row mt-3 move-on-hover">
      <ReviewListItem color="bg-gradient-success" :review="review" :memberId="memberId">
      </ReviewListItem>
    </div>
  </div>
</template>

<style scoped></style>