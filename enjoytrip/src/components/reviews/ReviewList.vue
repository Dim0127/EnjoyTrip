<script setup>
import ReviewListItem from "@/components/reviews/items/ReviewListItem.vue";
import ReviewFormItem from "@/components/reviews/items/ReviewFormItem.vue";
import TheMainView from "@/views/TheMainView.vue"

</script>

<template>
  <TheMainView></TheMainView>

  <div class="card card-body blur shadow-blur mx-3 mx-md-4 mt-n6 mb-4">

    <!--배경색 주고 싶으면 color="bg-gradient-success"-->
    <div class="container d-flex align-items-center justify-content-center mt-4 mb-4">
      <div class="card col-6 ms-auto">
        <div class="card-body text-center">
          <ReviewFormItem image="https://bit.ly/3q0AlKO" title="Get insights on Search"
            description="Website visitors today demand a frictionless user expericence — especially when using search. Because of the hight standards." />

          <div class="row mt-3 move-on-hover">
            <ReviewListItem name="Samuel Kamuli" color="bg-gradient-success" date="3 weeks ago" review='"Great product. Helped me cut the time to set up a site. I
          used the components within instead of starting from scratch. I
          highly recommend for developers who want to spend more time on
          the backend!."' :rating="3.0">
            </ReviewListItem>
          </div>
          <div class="row mt-3 move-on-hover">
            <ReviewListItem name="Samuel Kamuli" color="bg-gradient-success" date="3 weeks ago" review='"Great product. Helped me cut the time to set up a site. I
          used the components within instead of starting from scratch. I
          highly recommend for developers who want to spend more time on
          the backend!."' :rating="3.0">
            </ReviewListItem>
          </div>
          <div class="row mt-3 move-on-hover">
            <ReviewListItem name="Samuel Kamuli" color="bg-gradient-success" date="3 weeks ago" review='"Great product. Helped me cut the time to set up a site. I
          used the components within instead of starting from scratch. I
          highly recommend for developers who want to spend more time on
          the backend!."' :rating="3.0">
            </ReviewListItem>

          </div>

        </div>
      </div>
    </div>
  </div>




</template>

<style scoped></style>
