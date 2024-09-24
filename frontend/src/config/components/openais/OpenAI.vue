<script setup>
import { ref } from 'vue'; 
import { getResponse } from '@/api/openai';

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
const showWhere= ref(false);

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
const callOpenAI = async() => {
    loading.value = true;
    response.value =  await getResponse(selectedWhen, selectedWhere, selectedWho) ;
    loading.value = false;
}
</script>

<template>
    <div class="card container d-flex flex-column justify-content-center align-items-center text-center mt-5 mb-5 p-5">
        <h1 class="mb-5"> ~ AI에게 여행지를 추천받아봐 ~ </h1>
        <div class="row w-70 mb-5">
            <div class="row d-flex align-items-center">
                <div class="dropdown col col-3">
                    <MaterialButton variant="gradient" color="white" class="dropdown-toggle w-100 border border-dark p-3 mx-1" 
                        :class="{ show: showWhen }" id="dropdownMenuButton" data-bs-toggle="dropdown"
                        :aria-expanded="showWhen" @click="showWhen = !showWhen">
                        {{ selectedWhen ? selectedWhen : "어디든" }}
                    </MaterialButton>
                    <ul class="dropdown-menu px-2 py-3" :class="{ show: showWhen }" aria-labelledby="dropdownMenuButton">
                        <li v-for="whenName of whenOptions" :key="whenName">
                            <a class="dropdown-item border-radius-md"
                                @click="selectedWhen = whenName; showWhen = !showWhen">{{ whenName }}</a>
                        </li>
                    </ul>
                </div>
                
                <div class="dropdown col col-3">
                    <MaterialButton variant="gradient" color="white" class="dropdown-toggle w-100 border border-dark p-3 mx-1"
                        :class="{ show: showWhere }" id="dropdownMenuButton" data-bs-toggle="dropdown"
                        :aria-expanded="showWhere" @click="showWhere = !showWhere">
                        {{ selectedWhere ? selectedWhere : "언제든" }}
                    </MaterialButton>
                    <ul class="dropdown-menu px-2 py-3" :class="{ show: showWhere }" aria-labelledby="dropdownMenuButton">
                        <li v-for="whereName of whereOptions" :key="whereName">
                            <a class="dropdown-item border-radius-md"
                                @click="selectedWhere = whereName; showWhere = !showWhere">{{ whereName }}</a>
                        </li>
                    </ul>
                </div>

                
                <div class="dropdown col col-3">
                    <MaterialButton variant="gradient" color="white" class="dropdown-toggle w-100 border border-dark p-3 mx-1"
                        :class="{ show: showWho }" id="dropdownMenuButton" data-bs-toggle="dropdown"
                        :aria-expanded="showWho" @click="showWho = !showWho">
                        {{ selectedWho ? selectedWho : "누구든" }}
                    </MaterialButton>
                    <ul class="dropdown-menu px-2 py-3" :class="{ show: showWho }" aria-labelledby="dropdownMenuButton">
                        <li v-for="whoName of whoOptions" :key="whoName">
                            <a class="dropdown-item border-radius-md"
                                @click="selectedWho = whoName; showWho = !showWho">{{ whoName }}</a>
                        </li>
                    </ul>
                </div>

                <div class="dropdown col col-3">
                    <MaterialButton variant="gradient" color="primary" class="w-100 border-dark p-3" @click="callOpenAI">
                        추천해줘!
                    </MaterialButton>
                </div>
            </div>
        </div>

        <h3 class="px-5"> AI의 답변 </h3>
        <div v-if="response && response.length > 0" class="d-flex flex-wrap gap-2">
            <h5 v-for="(spot, index) in response" :key="spot" class="m-0">
                # {{ spot }}
                <br class="d-lg-none" v-if="(index + 1) % 2 === 0">
                <br class="d-xl-none" v-if="(index + 1) % 3 === 0">
            </h5>
        </div>
        <div v-if="loading" class="text-muted">
            <h5>
                검색중...
            </h5>
        </div>
    </div>
</template>

<style>
</style>