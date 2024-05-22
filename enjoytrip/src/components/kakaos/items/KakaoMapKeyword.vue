<script setup>
import { ref, watchEffect } from 'vue';

import { useRouter } from "vue-router";
const router = useRouter();

import { storeToRefs } from "pinia";
import { useKakaoStore } from "@/stores/kakaoStore.js";
import { isExist, createHotplace } from "@/api/hotplace.js";

const kakaoStore = useKakaoStore();
const { removeMarkers, setInfowindows, displayMarkers, setBounds, addClickEvent } = kakaoStore;
const { map, ps, markers, infowindowContents } = storeToRefs(kakaoStore);

const keyword = ref("");
const places = ref([]);
const selectedPlace = ref();
const createHotplaceButtonAble = ref(true);
const moveHotplaceDetailButtonAble = ref(true);

const searchPlacesByKeyword = async (newPlaces, status) => {
  if (status === kakao.maps.services.Status.OK) {
    for (const newPlace of newPlaces) {
      newPlace["isSelected"] = false;
    }
    places.value = newPlaces;

    removeMarkers();
    for (const place of places.value) {
      const marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(place.y, place.x),
        map: map.value,
      });
      markers.value.push(marker);

      const infowindowContent =
        `<div style="width:200px; background-color:white; border-radius:10px; padding: 5px;">
          <div style="text-align:center;">
            <h6 style="margin:0; font-size:14px;">${place.place_name}</h6>
            <p style="margin:0; font-size:12px;">${place.address_name}</p>

            <a href="${place.place_url}" style="margin:0; font-size:12px;">
              장소 보기
            </a>
          </div>
        </div>`;
      infowindowContents.value.push(infowindowContent);
    }
    displayMarkers();
    setInfowindows();
    addClickEvent();

    setBounds();
  }
  else if (status === kakao.maps.services.Status.ERROR) {
    alert("검색 결과 중 오류가 발생했습니다.");
    return;
  }
};

const searchPlaces = async () => {
  selectedPlace.value = null;
  places.value = [];

  removeMarkers();
  markers.value = [];

  if (!keyword.value.trim()) {
    alert("키워드를 입력해주세요!");
    return false;
  }

  await ps.value.keywordSearch(keyword.value, searchPlacesByKeyword);
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

    <div class="option mt-1" style="position: absolute; bottom: 0; left: 18%;">
      <div class="mt-auto">
        <button @click="callCreateHotplace" :disabled="createHotplaceButtonAble">
          핫플레이스 등록하기
        </button>
        <button @click="moveHotplaceDetail" :disabled="moveHotplaceDetailButtonAble">리뷰 작성하기</button>
      </div>
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

#menu_wrap {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 300px;
  height: 110%;
  margin: 10px 0 30px 10px;
  padding: 5px;
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
  background-color: #2163ab;
  color: white;
  padding: 1px;
  font-size: 10px;
}

#placesList {
  max-height: calc(100% - 50px); 
  overflow-y: auto; 
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

.highlighted {
  border: 1px solid black;
  border-color: gray;
}
</style>