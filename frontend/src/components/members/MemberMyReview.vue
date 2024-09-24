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
  <div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6 mb-4 pt-5 pb-5">
    <template v-if="myReviewListLen > 0">
      <template v-for="(item, index) in itemList">
        <div class="row mt-4 mb-4 d-flex justify-content-center align-items-center ">


          <div class="card d-flex py-4 justify-items-start col-4">
            <div class="card-body d-flex flex-column">
              <div class="ms-4 mt-2">
                <h5 style="height: 52px;">
                  <i class="material-icons" aria-hidden="true"
                    style="font-size: 20px; vertical-align: middle;">loyalty</i>
                  <a class="text-dark font-weight-bolds" style="justify-self: center;">{{ item.hotplace.hotplaceName
                    }}</a>
                </h5>
                <h6>
                  <i class="material-icons" aria-hidden="true"
                    style="font-size: 16px; vertical-align: middle;">location_on</i>
                  {{ item.hotplace.hotplaceAddress }}
                </h6>
                <p>
                  <i class="material-icons" aria-hidden="true"
                    style="font-size: 12px; vertical-align: middle;">mode_comment</i>
                  {{ item.hotplace.hotplaceCategory }}
                </p>
                <p>
                  <i class="material-icons" aria-hidden="true" style="font-size: 12px; vertical-align: middle;">call</i>
                  {{ item.hotplace.hotplacePhone ? item.hotplace.hotplacePhone : "없음" }}
                </p>
              </div>

              <div class="mt-auto d-flex justify-content-end">
                <RouterLink :to="{ name: 'hotplaceDetail', params: { hotplaceId: item.hotplace.hotplaceId } }">
                  <span class="text-md icon-move-right" style="color:#5f91c7">
                    자세히 보기
                    <i class="fas fa-arrow-right text-xs ms-1"></i>
                  </span>
                </RouterLink>
              </div>
            </div>
          </div>

          <ReviewListItem class="ms-2 col-5" color="bg-gradient-success" :review="item.review" />
        </div>
      </template>
    </template>
    <template v-else>
      <h3>아직 작성한 리뷰가 없어요</h3>
    </template>

  </div>
</template>

<style scoped></style>