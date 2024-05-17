import { ref } from "vue";
import { defineStore } from "pinia";

export const kakaoStore = defineStore("kakaoStore", () => {
  const map = ref();
  const ps = ref();
  const bounds = ref();
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
  };

  const setBounds = () => {
    for (const marker of markers.value) {
      const markerPosition = marker.getPosition();
      bounds.value.extend(markerPosition);
    }
    map.value.setBounds(bounds.value);
    if(markers.value.length>0){
      map.value.setCenter(markers.value[0].getPosition());
    }
  }

  const displayMarkers = () => {
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

  return { map, ps, bounds, markers, addScript, initMap, setBounds, displayMarkers, removeMarkers };
});
