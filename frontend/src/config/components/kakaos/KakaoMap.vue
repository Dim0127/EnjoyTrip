<script setup>
import { onMounted } from 'vue';
import { useKakaoStore } from "@/stores/kakaoStore.js";
const kakaoStore = useKakaoStore();

onMounted(() => {
    !window.kakao || !window.kakao.maps ? kakaoStore.addScript() : kakaoStore.initMap();
})
</script>

<template>
    <div id="map" style="width: 100%; height: 500px; position: relative; overflow: hidden">
    </div>
</template>

<style scoped></style>