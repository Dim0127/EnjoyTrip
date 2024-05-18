<script setup>
import { ref, onMounted, watch } from "vue";
import { getSido, getGugun, getResult } from '@/api/attraction.js'
import { kakaoStore } from "@/stores/kakaoStore.js";

const kStore = kakaoStore();
const keyword = ref();

const selectedSido = ref("0"); 
const callGetSido = async () =>{
    let areas = await getSido();

    let sel = document.getElementById("search-sido");
    areas.forEach((area) => {
        let opt = document.createElement("option");
        opt.setAttribute("value", area.code);
        opt.appendChild(document.createTextNode(area.name));

        sel.appendChild(opt);
    });
}

onMounted(async ()=>{
    !window.kakao || !window.kakao.maps ? kStore.addScript() : kStore.initMap();
    try {
        await callGetSido();
    } catch (error) {
        console.error('Error while fetching areas:', error);
    }
})

const callGetGugun = async (sidoCode) => {
    let areas = await getGugun(sidoCode);

    let sel = document.getElementById("search-gugun");
    sel.innerHTML = "<option value='0' selected>시도</option>";
    areas.forEach((area) => {
        let opt = document.createElement("option");
        opt.setAttribute("value", area.code);
        opt.appendChild(document.createTextNode(area.name));

        sel.appendChild(opt);
    });
}

watch(selectedSido, async (newSidoCode) => {
    if (newSidoCode !== "0") { 
        await callGetGugun(newSidoCode);
    }
});

const callGetResult = async () => {
    try {
        const sidoCode = document.getElementById("search-sido").value;
        const gugunCode = document.getElementById("search-gugun").value;
        const contentTypeCode = document.getElementById("search-content-id").value;

        const result = await getResult(sidoCode, gugunCode, contentTypeCode);
        // 얘를 지도에 그리기
        // 얘를 리스트로 보여주기
    } catch (error) {
        console.error('Error:', error);
    }
}
</script>

<template>
    <div class="overlay">
        <div class="d-flex my-3" onsubmit="return false;" role="search">
            <select id="search-sido" class="form-select me-2" v-model="selectedSido">
                <option value="0" selected>시도</option>
            </select>
            <select id="search-gugun" class="form-select me-2">
                <option value="0" selected>구군</option>
            </select>
            <select id="search-content-id" class="form-select me-2">
                <option value="0" selected>콘텐츠</option>
                <option value="12">관광지</option>
                <option value="14">문화시설</option>
                <option value="15">축제공연행사</option>
                <option value="25">여행코스</option>
                <option value="28">레포츠</option>
                <option value="32">숙박</option>
                <option value="38">쇼핑</option>
                <option value="39">음식점</option>
            </select>
            <button id="btn-search" class="btn btn-outline-success" @click="callGetResult">
                검색
            </button>
        </div>
    </div>
</template>

<style scoped>
</style>