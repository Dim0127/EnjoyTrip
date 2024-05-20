<script setup>
import { onMounted } from 'vue';
import { kakaoStore } from "@/stores/kakaoStore.js";
const kStore = kakaoStore();

onMounted(() => {
    !window.kakao || !window.kakao.maps ? kStore.addScript() : kStore.initMap();
})
</script>

<template>
    <div id="map" style="width: 100%; height: 500px; position: relative; overflow: hidden">
    </div>
</template>

<style scoped></style>