<script setup>
import {ref} from 'vue'
import KakaoAddress from  "@/components/kakaos/items/KakaoMapAddress.vue";
import KakaoMap from "@/components/kakaos/KakaoMap.vue";
import AttractionList from "@/components/attractions/AttractionList.vue"

const attractions = ref([]);
const updateAttractions = (updatedAttractions) => {
  attractions.value = updatedAttractions;
};
</script>

<template>
  <div class="page-header min-vh-75"
    style="background-image: url('https://www.etri.re.kr/webzine/20230421/img/sub05_visual.jpg')" loading="lazy">
    <div class="container">
      <div class="row">
        <div class="col-lg-7 text-center mx-auto position-relative">
          <h3 class="text-white pt-3 mt-n5 me-2" :style="{ display: 'inline-block ' }">
            이곳은 카카오 맵 뷰를 담은 관광지 검색 뷰입니다.
          </h3>
        </div>
      </div>
    </div>
  </div>
  <div class="container mt-5">
    <div class="row">
      <KakaoAddress @updateAttractions="updateAttractions"></KakaoAddress>
      <KakaoMap :isKeyword="false"></KakaoMap>
      <div class="mt-3 my-3">
        <AttractionList :attractions="attractions"></AttractionList>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
