<script setup>
import { ref, onMounted, watchEffect } from 'vue';
import { useRouter } from "vue-router";
const router = useRouter();
import { kakaoStore } from "@/stores/kakaoStore.js";
import { isExist, createHotplace } from "@/api/hotplace.js";

const kStore = kakaoStore();

const keyword = ref("");
const places = ref([]);
const selectedPlace = ref();
const createHotplaceButtonAble = ref(false);
const moveHotplaceDetailButtonAble = ref(false);

const searchPlacesByKeyword = async (newPlaces, status) => {
  if (status === kakao.maps.services.Status.OK) {
    for (const newPlace of newPlaces) {
      newPlace["isSelected"] = false;
    }
    places.value = newPlaces;

    kStore.removeMarkers();
    for (const place of places.value) {
      console.log(place)
      const marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(place.y, place.x),
        map: kStore.map,
      });
      kStore.markers.push(marker);
    }
    kStore.displayMarkers();

    kStore.bounds.value = new kakao.maps.LatLngBounds();
    kStore.setBounds();
  }
  else if (status === kakao.maps.services.Status.ERROR) {
    alert("검색 결과 중 오류가 발생했습니다.");
    return;
  }
};

const searchPlaces = async () => {
  selectedPlace.value = null;
  places.value = [];

  kStore.removeMarkers();
  kStore.markers = [];

  kStore.bounds.value = new kakao.maps.LatLngBounds();

  if (!keyword.value.trim()) {
    alert("키워드를 입력해주세요!");
    return false;
  }

  await kStore.ps.keywordSearch(keyword.value, searchPlacesByKeyword);
};

const selectPlace = (place) => {
  if (selectedPlace.value !== null) {
    selectedPlace.value.isSelected = false;
  }
  place.isSelected = true;
  selectedPlace.value = place;
};

const callCreateHotplace = async () => {
  const hotplace = selectedPlace.value;

  const hotplaceDto = {
    hotplaceId: hotplace.id,
    hotplaceName: hotplace.place_name,
    hotplaceCategory: hotplace.category_name.split('>')[0].trim(),
    hotplaceLag: hotplace.x,
    hotplaceLat: hotplace.y,
    hotplaceAddress: hotplace.address_name,
    hotplacePhone: hotplace.phone,
  };

  try {
    const isSuccess = await createHotplace(hotplaceDto);
    if (isSuccess) {
      createHotplaceButtonAble.value = true;
      moveHotplaceDetailButtonAble.value = false;
    }
  } catch (error) {
    console.log(error);
  }
};

const moveHotplaceDetail = () => {
  router.push({ name: 'hotplaceDetail', params: { hotplaceId: selectedPlace.value.id } });
}

const checkIsHotplace = async () => {
  try {
    return await isExist(selectedPlace.value.id);
  } catch (error) {
    console.log(error);
  }
};

watchEffect(async () => {
  if (selectedPlace.value) {
    const isExistHotplace = await checkIsHotplace();

    if (isExistHotplace) {
      createHotplaceButtonAble.value = true;
      moveHotplaceDetailButtonAble.value = false;
    } else {
      createHotplaceButtonAble.value = false;
      moveHotplaceDetailButtonAble.value = true;
    }
  } else {
    createHotplaceButtonAble.value = true;
    moveHotplaceDetailButtonAble.value = true;
  }
});
</script>

<template>
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
        <button @click="callCreateHotplace" :disabled="createHotplaceButtonAble">
          핫플레이스 등록하기
        </button>
        <button @click="moveHotplaceDetail" :disabled="moveHotplaceDetailButtonAble">리뷰 작성하기</button>
      </div>
    </div>
  </div>
</template>

<style>
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