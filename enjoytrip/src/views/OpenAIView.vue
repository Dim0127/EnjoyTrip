<script setup>
import { ref } from 'vue';
import { getResponse } from '@/api/openai';
import image from "@/assets/img/bg_attraction.jpg";

import MaterialButton from '@/components/materials/MaterialButton.vue';

const selectedWhen = ref();
const whenOptions = ref([
    "봄",
    "여름",
    "가을",
    "겨울",
])
const showWhen = ref(false);

const selectedWhere = ref();
const whereOptions = ref([
    "바다",
    "산",
    "국내",
    "아시아",
    "유럽",
    "도심",
])
const showWhere = ref(false);

const selectedWho = ref();
const whoOptions = ref([
    "혼자",
    "친구",
    "가족",
    "동기",
    "연인",
    "단체",
])
const showWho = ref(false);

const response = ref([]);
const loading = ref(false);
const callOpenAI = async () => {
    loading.value = true;
    response.value = await getResponse(selectedWhen, selectedWhere, selectedWho);
    loading.value = false;
}
</script>

<template>


    <div class="page-header min-vh-75" :style="{ 'background-image': 'url(' + image + ')' }" loading="lazy">
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

    <div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6 mb-6 d-flex align-items-center">
        <!-- <divclass="card container d-flex flex-column justify-content-center align-items-center text-center mt-5 mb-5 p-5"> -->
        <h3 class="mt-6 mb-5"> 원하는 옵션을 선택해주세요! </h3>
        <div class="row w-70 mb-5">
            <div class="row d-flex align-items-center">
                <div class="dropdown col col-3">
                    <MaterialButton variant="gradient" color="white"
                        class="dropdown-toggle w-100 border border-dark p-3 mx-1" :class="{ show: showWhen }"
                        id="dropdownMenuButton" data-bs-toggle="dropdown" :aria-expanded="showWhen"
                        style="font-size:25px" @click="showWhen = !showWhen">
                        {{ selectedWhen ? selectedWhen : "어디든" }}
                    </MaterialButton>
                    <ul class="dropdown-menu px-2 py-3" :class="{ show: showWhen }"
                        aria-labelledby="dropdownMenuButton">
                        <li v-for="whenName of whenOptions" :key="whenName" style="font-size:20px">
                            <a class="dropdown-item border-radius-md"
                                @click="selectedWhen = whenName; showWhen = !showWhen">{{ whenName }}</a>
                        </li>
                    </ul>
                </div>

                <div class="dropdown col col-3">
                    <MaterialButton variant="gradient" color="white" style="font-size:25px"
                        class="dropdown-toggle w-100 border border-dark p-3 mx-1" :class="{ show: showWhere }"
                        id="dropdownMenuButton" data-bs-toggle="dropdown" :aria-expanded="showWhere"
                        @click="showWhere = !showWhere">
                        {{ selectedWhere ? selectedWhere : "언제든" }}
                    </MaterialButton>
                    <ul class="dropdown-menu px-2 py-3" :class="{ show: showWhere }"
                        aria-labelledby="dropdownMenuButton">
                        <li v-for="whereName of whereOptions" :key="whereName" style="font-size:20px">
                            <a class="dropdown-item border-radius-md"
                                @click="selectedWhere = whereName; showWhere = !showWhere">{{ whereName }}</a>
                        </li>
                    </ul>
                </div>


                <div class="dropdown col col-3">
                    <MaterialButton variant="gradient" color="white"
                        class="dropdown-toggle w-100 border border-dark p-3 mx-1" :class="{ show: showWho }"
                        id="dropdownMenuButton" data-bs-toggle="dropdown" :aria-expanded="showWho"
                        style="font-size:25px" @click="showWho = !showWho">
                        {{ selectedWho ? selectedWho : "누구든" }}
                    </MaterialButton>
                    <ul class="dropdown-menu px-2 py-3" :class="{ show: showWho }" aria-labelledby="dropdownMenuButton">
                        <li v-for="whoName of whoOptions" :key="whoName" style="font-size:20px">
                            <a class="dropdown-item border-radius-md"
                                @click="selectedWho = whoName; showWho = !showWho">{{ whoName }}</a>
                        </li>
                    </ul>
                </div>

                <div class="dropdown col col-3">
                    <MaterialButton variant="gradient" class="w-100 border-dark p-3"
                        style="font-size:25px;background-color:#2163ab;color:white;" @click="callOpenAI">
                        추천해줘!
                    </MaterialButton>
                </div>
            </div>
        </div>

        <div v-if="response && response.length > 0" class="d-flex align-items-center flex-column gap-2 mb-6">

            <h3 class="px-5 mb-2"> AI의 답변 </h3>

            <h4 v-for="(spot, index) in response" :key="spot" class="m-0" style="color:#5f91c7">
                # {{ spot }}
                <br class="d-lg-none" v-if="(index + 1) % 2 === 0">
                <br class="d-xl-none" v-if="(index + 1) % 3 === 0">
            </h4>
        </div>
        <div v-if="loading" class="text-muted">
            <h3 class="px-5 mb-2">
                검색중...
            </h3>
        </div>
        <!-- </div> -->
    </div>
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