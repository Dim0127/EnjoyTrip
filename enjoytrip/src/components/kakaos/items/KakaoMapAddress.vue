<script setup>
import { ref, onMounted } from "vue";
import { getArea, getResult } from '@/api/attraction.js'
import { kakaoStore } from "@/stores/kakaoStore.js";

const kStore = kakaoStore();

const places = ref([]);

onMounted(async ()=>{
    !window.kakao || !window.kakao.maps ? kStore.addScript() : kStore.initMap();
    try {
        let areas = await getArea();

        let sel = document.getElementById("search-area");
        areas.forEach((area) => {
            let opt = document.createElement("option");
            opt.setAttribute("value", area.code);
            opt.appendChild(document.createTextNode(area.name));

            sel.appendChild(opt);
        });
    } catch (error) {
        console.error('Error while fetching areas:', error);
    }
})

const makeList = (data) => {
    document.querySelector("table").setAttribute("style", "display: ;");
    let trips = data.response.body.items.item;
    let tripList = ``;
    positions = [];
    trips.forEach((area) => {
        tripList += `
            <tr onclick="moveCenter(${area.mapy}, ${area.mapx});">
              <td><img src="${area.firstimage}" width="100px"></td>
              <td>${area.title}</td>
              <td>${area.addr1} ${area.addr2}</td>
              <td>${area.mapy}</td>
              <td>${area.mapx}</td>
            </tr>
          `;

        let markerInfo = {
            title: area.title,
            latlng: new kakao.maps.LatLng(area.mapy, area.mapx),
            img: area.firstimage,
            address: `${area.addr1} , ${area.addr2}`,
        };
        positions.push(markerInfo);
    });
    document.getElementById("trip-list").innerHTML = tripList;
    displayMarker();
}

const displayMarker = () => {
    var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

    for (var i = 0; i < positions.length; i++) {
        var imageSize = new kakao.maps.Size(24, 35);

        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        var marker = new kakao.maps.Marker({
            map: map,
            position: positions[i].latlng,
            title: positions[i].title,
            image: markerImage,
        });

        var infowindow = new kakao.maps.InfoWindow({
            content: `
        <div style="padding:5px;" width="300px" height="400px">
          <img src="${positions[i].img}" width="100px"> 
          <div> ${positions[i].title} </div>
          <div style="padding:5px;">${positions[i].address} </div>
          <div> </div>
        </div>`,
        });

        kakao.maps.event.addListener(marker, "mouseover", makeOverListener(map, marker, infowindow));
        kakao.maps.event.addListener(marker, "mouseout", makeOutListener(infowindow));
    }
}

const callGetResult = async () => {
    try {
        const areaCode = document.getElementById("search-area").value;
        const contentCode = document.getElementById("search-content-id").value;
        const result = await getResult(keyword.value, areaCode, contentCode);
    } catch (error) {
        console.error('Error:', error);
    }
}
</script>

<template>
    <div class="overlay">
        <div class="d-flex my-3" onsubmit="return false;" role="search">
            <select id="search-area" class="form-select me-2">
                <option value="0" selected>시도</option>
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
            <input id="search-keyword" class="form-control me-2" type="search" placeholder="검색어" aria-label="검색어"
                v-model="keyword" />
            <button id="btn-search" class="btn btn-outline-success" @click="callGetResult"
                :disabled="!keyword || !keyword.trim().length">검색</button>
        </div>
    </div>
</template>

<style scoped>
</style>