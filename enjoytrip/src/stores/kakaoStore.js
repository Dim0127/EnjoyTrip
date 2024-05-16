import { ref, onMounted, watchEffect } from "vue";
import { defineStore } from "pinia";

export const kakaoStore = defineStore("kakaoStore", () => {
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
    router.push({ name: "hotplaceDetail", params: { hotplaceId: selectedPlace.id } });
  };

  watchEffect(async () => {
    if (selectedPlace.value !== null) {
      const isExistHotplace = await checkHotplace(selectedPlace.id);
      if (isExistHotplace) {
        createHotplaceAvailable.value = true;
        goCreateReviewAvailable.value = false;
      } else {
        createHotplaceAvailable.value = false;
        goCreateReviewAvailable.value = true;
      }
    } else {
      createHotplaceAvailable.value = true;
      goCreateReviewAvailable.value = true;
    }
  });

  return { hotplaces, getAllHotplace };
});
