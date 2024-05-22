<script setup>
import image from "@/assets/img/bg_hotplace3.jpg";
</script>

<template>
  <div class="page-header min-vh-75" :style="{ 'background-image': 'url(' + image + ')' }" loading="lazy">
    <div class="container">
      <div class="row">
        <div class="col-lg-7 text-center mx-auto position-relative">
          <h2 class="text-white pt-3 mt-n5 me-2" :style="{ display: 'inline-block ' }" style="color:#2d7fe3;">
            키워드로 원하는 장소를 검색하고,
          </h2>
          <h2 class="text-white me-2 mt-1" :style="{ display: 'inline-block ' }" style="color:#2d7fe3;">
            핫플레이스로 등록해보세요!
          </h2>

        </div>
      </div>
    </div>
  </div>

  <div class="d-flex align-items-center">
    <div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6 mb-4 d-flex align-items-center">

      <RouterView></RouterView>
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

h2{
  font-family: 'TTLaundryGothicB';
}</style>
