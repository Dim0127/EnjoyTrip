<script setup>
const props = defineProps({
<<<<<<< HEAD
  image: {
    type: String,
    default: "",
  },
  color: {
    type: String,
    default: "",
  },
  name: {
    type: String,
    required: true,
  },
  date: {
    type: String,
    required: true,
  },
  review: {
    type: String,
    required: true,
  },
  rating: {
    type: Number,
    required: true,
  },
=======
    image: {
        type: String,
        default: "",
    },
    color: {
        type: String,
        default: "",
    },
    name: {
        type: String,
        required: true,
    },
    date: {
        type: String,
        required: true,
    },
    review: {
        type: String,
        required: true,
    },
    rating: {
        type: Number,
        required: true,
    },
>>>>>>> 599ae326ce5ba4e88b4f37c6d59f79075bbdd16a
});

// gets rating
const ratings = (rating) => {
<<<<<<< HEAD
  console.log(rating);
  let ratingValue;
  if (rating == 1) {
    ratingValue = `
    ${
      props.color
        ? `
=======
    console.log(rating);
    let ratingValue;
    if (rating == 1) {
        ratingValue = `
    ${props.color
                ? `
>>>>>>> 599ae326ce5ba4e88b4f37c6d59f79075bbdd16a
    <i class="fas fa-star text-white" aria-hidden="true"></i>
    <i class="far fa-star text-white" aria-hidden="true"></i>
    <i class="far fa-star text-white" aria-hidden="true"></i>
    <i class="far fa-star text-white" aria-hidden="true"></i>
    <i class="far fa-star text-white" aria-hidden="true"></i>
    `
<<<<<<< HEAD
        : `
=======
                : `
>>>>>>> 599ae326ce5ba4e88b4f37c6d59f79075bbdd16a
    <i class="fas fa-star" aria-hidden="true"></i>
    <i class="far fa-star" aria-hidden="true"></i>
    <i class="far fa-star" aria-hidden="true"></i>
    <i class="far fa-star" aria-hidden="true"></i>
    <i class="far fa-star" aria-hidden="true"></i>

    `
<<<<<<< HEAD
    }
`;
  } else if (rating == 2) {
    ratingValue = `
    ${
      props.color
        ? `
=======
            }
`;
    } else if (rating == 2) {
        ratingValue = `
    ${props.color
                ? `
>>>>>>> 599ae326ce5ba4e88b4f37c6d59f79075bbdd16a
    <i class="fas fa-star text-white" aria-hidden="true"></i>
    <i class="fas fa-star text-white" aria-hidden="true"></i>
    <i class="far fa-star text-white" aria-hidden="true"></i>
    <i class="far fa-star text-white" aria-hidden="true"></i>
    <i class="far fa-star text-white" aria-hidden="true"></i>
          `
<<<<<<< HEAD
        : `
=======
                : `
>>>>>>> 599ae326ce5ba4e88b4f37c6d59f79075bbdd16a
    <i class="fas fa-star" aria-hidden="true"></i>
    <i class="fas fa-star" aria-hidden="true"></i>
    <i class="far fa-star" aria-hidden="true"></i>
    <i class="far fa-star" aria-hidden="true"></i>
    <i class="far fa-star" aria-hidden="true"></i>
        `
<<<<<<< HEAD
    }
`;
  } else if (rating == 3) {
    ratingValue = `
    ${
      props.color
        ? `
=======
            }
`;
    } else if (rating == 3) {
        ratingValue = `
    ${props.color
                ? `
>>>>>>> 599ae326ce5ba4e88b4f37c6d59f79075bbdd16a
         <i class="fas fa-star text-white" aria-hidden="true"></i>
    <i class="fas fa-star text-white" aria-hidden="true"></i>
    <i class="fas fa-star text-white" aria-hidden="true"></i>
    <i class="far fa-star text-white" aria-hidden="true"></i>
    <i class="far fa-star text-white" aria-hidden="true"></i>
          `
<<<<<<< HEAD
        : `
=======
                : `
>>>>>>> 599ae326ce5ba4e88b4f37c6d59f79075bbdd16a
    <i class="fas fa-star" aria-hidden="true"></i>
    <i class="fas fa-star " aria-hidden="true"></i>
    <i class="fas fa-star " aria-hidden="true"></i>
    <i class="far fa-star " aria-hidden="true"></i>
    <i class="far fa-star " aria-hidden="true"></i>
        `
<<<<<<< HEAD
    }
`;
  } else if (rating == 4) {
    ratingValue = `
    ${
      props.color
        ? `
=======
            }
`;
    } else if (rating == 4) {
        ratingValue = `
    ${props.color
                ? `
>>>>>>> 599ae326ce5ba4e88b4f37c6d59f79075bbdd16a
    <i class="fas fa-star text-white" aria-hidden="true"></i>
    <i class="fas fa-star text-white" aria-hidden="true"></i>
    <i class="fas fa-star text-white" aria-hidden="true"></i>
    <i class="fas fa-star text-white" aria-hidden="true"></i>
    <i class="far fa-star text-white" aria-hidden="true"></i>
`
<<<<<<< HEAD
        : `
=======
                : `
>>>>>>> 599ae326ce5ba4e88b4f37c6d59f79075bbdd16a
    <i class="fas fa-star " aria-hidden="true"></i>
    <i class="fas fa-star " aria-hidden="true"></i>
    <i class="fas fa-star " aria-hidden="true"></i>
    <i class="fas fa-star " aria-hidden="true"></i>
    <i class="far fa-star " aria-hidden="true"></i>

        `
<<<<<<< HEAD
    }
`;
  } else if (rating == 5) {
    ratingValue = `
    ${
      props.color
        ? `
=======
            }
`;
    } else if (rating == 5) {
        ratingValue = `
    ${props.color
                ? `
>>>>>>> 599ae326ce5ba4e88b4f37c6d59f79075bbdd16a
    <i class="fas fa-star text-white" aria-hidden="true"></i>
    <i class="fas fa-star text-white" aria-hidden="true"></i>
    <i class="fas fa-star text-white" aria-hidden="true"></i>
    <i class="fas fa-star text-white" aria-hidden="true"></i>
    <i class="fas fa-star text-white" aria-hidden="true"></i>
`
<<<<<<< HEAD
        : `
=======
                : `
>>>>>>> 599ae326ce5ba4e88b4f37c6d59f79075bbdd16a
    <i class="fas fa-star" aria-hidden="true"></i>
    <i class="fas fa-star" aria-hidden="true"></i>
    <i class="fas fa-star" aria-hidden="true"></i>
    <i class="fas fa-star" aria-hidden="true"></i>
    <i class="fas fa-star" aria-hidden="true"></i>

        `
<<<<<<< HEAD
    }
`;
  }
  return ratingValue;
};
</script>
<template>
  <div class="col-lg-4 col-md-8">
    <div :class="`card ${props.color ? props.color : 'card-plain'}`">
      <div class="card-body">
        <img
          v-if="props.image"
          :src="props.image"
          :alt="props.name"
          class="avatar avatar-lg border-radius-lg shadow mt-n5"
        />
        <div class="author">
          <div class="name">
            <h6 class="mb-0 font-weight-bolder" :class="props.color ? 'text-white' : ''">
              {{ props.name }}
            </h6>
            <div class="stats" :class="props.color ? 'text-white' : ''">
              <i class="far fa-clock"></i> {{ props.date }}
            </div>
          </div>
        </div>
        <p class="mt-4" :class="props.color ? 'text-white' : ''">
          {{ props.review }}
        </p>
        <div class="rating mt-3" v-html="ratings(props.rating)"></div>
      </div>
    </div>
  </div>
=======
            }
`;
    }
    return ratingValue;
};
</script>
<template>
    <div class="col-lg-4 col-md-8">
        <div :class="`card ${props.color ? props.color : 'card-plain'}`">
            <div class="card-body">
                <img v-if="props.image" :src="props.image" :alt="props.name"
                    class="avatar avatar-lg border-radius-lg shadow mt-n5" />
                <div class="author">
                    <div class="name">
                        <h6 class="mb-0 font-weight-bolder" :class="props.color ? 'text-white' : ''">
                            {{ props.name }}
                        </h6>
                        <div class="stats" :class="props.color ? 'text-white' : ''">
                            <i class="far fa-clock"></i> {{ props.date }}
                        </div>
                    </div>
                </div>
                <p class="mt-4" :class="props.color ? 'text-white' : ''">
                    {{ props.review }}
                </p>
                <div class="rating mt-3" v-html="ratings(props.rating)"></div>
            </div>
        </div>
    </div>
>>>>>>> 599ae326ce5ba4e88b4f37c6d59f79075bbdd16a
</template>
