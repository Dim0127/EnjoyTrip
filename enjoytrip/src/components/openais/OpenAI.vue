<script setup>
import { ref , onMounted} from 'vue';
import { getResponse } from '@/api/openai';
import { getSido } from '@/api/attraction.js'

import MaterialButton from '@/components/materials/MaterialButton.vue';

const selectedWhen = ref();
const whenOptions = ref([
    "봄",
    "여름",
    "가을",
    "겨울",
])
const showWhen = ref(false);

// 장소 - 공공데이터 API에서 시군 데이터 받아오기
const selectedWhere = ref();
const whereOptions = ref([])
const callGetSido = async () => {
    let areas = await getSido();

    areas.forEach((area) => {
        whereOptions.value.push(area.name);
    });
}
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

//장소가 세팅이 되면 검색 가능하게 하기
const isDisable = ref(true);
onMounted(async() => {
    await callGetSido();
    isDisable.value = false;
})

const response = ref([]);
const loading = ref(false);
const callOpenAI = async () => {
    loading.value = true;
    response.value = await getResponse(selectedWhen, selectedWhere, selectedWho);
    loading.value = false;
}
</script>

<template>

    <div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6 mb-6 pb-10 d-flex align-items-center" style="height: 800px;">
        <h3 class="mt-6 mb-5"> 원하는 옵션을 선택해주세요! </h3>
        <div class="row w-70 mb-5">
            <div class="row d-flex align-items-center">
                <div class="dropdown col col-3">
                    <MaterialButton variant="gradient" color="white"
                        class="dropdown-toggle w-100 border border-dark p-3 mx-1" :class="{ show: showWhen }"
                        id="dropdownMenuButton" data-bs-toggle="dropdown" :aria-expanded="showWhen"
                        style="font-size:25px" @click="showWhen = !showWhen">
                        {{ selectedWhen ? selectedWhen : "언제든" }}
                    </MaterialButton>
                    <ul class="dropdown-menu mt-3 px-2 py-3 " :class="{ show: showWhen }"
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
                        {{ selectedWhere ? selectedWhere : "어디든" }}
                    </MaterialButton>
                    <ul class="dropdown-menu px-2 py-3" aria-labelledby="dropdownMenuButton"
                        :class="{ show: showWhere }">
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
                        style="font-size:25px;background-color:#2163ab;color:white;" @click="callOpenAI" :disabled="isDisable">
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

    </div>
</template>

<style>
@font-face {
    font-family: 'TTLaundryGothicB';
    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/2403-2@1.0/TTLaundryGothicB.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}

@media (min-width: 992px) {
    .dropdown:not(.dropdown-hover) .dropdown-menu.show {
        margin-top: 4rem !important;
        max-height: 280px;
        overflow-y: auto;
    }
}


div,
h1,
h2,
h3 {
    font-family: 'TTLaundryGothicB';
}
</style>