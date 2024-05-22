<script setup>
import { ref } from 'vue'
const props = defineProps({
  image: {
    type: String,
    default: "",
  },
  color: {
    type: String,
    default: "",
  },
  review: {
    type: Object
  },
});

const thumbColor = ref("#ffffff")
const toggleThumbColor = () => {
  if (thumbColor.value === "#ffffff") {
    thumbColor.value = "#ffcb47"
  }
  else {
    thumbColor.value = "#ffffff"
  }
}

const ratings = (score, color) => {
  const filledStar = '<i class="fas fa-star' + (color ? ' text-white' : '') + '" aria-hidden="true"></i>';
  const emptyStar = '<i class="far fa-star' + (color ? ' text-white' : '') + '" aria-hidden="true"></i>';

  let ratingValue = '';

  for (let i = 0; i < 5; i++) {
    ratingValue += i < score ? filledStar : emptyStar;
  }

  return ratingValue;
};

function formateDate(localCreatedAt) {
  const date = new Date(localCreatedAt);
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0');
  const day = String(date.getDate()).padStart(2, '0');

  return year + "-" + month + "-" + day;
}
</script>
<template>
  <div>
    <div :class="`card ${props.color ? props.color : 'card-plain'}`">
      <div class="card-body d-flex justify-content-between align-items-end"> <!-- align-items-end 추가 -->
        <div class="author">
          <div class="name">
            <h6 class="mb-0 font-weight-bolder" :class="props.color ? 'text-white' : ''">
              작성자 : {{ review.memberId }}
            </h6>
            <div class="rating mt-3" v-html="ratings(review.score, props.color)"></div>
            <p class="mt-4" :class="props.color ? 'text-white' : ''">코멘트 : {{ review.comment }}</p>
            <div class="stats d-flex align-items-center" :class="props.color ? 'text-white' : ''">
              <div class="d-flex align-items-center">
                <i class="far fa-clock"></i> 작성 날짜 : {{ formateDate(review.createdAt) }}
              </div>
            </div>
          </div>
        </div>
        <div style="position: relative;" class="me-5 mb-5">
          <i class="material-icons ms-2 move-on-hover" aria-hidden="true"
            style="font-size: 35px; position: absolute; left: 0; top: 0;" :style="{ color: thumbColor }"
            @click="toggleThumbColor">thumb_up</i>
          <span class="ms-1 text-bold" style="font-size: 15px;position: absolute; left: 20px; top: 8px; color:white;">6</span>
        </div>
        
      </div>
    </div>
  </div>

</template>