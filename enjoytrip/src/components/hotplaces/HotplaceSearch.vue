<script setup>
import KakaoMap from "@/components/kakaos/KakaoMap.vue";
const isKeywword = true;
</script>

<template>
  <div class="container mt-5">
    <div class="row">
      <KakaoMap :isKeyword="isKeywword">
      </KakaoMap>
    </div>
  </div>
</template>

<style scoped></style>
