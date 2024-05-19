<script setup>
import AttractionListItem from '@/components/attractions/items/AttractionListItem.vue';
defineProps(['attractions'])
</script>

<template>
    <AttractionListItem v-for="attraction in attractions" :key="attraction.id" :attraction="attraction">
    </AttractionListItem>
</template>

<style>
</style>