<script setup>
import { RouterLink, RouterView } from "vue-router";
</script>

<template>
  <div>header</div>
  <div>
    <RouterLink :to="{ name: 'login' }">member-login</RouterLink>
    |
    <RouterLink :to="{ name: 'hotplace' }">hotplace</RouterLink>
    |
    <RouterLink :to="{ name: 'review' }">review</RouterLink>
  </div>
</template>

<style scoped></style>
