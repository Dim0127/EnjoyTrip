<script setup>
defineProps({
  variant: {
    type: String,
    validator(variant) {
      return ["contained", "gradient", "outline"].includes(variant);
    },
    default: "contained",
  },
  color: {
    validator(color) {
      return [
        "primary",
        "secondary",
        "info",
        "success",
        "warning",
        "danger",
        "error",
        "light",
        "white",
        "dark",
        "none",
      ].includes(color);
    },
    default: "",
  },
  size: {
    validator(size) {
      return ["sm", "md", "lg"].includes(size);
    },
    default: "md",
  },
  fullWidth: {
    type: Boolean,
    default: false,
  },
  disabled: {
    type: Boolean,
    default: false,
  },
  inputValue: {
    type: String,
  }
});

const emit = defineEmits(['clickEvent'])

const clickEvent = () => {
  emit('clickEvent', true)
}

function getClasses(variant, color, size, fullWidth, disabled) {
  let colorValue, sizeValue, fullWidthValue, activeValue;

  // Setting the button variant and color
  if (variant === "gradient") {
    colorValue = `bg-gradient-${color}`;
  } else if (variant === "outline") {
    colorValue = `btn-outline-${color}`;
  } else {
    colorValue = `btn-${color}`;
  }

  sizeValue = size && `btn-${size}`;

  fullWidthValue = fullWidth && `w-80`;

  activeValue = disabled && `disabled`;

  return `${colorValue} ${sizeValue} ${fullWidthValue} ${activeValue}`;
}
</script>
<template>
  <button class="btn m-1" :class="getClasses(variant, color, size, fullWidth, disabled)" @click="clickEvent">
    <slot />
  </button>
</template>
