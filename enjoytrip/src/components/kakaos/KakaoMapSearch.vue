<script setup>
import { ref, onMounted, watchEffect } from "vue";
import { useRoute, useRouter } from "vue-router";
const route = useRoute();
const router = useRouter();
import { isExist, createHotplace } from "@/api/hotplace.js";

const map = ref();
const ps = ref();
const bounds = ref();

const places = ref([]);
const keyword = ref("");
const selectedPlace = ref();
const createHotplaceAvailable = ref(false);
const goCreateReviewAvailable = ref(false);

const markers = ref([]);

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
  ps.value = new kakao.maps.services.Places();
  bounds.value = new kakao.maps.LatLngBounds();

  createHotplaceAvailable.value = true;
  goCreateReviewAvailable.value = true;
};

onMounted(() => {
  !window.kakao || !window.kakao.maps ? addScript() : initMap();
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

const searchPlacesByKeyword = (newPlaces, status) => {
  if (status === kakao.maps.services.Status.OK) {
    for (const newPlace of newPlaces) {
      newPlace["isSelected"] = false;
    }
    places.value = newPlaces;
    setMarkers();
  } else if (status === kakao.maps.services.Status.ERROR) {
    alert("검색 결과 중 오류가 발생했습니다.");
    return;
  }
};

const searchPlaces = () => {
  selectedPlace.value = null;
  places.value = [];
  removeMarkers();
  markers.value = [];
  bounds.value = new kakao.maps.LatLngBounds();

  if (!keyword.value.trim()) {
    alert("키워드를 입력해주세요!");
    return false;
  }

  ps.value.keywordSearch(keyword.value, searchPlacesByKeyword);
};

const selectPlace = (place) => {
  if (selectedPlace.value !== null) {
    selectedPlace.value.isSelected = false;
  }
  place.isSelected = true;
  selectedPlace.value = place;
};

const checkHotplace = async () => {
  try {
    return await isExist(selectedPlace.id);
  } catch (error) {
    console.log(error);
  }
};

const plzCreateHotplace = async () => {
  const hotplace = selectedPlace.value;
  const hotplaceDto = {
    hotplaceId: hotplace.id,
    hotplaceName: hotplace.place_name,
    hotplaceLag: hotplace.x,
    hotplaceLat: hotplace.y,
    hotplaceAddress: hotplace.address_name,
    hotplacePhone: hotplace.phone,
  };

  try {
    const isSuccess = await createHotplace(hotplaceDto);
    if (isSuccess) {
      createHotplaceAvailable.value = true;
      goCreateReviewAvailable.value = false;
    }
  } catch (error) {
    console.log(error);
  }
};

const moveHotplaceDetail = () => {
  router.push({ name: 'hotplaceDetail', params: { hotplaceId: selectedPlace.id } });
}

watchEffect(async () => {
  if (selectedPlace.value !== null) {
    const isExistHotplace = await checkHotplace(selectedPlace.id);
    if (isExistHotplace) {
      createHotplaceAvailable.value = true;
      goCreateReviewAvailable.value = false;
    }
    else {
      createHotplaceAvailable.value = false;
      goCreateReviewAvailable.value = true;
    }
  } else {
    createHotplaceAvailable.value = true;
    goCreateReviewAvailable.value = true;
  }
});
</script>

<template>
  <div class="map_wrap">
    <div id="map" style="width: 100%; height: 100%; position: relative; overflow: hidden"></div>
    <div id="menu_wrap" class="bg_white">
      <div class="option">
        <div>
          키워드 : <input type="text" v-model="keyword" id="keyword" size="15" />
          <button @click="searchPlaces">검색하기</button>
        </div>
      </div>
      <hr />
      <ul id="placesList">
        <li v-for="place in places" :key="place.place_name">
          <span class="markerbg">
            <div class="info" :class="{ highlighted: place.isSelected }" @click="selectPlace(place)">
              <h5>{{ place.place_name }}</h5>
              <span>{{
                place.road_address_name ? place.road_address_name : "도로명 주소 없음"
              }}</span>
              <br />
              <span class="jibun gray"></span>
              <span>{{ place.address_name }}</span> <br />
              <span class="tel">{{ place.phone ? place.phone : "전화번호 없음" }}</span>
            </div>
          </span>
        </li>
      </ul>
      <hr />
      <div class="option">
        <div>
          <button @click="plzCreateHotplace" :disabled="createHotplaceAvailable">
            핫플레이스 등록하기
          </button>
          <button :disabled="goCreateReviewAvailable" @click="moveHotplaceDetail">리뷰 작성하기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}

.map_wrap a,
.map_wrap a:hover,
.map_wrap a:active {
  color: #000;
  text-decoration: none;
}

.map_wrap {
  position: relative;
  width: 100%;
  height: 500px;
}

#menu_wrap {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 250px;
  margin: 10px 0 30px 10px;
  padding: 5px;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.7);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}

.bg_white {
  background: #fff;
}

#menu_wrap hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 2px solid #5f5f5f;
  margin: 3px 0;
}

#menu_wrap .option {
  text-align: center;
}

#menu_wrap .option p {
  margin: 10px 0;
}

#menu_wrap .option button {
  margin-left: 5px;
}

#placesList li {
  list-style: none;
}

#placesList .item {
  position: relative;
  border-bottom: 1px solid #888;
  overflow: hidden;
  cursor: pointer;
  min-height: 65px;
}

#placesList .item span {
  display: block;
  margin-top: 4px;
}

#placesList .item h5,
#placesList .item .info {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}

#placesList .item .info {
  padding: 10px 0 10px 55px;
}

#placesList .info .gray {
  color: #8a8a8a;
}

#placesList .info .jibun {
  padding-left: 26px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;
}

#placesList .info .tel {
  color: #009900;
}

#placesList .item .markerbg {
  float: left;
  position: absolute;
  width: 36px;
  height: 37px;
  margin: 10px 0 0 10px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;
}

#placesList .item .marker_1 {
  background-position: 0 -10px;
}

#placesList .item .marker_2 {
  background-position: 0 -56px;
}

#placesList .item .marker_3 {
  background-position: 0 -102px;
}

#placesList .item .marker_4 {
  background-position: 0 -148px;
}

#placesList .item .marker_5 {
  background-position: 0 -194px;
}

#placesList .item .marker_6 {
  background-position: 0 -240px;
}

#placesList .item .marker_7 {
  background-position: 0 -286px;
}

#placesList .item .marker_8 {
  background-position: 0 -332px;
}

#placesList .item .marker_9 {
  background-position: 0 -378px;
}

#placesList .item .marker_10 {
  background-position: 0 -423px;
}

#placesList .item .marker_11 {
  background-position: 0 -470px;
}

#placesList .item .marker_12 {
  background-position: 0 -516px;
}

#placesList .item .marker_13 {
  background-position: 0 -562px;
}

#placesList .item .marker_14 {
  background-position: 0 -608px;
}

#placesList .item .marker_15 {
  background-position: 0 -654px;
}

.highlighted {
  border: 1px solid black;
  border-color: gray;
}
</style>
