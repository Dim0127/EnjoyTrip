<script setup>
import image from "@/assets/img/bg_hotplace3.jpg";
</script>

<template>
  <div class="page-header min-vh-100" :style="{ 'background-image': 'url(' + image + ')' }" loading="lazy">
    <div class="container">
      <div class="row">
        <div class="col-lg-7 text-center mx-auto position-relative">
          <h1 class="text-white pt-3 mt-n5 me-2" :style="{ display: 'inline-block ' }">
            Welcome!
          </h1>
          <h3 class=" px-5 mt-3" style="color:#2f8cc2" :style="{ fontWeight: '500' }">
            나만의 장소를
            <br> 모두의 핫플레이스로
            <br>
            <h3 style="color : #2f8cc2">EnjoyTrip에서 함께 공유해요
            </h3>
          </h3>
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

div,
h1,
h2,
h3,
h4,
h5 {
  font-family: 'TTLaundryGothicB';
}
</style>
