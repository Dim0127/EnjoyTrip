import { ref } from "vue";
import { defineStore } from "pinia";

export const kakaoStore = defineStore("kakaoStore", () => {
  const map = ref();
  const ps = ref();
  const markers = ref([]);
  const bounds = ref();

  // 마커 이미지 주소, 사이즈, 옵션🍴
  const imageSrc = "🍴";
  const imageSize = new kakao.maps.Size(64, 69);
  const imageOption = { offset: new kakao.maps.Point(27, 69) };

  // 마커 이미지 생성
  const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

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
  };

  const setBounds = () => {
    bounds.value = new kakao.maps.LatLngBounds(); // 바운드 초기화

    if (markers.value.length > 0) {
      for (const marker of markers.value) {
        const markerPosition = marker.getPosition();
        bounds.value.extend(markerPosition);
      }
      map.value.setBounds(bounds.value);
      map.value.setCenter(markers.value[0].getPosition());
    }
  };

  const displayMarkers = () => {
    if (markers.value.length > 0) {
      for (const marker of markers.value) {
        marker.setImage(markerImage);
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

  return { map, ps, bounds, markers, addScript, initMap, setBounds, displayMarkers, removeMarkers };
});
