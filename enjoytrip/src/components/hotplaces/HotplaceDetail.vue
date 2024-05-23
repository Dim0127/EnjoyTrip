<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();
import { getHotplace } from '@/api/hotplace';
import { getFirstImage } from "@/api/review";

import HotplaceItem from "@/components/hotplaces/items/HotplaceListItem.vue"
import ReviewFormItem from "@/components/reviews/items/ReviewFormItem.vue";
import ReviewList from "@/components/reviews/ReviewList.vue";


const defaultImageUrl = import.meta.env.VITE_DEFAULT_PLACE_URL;

const hotplace = ref(null);

const getHotplaceData = async (hotplaceId) => {
  try {
    const hotplaceData = await getHotplace(hotplaceId);
    hotplace.value = hotplaceData;
  } catch (error) {
    console.log(error);
  }
};

const firstImage = ref(null);
onMounted(async () => {
  const hotplaceId = router.currentRoute.value.params.hotplaceId;
  await getHotplaceData(hotplaceId);
  firstImage.value = await getFirstImage(hotplaceId);
  if (firstImage.value === null) {
    firstImage.value = defaultImageUrl
  }
  console.log(firstImage.value)
});

const updateList = ref(0);
const eventHandler = () => {
  updateList.value += 1;
}
</script>

<template>
  <div class="row mt-4 mb-4  d-flex justify-content-center">
    <div v-if="hotplace" class="card col-3 m-3 d-flex justify-content-center">
      <div class="card-body pt-3">
        <img :src="firstImage" class="img-fluid p-1 mb-2 justify-self-center" loading="lazy"
          style="height: 345px; width: 450px; border-radius: 5%; align-self: center;" />

        <div class="ms-4 mt-5">
          <h5 style="height: 52px;">
            <i class="material-icons" aria-hidden="true" style="font-size: 20px; vertical-align: middle;">loyalty</i>
            <a class="text-dark font-weight-bolds" style="justify-self: center;">{{ hotplace.hotplaceName }}</a>
          </h5>
          <h6>
            <i class="material-icons" aria-hidden="true"
              style="font-size: 16px; vertical-align: middle;">location_on</i>
            {{ hotplace.hotplaceAddress }}
          </h6>
          <p>
            <i class="material-icons" aria-hidden="true"
              style="font-size: 12px; vertical-align: middle;">mode_comment</i>
            {{ hotplace.hotplaceCategory }}
          </p>
          <p>
            <i class="material-icons" aria-hidden="true" style="font-size: 12px; vertical-align: middle;">call</i>
            {{ hotplace.hotplacePhone ? hotplace.hotplacePhone : "없음" }}
          </p>
        </div>

        <div class="mt-auto d-flex justify-content-end">
          <i class="material-icons ms-2" aria-hidden="true" style="font-size: 24px;">chat</i>
          <span class="text-md icon-move-right ms-1" style="color:#5f91c7">
            {{ reviews ? reviews.length : 0 }}
          </span>
        </div>
      </div>
    </div>

    <div class="card col-5 m-3 d-flex justify-content-center">
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