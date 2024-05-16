<script setup>
import { RouterLink, RouterView } from "vue-router";
import image from "@/assets/img/bg.jpg";

</script>

<template>
  <div class="page-header min-vh-75" :style="{ 'background-image': 'url(' + image + ')' }" loading="lazy">
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
