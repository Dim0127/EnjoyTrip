<script setup>
import HotplaceListItem from "@/components/hotplaces/items/HotplaceListItem.vue"
import ReviewListItem from '../reviews/items/ReviewListItem.vue';
import { ref, onMounted } from 'vue';

import { selectHotplaceWithReview } from '@/api/hotplace';
import { getMyReviewList } from '@/api/review';

import { useMemberStore } from "@/stores/member"
import { storeToRefs } from 'pinia'

const memberStore = useMemberStore()
const { getUserInfo } = memberStore
const { isLogin, userInfo } = storeToRefs(memberStore)

const memberId = ref()
const myReviewList = ref([])
const myReviewListLen = ref(0)

const hotplaceWithReview = ref()

const itemList = ref([])

onMounted(
  async () => {
    // getUserinfo로 memberId 가져오기
    let token = sessionStorage.getItem("accessToken")
    if (isLogin.value) {
      await getUserInfo(token)
      memberId.value = userInfo.value.memberId

      myReviewList.value = await getMyReviewList(memberId.value);
      myReviewListLen.value = myReviewList.value.length

      if (myReviewListLen.value > 0) {
        for (let i = 0; i < myReviewListLen.value; i++) {

          hotplaceWithReview.value = await selectHotplaceWithReview({
            memberId: memberId.value,
            hotplaceId: myReviewList.value[i].hotplaceId
          })
          itemList.value.push({
            hotplace: hotplaceWithReview.value,
            review: myReviewList.value[i]
          })
        }
      }
    }
  }
);
</script>

<template>
  <div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6 mb-4 pt-5">
    <div class="container d-flex align-items-center mt-4 mb-4b flex-wrap wrap">

      <template v-if="myReviewListLen > 0">
        <div class="row">
          <template v-for="(item, index) in itemList">
            <div class="col-md-4 mb-4">
              <div class="card card-body" style="height: 700px;">
                <HotplaceListItem :hotplace="item.hotplace" class="mb-4" />
                <ReviewListItem color="bg-gradient-success" :review="item.review" />
              </div>
            </div>
          </template>
        </div>
      </template>
      <template v-else>
        <h3>아직 작성한 리뷰가 없어요</h3>
      </template>
    </div>
  </div>
</template>

<style scoped></style>