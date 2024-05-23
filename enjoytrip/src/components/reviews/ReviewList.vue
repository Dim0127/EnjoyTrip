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

const emits = defineEmits(['reviewCnt'])
const getReviewData = async (hotplaceId) => {
  reviews.value = [];
  try {
    reviews.value = await getByHotplaceId(hotplaceId);
  } catch (error) {
    console.log(error);
  }

  console.log(reviews.value.length)
  emits('reviewCnt', reviews.value.length)
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
  <div style="width: 100%; overflow-x:hidden; overflow-y: auto; ">
    <div v-if="reviews.length === 0" class="row mt-3 text-center">
      <h3 class="w-100" style="overflow-x: hidden;">해당 핫플레이스에는 리뷰가 없습니다!</h3>
    </div>

    <div v-else style="width: 100%; height: 320px;">
      <div v-for="review in reviews" :key="`${review.hotplaceId}-${review.memberId}`" class="row mt-3 w-100" style="">
        <ReviewListItem :review="review" :memberId="memberId">
        </ReviewListItem>
      </div>
    </div>
  </div>

</template>

<style scoped></style>