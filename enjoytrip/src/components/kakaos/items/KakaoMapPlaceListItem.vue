<script setup>
defineProps(["place"]);
</script>

<template>
  <div>
    <span class="markerbg">
      <div class="info" :class="{ highlighted: place.isSelected }" @click="selectPlace(place)">
        <h5>{{ place.place_name }}</h5>
        <span>{{ place.road_address_name ? place.road_address_name : "도로명 주소 없음" }}</span>
        <br />
        <span class="jibun gray"></span>
        <span>{{ place.address_name }}</span> <br />
        <span class="tel">{{ place.phone ? place.phone : "전화번호 없음" }}</span>
      </div>
    </span>
  </div>
</template>

<style scoped></style>
