<script setup>
import { RouterLink, RouterView } from "vue-router";
import Header from "@/components/layout/TheHeader.vue";
import Footer from "@/components/layout/TheFooter.vue";
import Kakao from "@/components/kakaos/KakaoMap.vue"
</script>

<template>
  <Header></Header>
  <RouterView />
  <Kakao></Kakao>
  <Footer></Footer>
</template>

<style scoped></style>
