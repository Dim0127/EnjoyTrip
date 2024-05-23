<script setup>
import { onMounted, ref } from 'vue'

import {
  insertHelpful,
  deleteHelpful,
  countHelpful,
  isPushedHelpful
} from '@/api/review';

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
  memberId: {
    type: String
  }
});

// onClick
const cntHelpful = ref()
const checkPushed = ref(false)

const pHotplaceId = ref()
const pWriterId = ref()
const pMemberId = ref()

onMounted(async () => {
  if (props.review) {
    console.log(props.review);
    pHotplaceId.value = props.review.hotplaceId
    pWriterId.value = props.review.memberId
    pMemberId.value = props.memberId

    await setHelpfull();
  }
}
)

const setHelpfull = async () => {
  cntHelpful.value = await countHelpful(pHotplaceId.value, pWriterId.value)

  if (cntHelpful.value > 0) {
    checkPushed.value = await isPushedHelpful(pHotplaceId.value, pWriterId.value, pMemberId.value)
  }
}

const toggleHelpful = async () => {
  if (checkPushed.value) {
    await deleteHelpful(pHotplaceId.value, pWriterId.value, pMemberId.value)
    checkPushed.value = !checkPushed.value
  }
  else {
    await insertHelpful(pHotplaceId.value, pWriterId.value, pMemberId.value)
    checkPushed.value = !checkPushed.value
  }
  await setHelpfull()
}


const ratings = (score, color) => {
  const filledStar = '<i class="fas fa-star" style="color:#ffcb47" aria-hidden="true"></i>';
  const emptyStar = '<i class="far fa-star" style="color:#ffcb47" aria-hidden="true"></i>';

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
      <div class="card-body d-flex justify-content-between rounded align-items-end"
        style="background-color:#7fc1f0;color:white">

        <div class="d-flex flex-column col-5">
          <img :src="review.imageUrl" class="img-fluid p-1 mb-2" loading="lazy"
            style="height: 230px; width: 230px; border-radius: 5%; align-self: center;" />
        </div>


        <div class="author" style="margin-bottom:auto">
          <div class="row ms-3 mt-2">
            <h5 class="mb-0 font-weight-bolder" style="color:#0f6dd4" :class="props.color ? 'text-white' : ''">
              {{ review.memberId }}
            </h5>

            <div class="rating mt-3" v-html="ratings(review.score, props.color)"></div>

            <p class="mt-4" :class="props.color ? 'text-white' : ''" style="height:80px; overflow-y:auto;">
              {{ review.comment }}</p>


            <div class="row stats mt-auto" :class="props.color ? 'text-white' : ''">
              <div class="d-flex align-items-center col-10">
                <i class="far fa-clock"></i> &nbsp;작성 날짜 : {{ formateDate(review.createdAt) }}
              </div>

              <div style="position:relative" class="col-2">
                <div class="me-5 mb-5">
                  <i id="thumb" class="material-icons ms-2 move-on-hover" aria-hidden="true"
                    style="font-size: 35px; position: absolute; left: 0; top: 0;"
                    :class="{ 'yellow': checkPushed, 'white': !checkPushed }" @click="toggleHelpful">thumb_up</i>
                  <span v-if="cntHelpful > 0" class="ms-1 text-bold"
                    style="font-size: 15px;position: absolute; left: 19px; top: 10px;color:#0f6dd4;">{{
                      cntHelpful }}</span>
                </div>
              </div>

            </div>
          </div>
        </div>




      </div>
    </div>
  </div>

</template>
<style>
.white {
  color: white;
}

.yellow {
  color: #ffcb47;
}
</style>