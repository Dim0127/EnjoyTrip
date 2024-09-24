<script setup>
import { ref, watch, defineProps, defineEmits } from 'vue'

const props = defineProps({
  id: {
    type: String,
    default: "",
  },
  type: {
    type: String,
    default: "text",
  },
  label: {
    type: [String, Object],
    default: () => ({ class: "" }),
  },
  placeholder: {
    type: String,
    default: "",
  },
  size: {
    type: String,
    default: "md",
  },
  error: {
    type: Boolean,
    default: false,
  },
  success: {
    type: Boolean,
    default: false,
  },
  isRequired: {
    type: Boolean,
    default: false,
  },
  isDisabled: {
    type: Boolean,
    default: false,
  },
  inputClass: {
    type: String,
    default: "",
  },
  icon: {
    type: String,
    default: "",
  },
  inputValue: {
    type: String,
  }
});

const emit = defineEmits(['inputEvent'])

const inputValue = ref(props.inputValue)
watch(inputValue, (newValue) => {
  emit('inputEvent', newValue)
})

function getClasses(size, success, error) {
  let sizeValue, isValidValue;

  sizeValue = size && `form-control-${size}`;

  if (error) {
    isValidValue = "is-invalid";
  } else if (success) {
    isValidValue = "is-valid";
  } else {
    isValidValue = "";
  }

  return `${sizeValue} ${isValidValue}`;
}
</script>

<template>
  <div class="input-group">
    <label v-if="label" :class="label.class">{{ typeof label === 'string' ? label : label.text }}</label>
    <span v-if="icon" class="input-group-text"><i class="fas" :class="`fa-${icon}`" aria-hidden="true"></i></span>
    <input :id="id" :type="type" class="form-control" :class="[getClasses(size, success, error), inputClass]"
      :placeholder="placeholder" :required="isRequired" :disabled="isDisabled" v-model="inputValue" />
  </div>
</template>
