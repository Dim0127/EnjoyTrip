<script setup>
import { ref, onMounted, watch } from "vue";
import { storeToRefs } from "pinia";
import { getSido, getGugun, getResult } from '@/api/attraction.js'
import { useKakaoStore } from "@/stores/kakaoStore.js";

const emits = defineEmits(['updateAttractions'])

const kakaoStore = useKakaoStore();
const { removeMarkers, displayMarkers, setInfowindows, addMouseEvent, setBounds, } = kakaoStore;
const { markers, bounds, infowindowContents } = storeToRefs(kakaoStore);
const attractions = ref();

const selectedSido = ref("0");
const callGetSido = async () => {
    let areas = await getSido();

    let sel = document.getElementById("search-sido");
    areas.forEach((area) => {
        let opt = document.createElement("option");
        opt.setAttribute("value", area.code);
        opt.appendChild(document.createTextNode(area.name));

        sel.appendChild(opt);
    });
}

onMounted(async () => {
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

        attractions.value = [];
        removeMarkers();
        const result = await getResult(sidoCode, gugunCode, contentTypeCode);
        for (const attraction of result) {
            attractions.value.push({
                id: attraction.contentid,
                name: attraction.title,
                address: attraction.addr1,
                lng: attraction.mapx,
                lat: attraction.mapy,
                image: attraction.firstimage || "",
                phone: attraction.tel || "없음",
            });

            const marker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(attraction.mapy, attraction.mapx),
                clickable: true,
            });
            markers.value.push(marker);

            const infowindowContent =
                `<div style="width:200px; background-color:white; border-radius:10px; border:1px solid #ccc; padding: 5px;">
                    <div style="text-align:center;">
                        <h6 style="margin:0; font-size:14px;">${attraction.title}</h6>
                        <p style="margin:0; font-size:12px;">${attraction.addr1}</p>
                    </div>
                </div>`;

            infowindowContents.value.push(infowindowContent);
        }
        displayMarkers();
        setInfowindows();
        addMouseEvent();

        bounds.value = new kakao.maps.LatLngBounds();
        setBounds();

        emits('updateAttractions', attractions.value);
    } catch (error) {
        console.error('Error:', error);
    }
}
</script>


<template>
    <div class="overlay">
        <div class="d-flex my-3" role="search">
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
            <button id="btn-search" class="btn btn-outline-success mt-2 me-2 w-20" @click="callGetResult">
                검색
            </button>

        </div>
    </div>
</template>

<style scoped></style>