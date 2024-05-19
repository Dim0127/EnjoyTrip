<script setup>
const props = defineProps({
  image: {
    type: String,
    default: "",
  },
  color: {
    type: String,
    default: "",
  },
  review:{
    type: Object
  },
});

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
      <div class="card-body">
        <img v-if="props.image" :src="props.image" :alt="props.name"
          class="avatar avatar-lg border-radius-lg shadow mt-n5" />
        <div class="author">
          <div class="name">
            <h6 class="mb-0 font-weight-bolder" :class="props.color ? 'text-white' : ''">
              작성자 : {{ review.memberId }}
            </h6>
            <div class="rating mt-3" v-html="ratings(review.score, props.color)">
            </div>
            <p class="mt-4" :class="props.color ? 'text-white' : ''">
              코멘트 : {{ review.comment }}
            </p>
            <div class="stats" :class="props.color ? 'text-white' : ''">
              <i class="far fa-clock"></i> 작성 날짜 : {{ formateDate(review.createdAt) }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  
</template>