<script setup>
import ReviewListItem from "@/components/reviews/items/ReviewListItem.vue";
</script>

<template>
  <!--배경색 주고 싶으면 color="bg-gradient-success"-->
  <div class="row mt-6 move-on-hover">
    <ReviewListItem
      name="Samuel Kamuli"
      color="bg-gradient-success"
      date="3 weeks ago"
      review='"Great product. Helped me cut the time to set up a site. I
    used the components within instead of starting from scratch. I
    highly recommend for developers who want to spend more time on
    the backend!."'
      :rating="3.0"
    >
    </ReviewListItem>
  </div>
</template>

<style scoped></style>
