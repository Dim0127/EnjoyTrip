<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();
import { getHotplace } from '@/api/hotplace';

import HotplaceItem from "@/components/hotplaces/items/HotplaceListItem.vue"
import ReviewFormItem from "@/components/reviews/items/ReviewFormItem.vue";
import ReviewList from "@/components/reviews/ReviewList.vue";

const hotplace = ref(null);

const getHotplaceData = async (hotplaceId) => {
  try {
    const hotplaceData = await getHotplace(hotplaceId);
    hotplace.value = hotplaceData;
  } catch (error) {
    console.log(error);
  }
};

onMounted(() => {
  const hotplaceId = router.currentRoute.value.params;
  getHotplaceData(hotplaceId.hotplaceId);
});

const updateList = ref(0);
const eventHandler = () => {
  updateList.value += 1;
}
</script>

<template>
  <!-- <div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6 mb-4"> -->
  <div class="container d-flex align-items-center justify-content-center mt-4 mb-4">
    <div class="card col-6 m-3">
      <HotplaceItem v-if="hotplace !== null" :hotplace="hotplace"></HotplaceItem>
    </div>
    <div class="card col-6 m-3 d-flex justify-content-center">
      <div class="card-body pt-3">
        <div class="text-center">
          <ReviewFormItem @reviewCreated="eventHandler" @reviewUpdated="eventHandler" @reviewDeleted="eventHandler" />
        </div>
        <ReviewList :updateList="updateList"></ReviewList>
      </div>
    </div>
  </div>
  <!-- </div> -->
</template>

<style scoped>
@font-face {
  font-family: 'TTLaundryGothicB';
  src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/2403-2@1.0/TTLaundryGothicB.woff2') format('woff2');
  font-weight: 700;
  font-style: normal;
}

div,
h1,
h3 {
  font-family: 'TTLaundryGothicB';
}
</style>