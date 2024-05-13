<script setup>
import PlaceItem from "@/components/kakaos/items/KakaoMapPlaceListItem.vue";

defineProps(["places"]);
</script>

<template>
  <ul id="placesList">
    <PlaceItem v-for="place in places" :key="place.place_name"> </PlaceItem>
  </ul>
</template>

<style scoped></style>
