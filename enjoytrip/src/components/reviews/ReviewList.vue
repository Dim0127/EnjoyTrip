<script setup>
import {ref} from 'vue'
import ReviewListItem from "@/components/reviews/items/ReviewListItem.vue";
import ReviewFormItem from "@/components/reviews/items/ReviewFormItem.vue";
import TheMainView from "@/views/TheMainView.vue"

const reviews = ref([
  {
  memberName: "집에 가고 시픈 이슬이",
  comment: "아주아주 좋은 곳이에염",
  rating: "3.0"
},
{
memberName: "놀고 싶은 이슬이",
comment: "나쁘지 않네염",
  rating: "2.0"
}
])

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

          <div v-for="review in reviews" class="row mt-3 move-on-hover">
            <ReviewListItem :name="review.memberName" color="bg-gradient-success" date="3 weeks ago" :review="review.comment" :rating="review.rating">
            </ReviewListItem>
          </div>
          
        </div>
      </div>
    </div>
  </div>




</template>

<style scoped></style>
