<script setup>
import { RouterView } from "vue-router";
import Header from "@/components/layouts/TheHeader.vue";
import FooterCentered from "@/components/layouts/TheCenteredFooter.vue";
</script>

<template>
  <div class="container position-sticky z-index-sticky top-0">
    <div class="row">
      <div class="col-12">
        <Header :sticky="true" />
      </div>
    </div>
  </div>

  <RouterView />
  <FooterCentered></FooterCentered>
</template>

<style scoped>
@font-face {
  font-family: 'TTLaundryGothicB';
  src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/2403-2@1.0/TTLaundryGothicB.woff2') format('woff2');
  font-weight: 700;
  font-style: normal;
}

div {
  font-family: 'TTLaundryGothicB';
}
</style>
