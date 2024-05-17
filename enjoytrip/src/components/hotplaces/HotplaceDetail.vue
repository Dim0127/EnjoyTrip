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
</script>

<template>
  <div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6 mb-4">
    <!--배경색 주고 싶으면 color="bg-gradient-success"-->
    <div class="container d-flex align-items-center justify-content-center mt-4 mb-4">
      <div class="card col-6 m-3">
        <HotplaceItem v-if="hotplace !== null" :hotplace="hotplace"></HotplaceItem>
      </div>
      <div class="card col-6 m-3 d-flex justify-content-center">
        <div class="card-body pt-3">
          <div class="text-center">
            <ReviewFormItem image="https://bit.ly/3q0AlKO" title="Get insights on Search"
              description="Website visitors today demand a frictionless user expericence — especially when using search. Because of the hight standards." />
          </div>
          <ReviewList></ReviewList>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>