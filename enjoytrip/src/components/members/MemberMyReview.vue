<script setup>
import HotplaceListItem from "@/components/hotplaces/items/HotplaceListItem.vue"
import ReviewListItem from '../reviews/items/ReviewListItem.vue';
import { ref, onMounted } from 'vue';

import { useRouter } from 'vue-router';
const router = useRouter();

import { selectHotplaceWithReview } from '@/api/hotplace';
import { getMyReview } from '@/api/reviews';

import { useMemberStore } from "@/stores/member"
const memberStore = useMemberStore()
const { getUserInfo } = memberStore

const getHotplaceData = async (hotplaceId) => {
  try {
      const hotplaceData = await getHotplace(hotplaceId);
      hotplace.value = hotplaceData;
    } catch (error) {
      console.log(error);
    }
};

onMounted(() => {
  // getUserinfo로 memberId 가져오기
  // memberId로 getMemberReview 해서 작성한 리뷰 리스트 가져오기
  // -> 없으면 아직 작성한 리뷰가 없어요
  // -> 있으면 그 리뷰 하나하나의 hotplaceId로
  // selectHotplaceWithReview해서 hotplaceListItem띄움 -> 누르면 핫플디테일뷰로 이동
});


</script>

<template>
  <div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6 mb-4">
    <div class="container d-flex align-items-center justify-content-center mt-4 mb-4">
      
      <div class="card col-4 m-3 d-flex justify-content-center">
        <div class="card-body pt-3">
          <div class="text-center">
            <HotplaceListItem v-if="hotplace !== null" :hotplace="hotplace"></HotplaceListItem>
            
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>