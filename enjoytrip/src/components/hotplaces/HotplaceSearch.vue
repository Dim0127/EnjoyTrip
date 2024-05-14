<script setup>
import KakaoMap from "@/components/kakaos/KakaoMap.vue";
</script>

<template>
  <div
    class="page-header min-vh-75"
    style="
      background-image: url('https://flexible.img.hani.co.kr/flexible/normal/700/466/imgdb/original/2021/0603/20210603504045.jpg');
    "
    loading="lazy"
  >
    <div class="container">
      <div class="row">
        <div class="col-lg-7 text-center mx-auto position-relative">
          <h3 class="text-white pt-3 mt-n5 me-2" :style="{ display: 'inline-block ' }">
            이곳은 카카오 맵 뷰를 담은 핫플레이스 뷰입니다.
          </h3>
        </div>
      </div>
    </div>
  </div>
  <div class="container mt-5">
    <div class="row">
      <KakaoMap></KakaoMap>
    </div>
  </div>
</template>

<style scoped></style>
