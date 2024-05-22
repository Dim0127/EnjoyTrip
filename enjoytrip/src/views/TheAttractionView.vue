<script setup>
import { ref } from 'vue'
import KakaoAddress from "@/components/kakaos/items/KakaoMapAddress.vue";
import KakaoMap from "@/components/kakaos/KakaoMap.vue";
import AttractionList from "@/components/attractions/AttractionList.vue"
import image from "@/assets/img/bg_attraction.jpg";

const attractions = ref([]);
const updateAttractions = (updatedAttractions) => {
  attractions.value = updatedAttractions;
};

const searching = ref(false);
const count = ref(0);
const isSearching = (searchingState) => {
  searching.value = searchingState;
  if (isSearching) count.value += 1;
}

</script>

<template>
  <div class="page-header min-vh-75" :style="{ 'background-image': 'url(' + image + ')', 'background-size': 'cover',
   'background-repeat': 'no-repeat',
   'background-position': '50% 65%'}" loading="lazy">
    <div class="container">
      <div class="row">
        <div class="col-lg-7 text-center mx-auto position-relative">
          <h2 class="pt-3 mt-n5 me-2" style="display:inline-block; color:#2f8cc2;">
            원하는 조건에 맞는 관광지를 검색해보세요!
          </h2>
        </div>
      </div>
    </div>
  </div>

  <div class="d-flex align-items-center">
    <div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6 mb-4 d-flex align-items-center col-10">
      <div class="container mt-5">
        <div class="row">
          <KakaoAddress @updateAttractions="updateAttractions" @searching="isSearching"></KakaoAddress>
          <div class="map_wrap">
            <KakaoMap></KakaoMap>
          </div>

          <div v-if="!searching">
            <div v-if="count == 0" class="w-100 my-3 p-5">
              <!-- <h5 class="mb-0 text-center"> 관광지를 검색해보세요! </h5> -->
            </div>
            <div v-else class="mt-3 my-3">
              <div v-if="attractions.length > 0" class="mt-3 my-3">
                <AttractionList :attractions="attractions"></AttractionList>
              </div>

            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
@font-face {
  font-family: 'TTLaundryGothicB';
  src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/2403-2@1.0/TTLaundryGothicB.woff2') format('woff2');
  font-weight: 700;
  font-style: normal;
}

div h2 {
  font-family: 'TTLaundryGothicB';
}
</style>
