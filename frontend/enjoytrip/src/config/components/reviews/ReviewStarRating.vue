<script setup>
import { ref, defineProps, defineEmits, watch } from 'vue';

const props = defineProps({
  rate: {
    type: Number,
    default: 0,
  }
});

const emits = defineEmits(['update:rate']);

const selectedValue = ref(props.rate);

const updateRating = (value) => {
  selectedValue.value = value;
  emits('update:rate', value);
}

watch(() => props.rate, (newRate) => {
  selectedValue.value = newRate;
});
</script>

<template>
  <div class="container d-flex align-items-center justify-content-center">
    <div class="row">

      <!-- star rating -->
      <div class="rating-wrapper">

        <!-- star 5 -->
        <input type="radio" id="5-star-rating" name="star-rating" value="5" v-model="selectedValue">
        <label for="5-star-rating" class="star-rating" @click="updateRating(5)">
          <i class="fas fa-star d-inline-block"></i>
        </label>

        <!-- star 4 -->
        <input type="radio" id="4-star-rating" name="star-rating" value="4" v-model="selectedValue">
        <label for="4-star-rating" class="star-rating star" @click="updateRating(4)">
          <i class="fas fa-star d-inline-block"></i>
        </label>

        <!-- star 3 -->
        <input type="radio" id="3-star-rating" name="star-rating" value="3" v-model="selectedValue">
        <label for="3-star-rating" class="star-rating star" @click="updateRating(3)">
          <i class="fas fa-star d-inline-block"></i>
        </label>

        <!-- star 2 -->
        <input type="radio" id="2-star-rating" name="star-rating" value="2" v-model="selectedValue">
        <label for="2-star-rating" class="star-rating star" @click="updateRating(2)">
          <i class="fas fa-star d-inline-block"></i>
        </label>

        <!-- star 1 -->
        <input type="radio" id="1-star-rating" name="star-rating" value="1" v-model="selectedValue">
        <label for="1-star-rating" class="star-rating star" @click="updateRating(1)">
          <i class="fas fa-star d-inline-block"></i>
        </label>

      </div>
    </div>
  </div>
</template>

<style scoped>
.container-wrapper {
  background-color: #EDF0F9;
}

.container {
  height: 10vh;
}

.rating-wrapper {
  align-self: center;
  border-radius: 5rem;
  display: inline-flex;
  direction: rtl !important;
  padding: 0.6rem 1.5rem;

  label {
    color: #E1E6F6;
    cursor: pointer;
    display: inline-flex;
    font-size: 100%;
    transition: color 0.5s;
  }

  svg {
    -webkit-text-fill-color: transparent;
    -webkit-filter: drop-shadow (4px 1px 6px rgba(198, 206, 237, 1));
    filter: drop-shadow(5px 1px 3px rgba(198, 206, 237, 1));
  }

  input {
    height: 100%;
    width: 100%;
  }

  input {
    display: none;
  }

  label:hover,
  label:hover~label,
  input:checked~label {
    color: #34AC9E;
  }

  label:hover,
  label:hover~label,
  input:checked~label {
    color: #34AC9E;
  }
}
</style>
