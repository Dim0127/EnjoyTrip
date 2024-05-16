<script setup>
import { ref, onMounted, watch } from "vue";
import { getArea, getResult } from '@/api/attraction.js'

const map = ref();
const keyword = ref();
const places = ref([]);
const markers = ref([]);
const bounds = ref();

const addScript = () => {
    const script = document.createElement("script");
    script.onload = () => kakao.maps.load(initMap);
    script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=3b2905e963dece1f2a34a9b0c887904b&libraries=clusterer,drawing,services";
    document.head.appendChild(script);
};

const initMap = () => {
    const container = document.getElementById("map");
    const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
    };

    map.value = new kakao.maps.Map(container, options);
    bounds.value = new kakao.maps.LatLngBounds();
};

onMounted(async () => {
    if (!window.kakao || !window.kakao.maps) {
        addScript();
    }
    else {
        initMap();
    }

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
});


const drawMarkers = () => {
    if (markers.value.length > 0) {
        for (const marker of markers.value) {
            marker.setMap(map.value);
        }
    }
};

const removeMarkers = () => {
    if (markers.value.length > 0) {
        for (const marker of markers.value) {
            marker.setMap(null);
        }
    }
};

const setMarkers = () => {
    removeMarkers();
    bounds.value = new kakao.maps.LatLngBounds(); // Reset bounds

    for (const place of places.value) {
        const markerPosition = new kakao.maps.LatLng(place.y, place.x);
        const marker = new kakao.maps.Marker({ title: place.place_name, position: markerPosition });
        markers.value.push(marker);
        bounds.value.extend(markerPosition); // Extend bounds
    }
    drawMarkers();
    map.value.setBounds(bounds.value);
    map.value.setCenter(markers.value[0].getPosition());
};

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

const moveCenter = (lat, lng) => {
    map.setCenter(new kakao.maps.LatLng(lat, lng));
}

const makeOverListener = (map, marker, infowindow) => {
    return function () {
        infowindow.open(map, marker);
    };
}

const makeOutListener = (infowindow) => {
    return function () {
        infowindow.close();
    };
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

    <div class="row">
        <div class="col-md-12">
            <div class="map_container">
                <div id="map" style="width: 1300px; height: 800px; position: relative; overflow: hidden"></div>
            </div>
        </div>
    </div>
</template>

<style scoped></style>