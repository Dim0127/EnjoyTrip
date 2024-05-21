import { ref } from "vue";
import { defineStore } from "pinia";

export const useKakaoStore = defineStore("useKakaoStore", () => {
  const map = ref();
  const ps = ref();
  const markers = ref([]);
  const infowindowContents = ref([]);
  const infowindows = ref([]);
  const positions = ref([]);
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
    ps.value = new kakao.maps.services.Places();
    bounds.value = new kakao.maps.LatLngBounds();
  };

  const displayMarkers = () => {
    for (let i = 0; i < markers.value.length; i++) {
      const marker = markers.value[i];
      marker.setMap(map.value);
    }
  };

  const removeMarkers = () => {
    if (markers.value.length > 0) {
      for (const marker of markers.value) {
        marker.setMap(null);
      }
    }
    markers.value = [];
  };

  const setInfowindows = () => {
    infowindows.value = [];
    for (let i = 0; i < infowindowContents.value.length; i++) {
      const marker = markers.value[i];
      const infowindowContent = infowindowContents.value[i];

      var infowindow = new kakao.maps.InfoWindow({
        position: marker.getPosition(),
        content: infowindowContent,
      });
      infowindows.value.push(infowindow);
    }
  };

  const displayInfowindow = (marker) => {
    const index = markers.value.indexOf(marker);
    var infowindow = infowindows.value[index];
    return infowindow.open(map.value, marker);
  };

  const removeInfowindow = (marker) => {
    const index = markers.value.indexOf(marker);
    var infowindow = infowindows.value[index];
    return infowindow.close();
  };

  const clickInfowindow = (marker) => {
    const index = markers.value.indexOf(marker);
    var infowindow = infowindows.value[index];
    if (infowindow.getMap()) {
      return infowindow.close();
    } else {
      return infowindow.open(map.value, marker);
    }
  };

  const addMouseEvent = () => {
    for (let i = 0; i < markers.value.length; i++) {
      const marker = markers.value[i];
      kakao.maps.event.addListener(marker, "mouseover", () => displayInfowindow(marker));
      kakao.maps.event.addListener(marker, "mouseout", () => removeInfowindow(marker));
    }
  };

  const addClickEvent = () => {
    for (let i = 0; i < markers.value.length; i++) {
      const marker = markers.value[i];
      kakao.maps.event.addListener(marker, "click", () => clickInfowindow(marker));
    }
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

  return {
    map,
    ps,
    bounds,
    markers,
    infowindowContents,
    infowindows,
    positions,
    addScript,
    initMap,
    displayMarkers,
    removeMarkers,
    setInfowindows,
    addMouseEvent,
    addClickEvent,
    setBounds,
  };
});
