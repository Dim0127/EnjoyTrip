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
    <div class="container d-flex align-items-center justify-content-center mt-4 mb-4">
      <div class="col-6 m-3">
        <div class="d-flex justify-content-center">

          <HotplaceItem v-if="hotplace !== null" :hotplace="hotplace" class="move-on-hover"></HotplaceItem>
        </div>
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
</template>

<style scoped>

div,
h1,
h3 {
  font-family: 'TTLaundryGothicB';
}
</style>