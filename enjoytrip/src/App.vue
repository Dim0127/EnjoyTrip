<script setup>
import { RouterLink, RouterView } from "vue-router";

import Header from "@/components/layouts/TheHeader.vue";
import FooterCentered from "@/components/layouts/TheCenteredFooter.vue";



</script>



<template>
  <div class="container position-sticky z-index-sticky top-0">
    <div class="row">
      <div class="col-12">
        <!-- <Header :sticky="true" /> -->
      </div>
    </div>
  </div>

  <RouterView />
  <FooterCentered></FooterCentered>
</template>

<style scoped></style>
