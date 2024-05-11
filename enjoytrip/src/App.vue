<script setup>
import { RouterLink, RouterView } from "vue-router";
import Header from "@/components/layout/TheHeader.vue";
import Footer from "@/components/layout/TheFooter.vue";
import Kakao from "@/components/kakaos/KakaoMap3.vue"
</script>

<template>
  <Header></Header>
  <RouterView />
  <div>
    <Kakao></Kakao>
  </div>
  <Footer></Footer>
</template>

<style scoped></style>
