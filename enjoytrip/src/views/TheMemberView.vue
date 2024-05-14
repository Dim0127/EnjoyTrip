<script setup>
import { RouterLink, RouterView } from "vue-router";
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
            이곳은 회원 뷰 입니다.
          </h3>
        </div>
      </div>
    </div>
  </div>

  <RouterView></RouterView>
</template>

<style scoped></style>
