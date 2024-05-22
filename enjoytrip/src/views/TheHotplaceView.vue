<script setup>
import image from "@/assets/img/bg_hotplace.jpg";
</script>

<template>
  <div class="page-header min-vh-75" :style="{ 'background-image': 'url(' + image + ')' }" loading="lazy">
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

  <div class="d-flex align-items-center">
    <div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6 mb-4 d-flex align-items-center col-10">

      <RouterView></RouterView>
    </div>
  </div>
</template>

<style scoped></style>
