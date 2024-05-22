<script setup>
import image from "@/assets/img/bg_attraction.jpg";
import OpenAI from "@/components/openais/OpenAI.vue"
</script>

<template>
    <div class="page-header min-vh-75" :style="{ 'background-image': 'url(' + image + ')','background-position': '50% 65%' }" loading="lazy">
        <div class="container">
            <div class="row">
                <div class="col-lg-7 text-center mx-auto position-relative">
                    <h2 class="pt-3 mt-n5 me-2" style="display:inline-block; color:#2f8cc2;">
                        Chat GPT에게 국내 여행지를 추천 받아보세요!
                        {{ count }}
                    </h2>
                </div>
            </div>
        </div>
    </div>
    <OpenAI></OpenAI>
</template>

<style>
@font-face {
    font-family: 'TTLaundryGothicB';
    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/2403-2@1.0/TTLaundryGothicB.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}

div,
h1,
h2,
h3 {
    font-family: 'TTLaundryGothicB';
}
</style>